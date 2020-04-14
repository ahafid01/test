package fr.olaqin.pfd.service.validator;

import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import fr.olaqin.pfd.exception.ErrorCode;
import fr.olaqin.pfd.exception.common.InvalidException;
import fr.olaqin.pfd.service.specification.AbstractLeafSpecification;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ValidatorProfessionnelSanteProspect extends AbstractLeafSpecification<ProfessionnelSanteProspect> {

    @Override
    public boolean isSatisfiedBy(ProfessionnelSanteProspect candidate) {
        if (Objects.isNull(candidate) || isBlank(candidate.getNom()) || isBlank(candidate.getPrenom()) || isBlank(candidate.getCodePostal())) {
            throw new InvalidException(
                    ErrorCode.INVALIDEXCEPTION.CODE_ERREUR_NOM_PRENOM_OBLIGATOIRES,
                    ErrorCode.INVALIDEXCEPTION.MESSAGE_ERREUR_NOM_PRENOM_OBLIGATOIRES,
                    null
            );
        }
        return true;
    }

}
