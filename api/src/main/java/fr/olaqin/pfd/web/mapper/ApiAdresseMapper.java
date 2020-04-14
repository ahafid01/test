package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiAdresse;
import fr.olaqin.pfd.entity.Adresse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApiAdresseMapper extends ApiObjectMapper<ApiAdresse, Adresse> {
}
