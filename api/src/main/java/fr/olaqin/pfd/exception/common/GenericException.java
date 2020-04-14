package fr.olaqin.pfd.exception.common;

import fr.olaqin.pfd.exception.ErrorCode;
import fr.olaqin.pfd.exception.ErrorDetails;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericException extends RuntimeException {

    private static final long serialVersionUID = 3600522611127834082L;
    private final List<ErrorDetails> details;
    private final String code;
    private final String message;
    private Object[] args;

    public GenericException(Throwable cause) {
        super(cause);
        this.code = ErrorCode.DEFINITION.CODE_ERREUR_INVALID_DEFINITION;
        this.message = cause.getMessage();
        this.details = Collections.emptyList();
    }

    public GenericException(String code, String message, Object[] args, List<ErrorDetails> details) {
        this.code = code;
        this.message = message;
        this.details = ListUtils.defaultIfNull(details, Collections.emptyList());
        this.args = args;
    }

    public GenericException(String code, String message, Object[] args, ErrorDetails... details) {
        this.code = code;
        this.message = message;
        if (ArrayUtils.isNotEmpty(details)) {
            this.details = new ArrayList<>(ArrayUtils.getLength(details));
            CollectionUtils.addAll(this.details, details);
        } else {
            this.details = Collections.emptyList();
        }
        this.args = args;
    }

    public GenericException(String code, String message, List<ErrorDetails> details) {
        this.code = code;
        this.message = message;
        this.details = ListUtils.defaultIfNull(details, Collections.emptyList());
    }

    public GenericException(String code, String message, ErrorDetails... details) {
        this.code = code;
        this.message = message;
        if (ArrayUtils.isNotEmpty(details)) {
            this.details = new ArrayList<>(ArrayUtils.getLength(details));
            CollectionUtils.addAll(this.details, details);
        } else {
            this.details = Collections.emptyList();
        }
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<ErrorDetails> getDetails() {
        return ListUtils.emptyIfNull(details);
    }

    public Object[] getArgs() {
        return args;
    }
}
