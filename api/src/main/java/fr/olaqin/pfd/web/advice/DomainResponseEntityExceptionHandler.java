package fr.olaqin.pfd.web.advice;

import fr.olaqin.pfd.api.model.ApiError;
import fr.olaqin.pfd.exception.common.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice(basePackages = "fr.olaqin.pfd")
public class DomainResponseEntityExceptionHandler {

    private static final String EXCEPTION_MESSAGE = "une exception est levée:";
    private final GenericExceptionToApiError genericExceptionToApiError;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(final NotFoundException ex) {
        return handleAllExceptions(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAutorisedException.class)
    public ResponseEntity<ApiError> handleNotAllowedGuarantee(final NotAutorisedException ex) {
        return handleAllExceptions(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ApiError> handleInvalidError(final InvalidException ex) {
        return handleAllExceptions(ex, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<ApiError> handleConstraintException(final ConstraintException ex) {
        return handleAllExceptions(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FatalExcetpion.class)
    public ResponseEntity<ApiError> handleInternalError(final FatalExcetpion ex) {
        return handleAllExceptions(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiError> handleGenericException(final GenericException ex) {
        return handleAllExceptions(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiError> handleAllExceptions(final GenericException ex, final HttpStatus status) {
        log.error(EXCEPTION_MESSAGE, ex);
        return new ResponseEntity<>(genericExceptionToApiError.apply(ex), status);
    }
}
