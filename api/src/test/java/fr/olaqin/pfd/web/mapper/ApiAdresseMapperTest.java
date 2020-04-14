package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiAdresse;
import fr.olaqin.pfd.entity.Adresse;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApiAdresseMapperTest {

    private static final ApiAdresseMapper INSTANCE = Mappers.getMapper(ApiAdresseMapper.class);

    @Test
    public void toModelNullTest() {
        assertNull(INSTANCE.toModel((ApiAdresse) null));
    }

    @Test
    public void toModelTest() {
        final ApiAdresse apiAdresse = new ApiAdresse()
                .nomComplet("Mr David BRAS")
                .ligne1("Ingenico Group S.A.")
                .ligne2("28/32 boulevard de Grenelle")
                .codePostal("75015")
                .ville("PARIS");

        final Object result = INSTANCE.toModel(apiAdresse);

        assertNotNull(result);
        MatcherAssert.assertThat(result, instanceOf(Adresse.class));

        final Adresse adresse = (Adresse) result;

        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(adresse.getNomComplet()).isEqualTo(apiAdresse.getNomComplet());
        softly.assertThat(adresse.getLigne1()).isEqualTo(apiAdresse.getLigne1());
        softly.assertThat(adresse.getLigne2()).isEqualTo(apiAdresse.getLigne2());
        softly.assertThat(adresse.getCodePostal()).isEqualTo(apiAdresse.getCodePostal());
        softly.assertThat(adresse.getVille()).isEqualTo(apiAdresse.getVille());
        softly.assertAll();

    }

    @Test
    public void toApiNullTest() {
        assertNull(INSTANCE.toApi((Adresse) null));
    }

    @Test
    public void toApiTest() {
        final Adresse adresse = Adresse.builder()
                .nomComplet("Mr David BRAS")
                .ligne1("Ingenico Group S.A.")
                .ligne2("28/32 boulevard de Grenelle")
                .codePostal("75015")
                .ville("PARIS")
                .build();

        final Object result = INSTANCE.toApi(adresse);

        assertNotNull(result);
        MatcherAssert.assertThat(result, instanceOf(ApiAdresse.class));

        final ApiAdresse apiAdresse = (ApiAdresse) result;

        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(apiAdresse.getNomComplet()).isEqualTo(adresse.getNomComplet());
        softly.assertThat(apiAdresse.getLigne1()).isEqualTo(adresse.getLigne1());
        softly.assertThat(apiAdresse.getLigne2()).isEqualTo(adresse.getLigne2());
        softly.assertThat(apiAdresse.getCodePostal()).isEqualTo(adresse.getCodePostal());
        softly.assertThat(apiAdresse.getVille()).isEqualTo(adresse.getVille());
        softly.assertAll();

    }

}