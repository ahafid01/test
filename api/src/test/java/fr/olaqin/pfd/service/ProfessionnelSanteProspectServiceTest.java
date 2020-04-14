package fr.olaqin.pfd.service;

import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import fr.olaqin.pfd.exception.common.InvalidException;
import fr.olaqin.pfd.repository.AnnuaireProfessionnelSanteRepository;
import fr.olaqin.pfd.repository.ProfessionnelSanteProspectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static fr.olaqin.pfd.enums.RaisonQualif.*;
import static fr.olaqin.pfd.enums.ResultatQualif.NOK;
import static fr.olaqin.pfd.enums.ResultatQualif.OK;
import static fr.olaqin.pfd.exception.ErrorCode.INVALIDEXCEPTION.MESSAGE_ERREUR_NOM_PRENOM_OBLIGATOIRES;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfessionnelSanteProspectServiceTest {


    @Captor
    ArgumentCaptor<ProfessionnelSanteProspect> professionnelSanteArgumentCaptor;
    @InjectMocks
    ProfessionnelSanteProspectService sut;
    @Mock
    private ProfessionnelSanteProspectRepository professionnelSanteProspectRepository;
    @Mock
    private AnnuaireProfessionnelSanteRepository annuaireRepository;
    @Mock
    private SESEmailService sesEmailService;

    @Nested
    @DisplayName("CheckAndSave")
    class CheckAndSave {

        @Test
        @DisplayName("doit créer un ProfessionnelSante avec resultat = Ok et raison = utilisateur trouvé")
        void should_save_professionnel_sante_with_resultat_ok_and_utilisateur_trouve() {
            String tdId1 = randomUUID().toString();
            String tdId2 = randomUUID().toString();
            AnnuaireProfessionnelSanteEnActivite ps1 = AnnuaireProfessionnelSanteEnActivite.builder().id(tdId1).nomExercice("haf1").prenomExercice("abder1").codePostalCoordStructure("12345").build();
            when(annuaireRepository
                    .findAllByNomExerciceSearchAndPrenomExerciceSearch("haf1", "abder1"))
                    .thenReturn(asList(ps1));

            sut.checkAndSave(ProfessionnelSanteProspect.builder().nom("haf1").prenom("abder1").codePostal("12345").build());

            verify(professionnelSanteProspectRepository)
                    .save(professionnelSanteArgumentCaptor.capture());
            ProfessionnelSanteProspect savedPS = professionnelSanteArgumentCaptor.getValue();
            assertSoftly(softly -> {
                softly.assertThat(savedPS.getId()).isNull();
                softly.assertThat(savedPS.getNom()).isEqualTo(ps1.getNomExercice());
                softly.assertThat(savedPS.getResultatQualif()).isEqualTo(OK);
                softly.assertThat(savedPS.getRaisonQualif()).isEqualTo(UTILISATEUR_TROUVE);
            });
        }

        @Test
        @DisplayName("doit créer un ProfessionnelSante avec resultat = Ok et raison = utilisateur non trouvé")
        void should_save_professionnel_sante_with_resultat_nok_and_non_trouve() {
            when(annuaireRepository
                    .findAllByNomExerciceSearchAndPrenomExerciceSearch("haf1", "abder1"))
                    .thenReturn(emptyList());

            sut.checkAndSave(ProfessionnelSanteProspect.builder().nom("haf1").prenom("abder1").codePostal("12345").build());

            verify(professionnelSanteProspectRepository)
                    .save(professionnelSanteArgumentCaptor.capture());
            ProfessionnelSanteProspect savedPS = professionnelSanteArgumentCaptor.getValue();
            assertSoftly(softly -> {
                softly.assertThat(savedPS.getId()).isNull();
                softly.assertThat(savedPS.getNom()).isEqualTo("haf1");
                softly.assertThat(savedPS.getResultatQualif()).isEqualTo(NOK);
                softly.assertThat(savedPS.getRaisonQualif()).isEqualTo(NON_TROUVE);
            });
        }

        @Test
        @DisplayName("doit créer un ProfessionnelSante avec resultat = Ok et raison = Aucune ville retournée")
        void should_save_professionnel_sante_with_resultat_ok_and_aucune_ville_retournee() {
            String tdId1 = randomUUID().toString();
            String tdId2 = randomUUID().toString();
            AnnuaireProfessionnelSanteEnActivite ps1 = AnnuaireProfessionnelSanteEnActivite.builder().id(tdId1).nomExercice("haf1").prenomExercice("abder1").build();
            when(annuaireRepository
                    .findAllByNomExerciceSearchAndPrenomExerciceSearch("haf1", "abder1"))
                    .thenReturn(asList(ps1));

            sut.checkAndSave(ProfessionnelSanteProspect.builder().nom("haf1").prenom("abder1").codePostal("12345").build());

            verify(professionnelSanteProspectRepository)
                    .save(professionnelSanteArgumentCaptor.capture());
            ProfessionnelSanteProspect savedPS = professionnelSanteArgumentCaptor.getValue();
            assertSoftly(softly -> {
                softly.assertThat(savedPS.getId()).isNull();
                softly.assertThat(savedPS.getNom()).isEqualTo(ps1.getNomExercice());
                softly.assertThat(savedPS.getResultatQualif()).isEqualTo(OK);
                softly.assertThat(savedPS.getRaisonQualif()).isEqualTo(AUCUNE_VILLE_RETOURNEE);
            });
        }

        @Test
        @DisplayName("doit créer un ProfessionnelSante avec resultat = NOk et raison = Localisation légèrement différente")
        void should_save_professionnel_sante_with_resultat_ok_and_localisation_legerement_differente() {
            String tdId1 = randomUUID().toString();
            AnnuaireProfessionnelSanteEnActivite ps1 = AnnuaireProfessionnelSanteEnActivite.builder().id(tdId1).nomExercice("haf1").prenomExercice("abder1").codePostalCoordStructure("12000").build();
            when(annuaireRepository
                    .findAllByNomExerciceSearchAndPrenomExerciceSearch("haf1", "abder1"))
                    .thenReturn(asList(ps1));

            sut.checkAndSave(ProfessionnelSanteProspect.builder().nom("haf1").prenom("abder1").codePostal("12345").build());

            verify(professionnelSanteProspectRepository)
                    .save(professionnelSanteArgumentCaptor.capture());
            ProfessionnelSanteProspect savedPS = professionnelSanteArgumentCaptor.getValue();
            assertSoftly(softly -> {
                softly.assertThat(savedPS.getId()).isNull();
                softly.assertThat(savedPS.getNom()).isEqualTo(ps1.getNomExercice());
                softly.assertThat(savedPS.getResultatQualif()).isEqualTo(NOK);
                softly.assertThat(savedPS.getRaisonQualif()).isEqualTo(LOCALISATION_LEGEREMENT_DIFFERENTE);
            });
        }

        @Test
        @DisplayName("doit créer un ProfessionnelSante avec resultat = NOk et raison = Localisation différente")
        void should_save_professionnel_sante_with_resultat_ok_and_localisation_differente() {
            String tdId1 = randomUUID().toString();
            AnnuaireProfessionnelSanteEnActivite ps1 = AnnuaireProfessionnelSanteEnActivite.builder().id(tdId1).nomExercice("haf1").prenomExercice("abder1").codePostalCoordStructure("75000").build();
            when(annuaireRepository
                    .findAllByNomExerciceSearchAndPrenomExerciceSearch("haf1", "abder1"))
                    .thenReturn(asList(ps1));

            sut.checkAndSave(ProfessionnelSanteProspect.builder().nom("haf1").prenom("abder1").codePostal("12345").build());

            verify(professionnelSanteProspectRepository)
                    .save(professionnelSanteArgumentCaptor.capture());
            ProfessionnelSanteProspect savedPS = professionnelSanteArgumentCaptor.getValue();
            assertSoftly(softly -> {
                softly.assertThat(savedPS.getId()).isNull();
                softly.assertThat(savedPS.getNom()).isEqualTo(ps1.getNomExercice());
                softly.assertThat(savedPS.getResultatQualif()).isEqualTo(NOK);
                softly.assertThat(savedPS.getRaisonQualif()).isEqualTo(LOCALISATION_DIFFERENTE);
            });
        }

        @Test
        @DisplayName("doit lancer une exception si l'un des champs suivants est absent dans la requete : nom, prenom ou code postal ")
        void should_throws_exception_if_missing_nom_or_prenom_or_codepostal() {
            String tdId1 = randomUUID().toString();
            AnnuaireProfessionnelSanteEnActivite ps1 = AnnuaireProfessionnelSanteEnActivite.builder().id(tdId1).nomExercice("haf1").prenomExercice("abder1").codePostalCoordStructure("75000").build();

            assertThatThrownBy(() -> sut.checkAndSave(ProfessionnelSanteProspect.builder().prenom("abder1").codePostal("12345").build()))
                    .isInstanceOf(InvalidException.class)
                    .hasMessage(MESSAGE_ERREUR_NOM_PRENOM_OBLIGATOIRES);

            assertThatThrownBy(() -> sut.checkAndSave(ProfessionnelSanteProspect.builder().nom("haf1").codePostal("12345").build()))
                    .isInstanceOf(InvalidException.class)
                    .hasMessage(MESSAGE_ERREUR_NOM_PRENOM_OBLIGATOIRES);

            assertThatThrownBy(() -> sut.checkAndSave(ProfessionnelSanteProspect.builder().nom("haf1").prenom("abder1").build()))
                    .isInstanceOf(InvalidException.class)
                    .hasMessage(MESSAGE_ERREUR_NOM_PRENOM_OBLIGATOIRES);
        }

        @Test
        @DisplayName("doit retourner le bon ProfessionnelSante par son id")
        void should_return_PS_by_id() {
            String tdId1 = randomUUID().toString();
            Optional<ProfessionnelSanteProspect> ps1 = Optional.of(ProfessionnelSanteProspect.builder().id(tdId1).nom("haf1").prenom("abder1").codePostal("12000").build());
            when(professionnelSanteProspectRepository
                    .findById(any()))
                    .thenReturn(ps1);

            ProfessionnelSanteProspect professionnelSanteProspect = sut.get(tdId1);

            assertSoftly(softly -> {
                softly.assertThat(professionnelSanteProspect.getId()).isNotNull();
                softly.assertThat(professionnelSanteProspect.getNom()).isEqualTo(ps1.get().getNom());
                softly.assertThat(professionnelSanteProspect.getPrenom()).isEqualTo(ps1.get().getPrenom());
            });
        }

        @Test
        @DisplayName("doit retourner tous les ProfessionnelSante")
        void should_return_All_PS() {
            String tdId1 = randomUUID().toString();
            ProfessionnelSanteProspect ps1 = ProfessionnelSanteProspect.builder().id(tdId1).nom("haf1").prenom("abder1").codePostal("01000").build();
            ProfessionnelSanteProspect ps2 = ProfessionnelSanteProspect.builder().id(tdId1).nom("ha2").prenom("abder2").codePostal("02000").build();
            ProfessionnelSanteProspect ps3 = ProfessionnelSanteProspect.builder().id(tdId1).nom("haf3").prenom("abder3").codePostal("03000").build();
            when(professionnelSanteProspectRepository
                    .findAll())
                    .thenReturn(asList(ps1, ps2, ps3));
            ;

            List<ProfessionnelSanteProspect> list = sut.getAll();

            assertThat(list)
                    .isNotNull()
                    .hasSize(3)
                    .extracting(ProfessionnelSanteProspect::getNom, ProfessionnelSanteProspect::getPrenom, ProfessionnelSanteProspect::getCodePostal)
                    .contains(
                            tuple(ps1.getNom(), ps1.getPrenom(), ps1.getCodePostal()),
                            tuple(ps2.getNom(), ps2.getPrenom(), ps2.getCodePostal()),
                            tuple(ps3.getNom(), ps3.getPrenom(), ps3.getCodePostal())
                    );
        }

    }
}