package fr.olaqin.pfd.exception.common;

import fr.olaqin.pfd.exception.ErrorDetails;

import java.util.List;

/**
 * Exception levé si une exception fata c'est produite telque l'indisponibilité des clients tierces
 */
public class FatalExcetpion extends GenericException {
    public FatalExcetpion(Throwable cause) {
        super(cause);
    }

    public FatalExcetpion(String code, String message, Object[] args, List<ErrorDetails> details) {
        super(code, message, args, details);
    }

    public FatalExcetpion(String code, String message, Object[] args, ErrorDetails... details) {
        super(code, message, args, details);
    }
}
