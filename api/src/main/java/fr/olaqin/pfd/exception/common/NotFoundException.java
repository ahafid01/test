package fr.olaqin.pfd.exception.common;

import fr.olaqin.pfd.exception.ErrorDetails;

import java.util.List;

public class NotFoundException extends GenericException {

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String code, String message, Object[] args, List<ErrorDetails> details) {
        super(code, message, args, details);
    }

    public NotFoundException(String code, String message, Object[] args, ErrorDetails... details) {
        super(code, message, args, details);
    }

    public NotFoundException(String code, String message, List<ErrorDetails> details) {
        super(code, message, details);
    }

    public NotFoundException(String code, String message, ErrorDetails... details) {
        super(code, message, details);
    }

}
