package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiUtilisateur;
import fr.olaqin.pfd.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = ApiAdresseMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApiUtilisateurMapper extends ApiObjectMapper<ApiUtilisateur, Utilisateur> {
}
