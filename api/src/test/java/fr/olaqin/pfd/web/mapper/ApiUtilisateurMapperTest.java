package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiUtilisateur;
import fr.olaqin.pfd.entity.Utilisateur;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiUtilisateurMapperImpl.class, ApiAdresseMapperImpl.class})
class ApiUtilisateurMapperTest {

    @Autowired
    private ApiUtilisateurMapper apiUtilisateurMapper;

    @Test
    public void toModelNullTest() {
        assertNull(apiUtilisateurMapper.toModel((ApiUtilisateur) null));
    }

    @Test
    public void toModelTest() {

        final ApiUtilisateur apiUtilisateur = new ApiUtilisateur()
                .id("e1ddde6e-644d-11ea-9a82-abdf78990132")
                .nom("BRAS")
                .prenom("David")
                .email("david.bras@olaqin.fr")
                .telephonePrincipal("0600000000")
                .telephoneSecondaire("0100000000");

        final Object result = apiUtilisateurMapper.toModel(apiUtilisateur);

        assertNotNull(result);
        MatcherAssert.assertThat(result, instanceOf(Utilisateur.class));

        final Utilisateur utilisateur = (Utilisateur) result;

        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(utilisateur.getId()).isEqualTo(apiUtilisateur.getId());
        softly.assertThat(utilisateur.getNom()).isEqualTo(apiUtilisateur.getNom());
        softly.assertThat(utilisateur.getPrenom()).isEqualTo(apiUtilisateur.getPrenom());
        softly.assertThat(utilisateur.getEmail()).isEqualTo(apiUtilisateur.getEmail());
        softly.assertThat(utilisateur.getEmail()).isEqualTo(apiUtilisateur.getEmail());
        softly.assertThat(utilisateur.getEmail()).isEqualTo(apiUtilisateur.getEmail());
        softly.assertAll();

    }

    @Test
    public void toApilNullTest() {
        assertNull(apiUtilisateurMapper.toApi((Utilisateur) null));
    }

    @Test
    public void toApiTest() {

        final Utilisateur utilisateur = Utilisateur.builder()
                .id("e1ddde6e-644d-11ea-9a82-abdf78990132")
                .nom("BRAS")
                .prenom("David")
                .email("david.bras@olaqin.fr")
                .telephonePrincipal("0600000000")
                .telephoneSecondaire("0100000000")
                .build();

        final Object result = apiUtilisateurMapper.toApi(utilisateur);

        assertNotNull(result);
        MatcherAssert.assertThat(result, instanceOf(ApiUtilisateur.class));

        final ApiUtilisateur apiUtilisateur = (ApiUtilisateur) result;

        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(apiUtilisateur.getId()).isEqualTo(utilisateur.getId());
        softly.assertThat(apiUtilisateur.getNom()).isEqualTo(utilisateur.getNom());
        softly.assertThat(apiUtilisateur.getPrenom()).isEqualTo(utilisateur.getPrenom());
        softly.assertThat(apiUtilisateur.getEmail()).isEqualTo(utilisateur.getEmail());
        softly.assertThat(apiUtilisateur.getEmail()).isEqualTo(utilisateur.getEmail());
        softly.assertThat(apiUtilisateur.getEmail()).isEqualTo(utilisateur.getEmail());
        softly.assertAll();

    }
}