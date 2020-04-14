package fr.olaqin.pfd.service;

import fr.olaqin.pfd.entity.Utilisateur;
import fr.olaqin.pfd.exception.ErrorCode;
import fr.olaqin.pfd.exception.common.NotFoundException;
import fr.olaqin.pfd.repository.UtilisateurRepository;
import fr.olaqin.pfd.service.validator.ValidatorAdresseMail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public Utilisateur get(final String utilisateurId) {
        return utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOTFOUNDEXCEPTION.CODE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT,
                        ErrorCode.NOTFOUNDEXCEPTION.MESSAGE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT,
                        new Object[]{utilisateurId}
                ));
    }

    public Utilisateur findByEmail(final String email) {
        new ValidatorAdresseMail().isSatisfiedBy(email);
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOTFOUNDEXCEPTION.CODE_ERREUR_UTILISATEUR_AVEC_EMAIL,
                        ErrorCode.NOTFOUNDEXCEPTION.MESSAGE_ERREUR_UTILISATEUR_AVEC_EMAIL,
                        new Object[]{email}
                ));
    }

    public void put(final Utilisateur utilisateur) {
        final String utilisateurId = utilisateur.getId();

        if (utilisateurRepository.existsById(utilisateurId)) {
            if (Objects.equals(utilisateur.getAdresseLivraison(), utilisateur.getAdresseFacturation())) {
                utilisateur.setAdresseFacturation(null);
            }
            utilisateurRepository.save(utilisateur);
            return;
        }

        throw new NotFoundException(
                ErrorCode.NOTFOUNDEXCEPTION.CODE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT,
                ErrorCode.NOTFOUNDEXCEPTION.MESSAGE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT,
                new Object[]{utilisateurId}
        );
    }
}