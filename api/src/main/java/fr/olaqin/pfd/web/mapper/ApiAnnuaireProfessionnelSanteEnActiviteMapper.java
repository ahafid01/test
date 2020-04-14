package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiAnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApiAnnuaireProfessionnelSanteEnActiviteMapper extends ApiObjectMapper<ApiAnnuaireProfessionnelSanteEnActivite, AnnuaireProfessionnelSanteEnActivite> {
}
