package fr.olaqin.pfd.exception.common;

import fr.olaqin.pfd.exception.ErrorDetails;

import java.util.List;

public class ConstraintException extends GenericException {
    public ConstraintException(Throwable cause) {
        super(cause);
    }

    public ConstraintException(String code, String message, Object[] args, List<ErrorDetails> details) {
        super(code, message, args, details);
    }

    public ConstraintException(String code, String message, Object[] args, ErrorDetails... details) {
        super(code, message, args, details);
    }
}
