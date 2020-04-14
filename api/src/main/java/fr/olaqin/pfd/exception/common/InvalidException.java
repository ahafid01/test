package fr.olaqin.pfd.exception.common;

import fr.olaqin.pfd.exception.ErrorDetails;

import java.util.List;

public class InvalidException extends GenericException {

    public InvalidException(Throwable cause) {
        super(cause);
    }

    public InvalidException(String code, String message, Object[] args, List<ErrorDetails> details) {
        super(code, message, args, details);
    }

    public InvalidException(String code, String message, Object[] args, ErrorDetails... details) {
        super(code, message, args, details);
    }
}
