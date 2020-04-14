package fr.olaqin.pfd.exception.common;

import fr.olaqin.pfd.exception.ErrorDetails;

import java.util.List;

public class NotAutorisedException extends GenericException {
    public NotAutorisedException(Throwable cause) {
        super(cause);
    }

    public NotAutorisedException(String code, String message, Object[] args, List<ErrorDetails> details) {
        super(code, message, args, details);
    }

    public NotAutorisedException(String code, String message, Object[] args, ErrorDetails... details) {
        super(code, message, args, details);
    }
}
