package fr.olaqin.pfdbatch.mapper;

import fr.olaqin.pfdbatch.entity.AnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfdbatch.model.AnnuaireProfessionnelSanteEnActiviteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnnuaireProfessionnelSanteEnActiviteMapper {

    AnnuaireProfessionnelSanteEnActiviteMapper INSTANCE = Mappers.getMapper(AnnuaireProfessionnelSanteEnActiviteMapper.class);

    List<AnnuaireProfessionnelSanteEnActivite> map(List<AnnuaireProfessionnelSanteEnActiviteDto> dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nomExerciceSearch", ignore = true)
    @Mapping(target = "prenomExerciceSearch", ignore = true)
    AnnuaireProfessionnelSanteEnActivite map(AnnuaireProfessionnelSanteEnActiviteDto dto);


}
