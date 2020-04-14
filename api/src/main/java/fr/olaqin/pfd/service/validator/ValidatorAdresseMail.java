package fr.olaqin.pfd.service.validator;

import fr.olaqin.pfd.exception.ErrorCode;
import fr.olaqin.pfd.exception.common.InvalidException;
import fr.olaqin.pfd.service.specification.AbstractLeafSpecification;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidatorAdresseMail extends AbstractLeafSpecification<String> {
    @Override
    public boolean isSatisfiedBy(String candidate) {
        try {
            final InternetAddress internetAddress = new InternetAddress(candidate);
            internetAddress.validate();
        } catch (AddressException e) {
            throw new InvalidException(
                    ErrorCode.INVALIDEXCEPTION.CODE_ERREUR_EMAIL,
                    ErrorCode.INVALIDEXCEPTION.MESSAGE_ERREUR_EMAIL,
                    new Object[]{candidate}
            );
        }
        return true;
    }
}
