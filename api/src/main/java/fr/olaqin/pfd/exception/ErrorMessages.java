package fr.olaqin.pfd.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ErrorMessages {

    private final List<String> keys = new ArrayList<>();

    private final Locale locale = LocaleContextHolder.getLocale();

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @Autowired
    public ErrorMessages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void init() {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        Enumeration<String> enumeration = bundle.getKeys();
        while (enumeration.hasMoreElements()) {
            keys.add(enumeration.nextElement());
        }
        accessor = new MessageSourceAccessor(messageSource, locale);
    }

    public String getMessage(String code, Object[] objects) {
        String message = code;
        if (verify(code)) {
            message = accessor.getMessage(code, objects);
        }
        return message;
    }

    public String getMessage(String code) {
        String message = code;
        if (verify(code)) {
            message = accessor.getMessage(code);
        }
        return message;
    }

    public String getMessage(String code, Locale locale) {
        String message = code;
        if (verify(code)) {
            message = accessor.getMessage(code, locale);
        }
        return message;
    }

    public String getMessage(ObjectError objectError) {
        return accessor.getMessage(objectError, locale);
    }

    private boolean verify(String code) {
        return keys.contains(code);
    }

}
