package fr.olaqin.pfd.web.advice;

import fr.olaqin.pfd.api.model.ApiError;
import fr.olaqin.pfd.api.model.ApiErrorDetails;
import fr.olaqin.pfd.exception.ErrorMessages;
import fr.olaqin.pfd.exception.common.GenericException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class GenericExceptionToApiError {

    private final ErrorMessages errorMessages;

    public ApiError apply(final GenericException ex) {

        final ApiError apiError = new ApiError()
                .horodatage(LocalDateTime.now())
                .code(errorMessages.getMessage(ex.getCode()))
                .message(Objects.nonNull(ex.getMessage()) ? errorMessages.getMessage(ex.getMessage(), ex.getArgs()) : null);

        ex.getDetails()
                .stream()
                .map(detail -> {
                    String champ = detail.getChamp();
                    String valeur = Objects.nonNull(detail.getValeur()) ? String.valueOf(detail.getValeur()) : null;
                    String champAssocie = detail.getChampAssocie();
                    String description = detail.getDescription();
                    String message = Objects.nonNull(detail.getMessage()) ? errorMessages.getMessage(detail.getMessage(), detail.getArgs()) : null;

                    return new ApiErrorDetails().champ(champ).valeur(valeur).message(message).description(description).champAssocie(champAssocie);
                }).forEach(apiError::addDetailsItem);

        return apiError;
    }
}
