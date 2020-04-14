package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiProfessionnelSanteProspectForm;
import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApiProfessionnelSanteProspectFormMapper extends ApiObjectMapper<ApiProfessionnelSanteProspectForm, ProfessionnelSanteProspect> {
}
