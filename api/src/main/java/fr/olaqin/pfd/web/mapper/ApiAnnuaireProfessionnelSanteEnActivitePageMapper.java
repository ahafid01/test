package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiAnnuaireProfessionnelSanteEnActivitePage;
import fr.olaqin.pfd.api.model.ApiPage;
import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ApiAnnuaireProfessionnelSanteEnActivitePageMapper extends ApiPageMapper<ApiAnnuaireProfessionnelSanteEnActivitePage, AnnuaireProfessionnelSanteEnActivite> {

    private final ApiAnnuaireProfessionnelSanteEnActiviteMapper apiAnnuaireProfessionnelSanteEnActiviteMapper;

    @Override
    public ApiAnnuaireProfessionnelSanteEnActivitePage toApi(Page page) {
        final ApiPage apiPage = super.toApiPage(page);

        if (Objects.isNull(apiPage)) {
            return null;
        }

        final ApiAnnuaireProfessionnelSanteEnActivitePage response = new ApiAnnuaireProfessionnelSanteEnActivitePage();
        BeanUtils.copyProperties(apiPage, response);

        return response.content(apiAnnuaireProfessionnelSanteEnActiviteMapper.toApi(page.getContent()));
    }

}
