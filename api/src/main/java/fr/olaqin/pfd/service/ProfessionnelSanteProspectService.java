package fr.olaqin.pfd.service;

import fr.olaqin.pfd.config.AmazonProperties;
import fr.olaqin.pfd.config.ApplicationProperties;
import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import fr.olaqin.pfd.enums.RaisonQualif;
import fr.olaqin.pfd.enums.ResultatQualif;
import fr.olaqin.pfd.exception.ErrorCode;
import fr.olaqin.pfd.exception.common.NotFoundException;
import fr.olaqin.pfd.repository.AnnuaireProfessionnelSanteRepository;
import fr.olaqin.pfd.repository.ProfessionnelSanteProspectRepository;
import fr.olaqin.pfd.service.validator.ValidatorProfessionnelSanteProspect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static fr.olaqin.pfd.enums.RaisonQualif.*;
import static fr.olaqin.pfd.enums.ResultatQualif.NOK;
import static fr.olaqin.pfd.enums.ResultatQualif.OK;
import static fr.olaqin.pfd.utils.StringUtilsPfd.replaceAccent;
import static org.apache.commons.collections4.IterableUtils.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfessionnelSanteProspectService {

    private final ProfessionnelSanteProspectRepository professionnelSanteProspectRepository;
    private final AnnuaireProfessionnelSanteRepository annuaireRepository;
    private final SESEmailService sesEmailService;
    private final ApplicationProperties applicationProperties;
    private final AmazonProperties amazonProperties;

    @Value("classpath:templates/mail/mailNotificationStellair.json")
    private Resource resourceApiUtilisateur;

    public ProfessionnelSanteProspect checkAndSave(ProfessionnelSanteProspect professionnelSanteProspect) {
        check(professionnelSanteProspect);
        ProfessionnelSanteProspect savedPF = professionnelSanteProspectRepository.save(professionnelSanteProspect);

        if (applicationProperties != null && applicationProperties.getStellair() != null) {
            try {
                sesEmailService.sendEmail(applicationProperties.getStellair().getTemplateName(), amazonProperties.getSes().getFrom(), applicationProperties.getStellair().getToAddresses(), getDataTemplate(savedPF));
            } catch (IOException e) {
                log.error("Une erreur est produite, aucun mail n'a été envoyé", e);
            }
        }

        return savedPF;
    }

    public List<ProfessionnelSanteProspect> getAll() {
        return toList(professionnelSanteProspectRepository.findAll());
    }

    public ProfessionnelSanteProspect get(String id) {
        return professionnelSanteProspectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOTFOUNDEXCEPTION.CODE_ERREUR_PROFESSIONNEL_SANTE_AVEC_IDENTIFIANT,
                        ErrorCode.NOTFOUNDEXCEPTION.MESSAGE_ERREUR_PROFESSIONNEL_SANTE_AVEC_IDENTIFIANT,
                        new Object[]{id}
                ));
    }

    private ProfessionnelSanteProspect check(ProfessionnelSanteProspect professionnelSanteProspect) {

        new ValidatorProfessionnelSanteProspect().isSatisfiedBy(professionnelSanteProspect);

        List<AnnuaireProfessionnelSanteEnActivite> list = annuaireRepository.findAllByNomExerciceSearchAndPrenomExerciceSearch(replaceAccent(professionnelSanteProspect.getNom().toLowerCase()), replaceAccent(professionnelSanteProspect.getPrenom().toLowerCase()));
        for (AnnuaireProfessionnelSanteEnActivite proEnActivite : list) {
            if (isBlank(proEnActivite.getCodePostalCoordStructure())) {
                return setRaisonEtResultatQualif(professionnelSanteProspect, OK, AUCUNE_VILLE_RETOURNEE);
            }
            if (proEnActivite.getCodePostalCoordStructure().equals(professionnelSanteProspect.getCodePostal())) {
                return setRaisonEtResultatQualif(professionnelSanteProspect, OK, UTILISATEUR_TROUVE);
            } else if (proEnActivite.getCodePostalCoordStructure().startsWith(professionnelSanteProspect.getCodePostal().substring(0, 2))) {
                return setRaisonEtResultatQualif(professionnelSanteProspect, NOK, LOCALISATION_LEGEREMENT_DIFFERENTE);
            } else {
                return setRaisonEtResultatQualif(professionnelSanteProspect, NOK, LOCALISATION_DIFFERENTE);
            }
        }
        return setRaisonEtResultatQualif(professionnelSanteProspect, NOK, NON_TROUVE);
    }

    private ProfessionnelSanteProspect setRaisonEtResultatQualif(ProfessionnelSanteProspect professionnelSanteProspect, ResultatQualif resultat, RaisonQualif raison) {
        professionnelSanteProspect.setResultatQualif(resultat);
        professionnelSanteProspect.setRaisonQualif(raison);
        return professionnelSanteProspect;
    }

    private String getDataTemplate(ProfessionnelSanteProspect prospect) throws IOException {
        final File file = resourceApiUtilisateur.getFile();
        final String body = new String(Files.readAllBytes(file.toPath()));

        return String.format(body, applicationProperties.getStellair().getUrlForm(), applicationProperties.getStellair().getSubject(), prospect.getCivilite().getLibelle(), prospect.getNom(), prospect.getPrenom(), prospect.getEmail(), prospect.getTelephone(), prospect.getCodePostal(),
                prospect.getProfession().getLibelle(), prospect.getReponseAutreProfession(), prospect.getAccepteEtreRecontacte() ? "Oui" : "Non", prospect.getResultatQualif(), prospect.getRaisonQualif().getLibelle());
    }

}