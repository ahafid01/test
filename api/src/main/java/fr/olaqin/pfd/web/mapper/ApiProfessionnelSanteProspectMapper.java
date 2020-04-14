package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiProfessionnelSanteProspect;
import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApiProfessionnelSanteProspectMapper extends ApiObjectMapper<ApiProfessionnelSanteProspect, ProfessionnelSanteProspect> {

    @Mapping(target = "dateQualif", source = "createdDate")
    @Override
    ApiProfessionnelSanteProspect toApi(ProfessionnelSanteProspect professionnelSanteProspect);
}
