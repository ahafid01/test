package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiAnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfd.api.model.ApiAnnuaireProfessionnelSanteEnActivitePage;
import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiAnnuaireProfessionnelSanteEnActivitePageMapper.class, ApiAnnuaireProfessionnelSanteEnActiviteMapperImpl.class})
class ApiAnnuaireProfessionnelSanteEnActivitePageMapperTest {

    @Autowired
    private ApiAnnuaireProfessionnelSanteEnActivitePageMapper mapper;

    @Test
    void toApiNullTest() {
        assertNull(mapper.toApi((Page) null));
    }

    @Test
    void toApiTest() {
        AnnuaireProfessionnelSanteEnActivite annuaireProfessionnelSanteEnActivite = AnnuaireProfessionnelSanteEnActivite.builder()
                .codeProfession("60")
                .build();

        List<AnnuaireProfessionnelSanteEnActivite> content = asList(annuaireProfessionnelSanteEnActivite);
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 1;
            }

            @Override
            public int getPageSize() {
                return 5;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        long total = 5;
        Page page = new PageImpl(content, pageable, total);

        Object result = mapper.toApi(page);

        assertNotNull(result);
        assertThat(result, instanceOf(ApiAnnuaireProfessionnelSanteEnActivitePage.class));

        ApiAnnuaireProfessionnelSanteEnActivitePage apiAnnuaireProfessionnelSanteEnActivitePage = (ApiAnnuaireProfessionnelSanteEnActivitePage) result;

        List<ApiAnnuaireProfessionnelSanteEnActivite> apiAnnuaireProfessionnelSanteEnActivites = apiAnnuaireProfessionnelSanteEnActivitePage.getContent();

        assertThat(apiAnnuaireProfessionnelSanteEnActivites, hasSize(1));
        assertThat(apiAnnuaireProfessionnelSanteEnActivites, containsInAnyOrder(
                hasProperty("codeProfession", is(annuaireProfessionnelSanteEnActivite.getCodeProfession()))
        ));
    }
}