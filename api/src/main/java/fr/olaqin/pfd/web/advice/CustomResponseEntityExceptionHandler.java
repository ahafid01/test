package fr.olaqin.pfd.web.advice;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import fr.olaqin.pfd.api.model.ApiError;
import fr.olaqin.pfd.exception.ErrorCode;
import fr.olaqin.pfd.exception.ErrorDetails;
import fr.olaqin.pfd.exception.common.GenericException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.OperationNotSupportedException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Personnalisation du {@code ResponseEntityExceptionHandler} de Spring pour gérer les exceptions avec un {@code body} qui correspond à nos
 * spécifications
 */
@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final GenericExceptionToApiError genericExceptionToApiError;

    /**
     * Contrôle de surface multi-champs
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleConstraintViolationException(final ConstraintViolationException ex) {
        final List<ErrorDetails> details = new ArrayList<>();

        for (final ConstraintViolation<?> violation : ((ConstraintViolationException) ex).getConstraintViolations()) {
            final NodeImpl currentLeafNode = ((PathImpl) violation.getPropertyPath()).getLeafNode();
            details.add(new ErrorDetails(currentLeafNode.getName(), ErrorCode.SURFACE.MESSAGE_ERREUR_DE_SURFACE_MULTI_CHAMPS, violation
                    .getInvalidValue(), violation.getMessage(), null,
                    new Object[]{violation.getInvalidValue(), currentLeafNode.getName()}));
        }

        return handleAllExceptions(new GenericException(ErrorCode.SURFACE.CODE_ERREUR_DE_SURFACE_MULTI_CHAMPS,
                ErrorCode.SURFACE.MESSAGE_GLOBAL_ERREUR_DE_SURFACE_MULTI_CHAMPS, details), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TransactionSystemException.class})
    public ResponseEntity<ApiError> handleTransactionSystemException(final TransactionSystemException ex) {
        final Throwable cause = ex.getRootCause();
        final List<ErrorDetails> details = new ArrayList<>();

        if (cause instanceof ConstraintViolationException) {
            final Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            constraintViolations.forEach(violation -> {
                final String fieldName = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();

                details.add(new ErrorDetails().setChamp(fieldName).setMessage(ErrorCode.DEFINITION.MESSAGE_ERREUR_INVALID_DEFINITION)
                        .setValeur(violation.getMessage()).setArgs(new Object[]{fieldName}));
            });
        }

        return handleAllExceptions(new GenericException(ErrorCode.DEFINITION.CODE_ERREUR_INVALID_DEFINITION,
                ErrorCode.DEFINITION.MESSAGE_GLOBAL_ERREUR_INVALID_DEFINITION, details), HttpStatus.BAD_REQUEST);
    }

    /**
     * Contrôle de surface multi-champs
     *
     * @param ex
     * @return
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers,
                                                               final HttpStatus status, final WebRequest request) {
        final BindingResult bindingResult = ex.getBindingResult();

        if (bindingResult.hasErrors()) {
            final List<ErrorDetails> details = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> {
                if (e instanceof FieldError) {
                    final FieldError fe = (FieldError) e;
                    details.add(
                            new ErrorDetails(fe.getField(), null, fe.getRejectedValue(), fe.getDefaultMessage(), fe.getObjectName(),
                                    null)
                    );
                }
            });

            final ApiError body = buildError(new GenericException(ErrorCode.DEFINITION.CODE_ERREUR_INVALID_DEFINITION,
                    ErrorCode.DEFINITION.MESSAGE_GLOBAL_ERREUR_INVALID_DEFINITION, details));
            return handleExceptionInternal(ex, body, headers, status, request);
        }

        return this.handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Contrôle de surface des enumérateurs
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {InvalidDefinitionException.class})
    public ResponseEntity<ApiError> handleInvalidDefinitionException(final InvalidDefinitionException ex) {
        final List<ErrorDetails> details = new ArrayList<>();
        ex.getPath().forEach(r -> details.add(new ErrorDetails().setChamp(r.getFieldName())
                .setMessage(ErrorCode.DEFINITION.MESSAGE_ERREUR_INVALID_DEFINITION).setArgs(new Object[]{r.getFieldName()})));

        return handleAllExceptions(new GenericException(ErrorCode.DEFINITION.CODE_ERREUR_INVALID_DEFINITION,
                ErrorCode.DEFINITION.MESSAGE_GLOBAL_ERREUR_INVALID_DEFINITION, details), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    public ResponseEntity<String> handlePrefaceValiditePretException(final InvalidFormatException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers,
                                                               final HttpStatus status, final WebRequest request) {
        if (ex.getCause() instanceof InvalidFormatException) {
            final InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();

            final List<ErrorDetails> details = invalidFormatException.getPath().stream()
                    .map(reference -> new ErrorDetails().setChamp(reference.getFieldName())
                            .setMessage(ErrorCode.DEFINITION.MESSAGE_ERREUR_INVALID_DEFINITION)
                            .setValeur(invalidFormatException.getValue())
                            .setArgs(new Object[]{reference.getFieldName()})).collect(Collectors.toList());

            final ApiError body = buildError(new GenericException(ErrorCode.DEFINITION.CODE_ERREUR_INVALID_DEFINITION,
                    ErrorCode.DEFINITION.MESSAGE_GLOBAL_ERREUR_INVALID_DEFINITION, details));

            return handleExceptionInternal(ex, body, headers, status, request);

        } else if (ex.getCause() instanceof MismatchedInputException) {
            final MismatchedInputException mismatchedInputException = (MismatchedInputException) ex.getCause();

            final List<ErrorDetails> details =
                    mismatchedInputException.getPath().stream().map(reference -> new ErrorDetails().setChamp(reference.getFieldName())
                            .setMessage(ErrorCode.DEFINITION.MESSAGE_ERREUR_INVALID_DEFINITION).setArgs(new Object[]{reference.getFieldName()}))
                            .collect(Collectors.toList());

            final ApiError body = buildError(new GenericException(ErrorCode.DEFINITION.CODE_ERREUR_INVALID_DEFINITION,
                    ErrorCode.DEFINITION.MESSAGE_GLOBAL_ERREUR_INVALID_DEFINITION, details));

            return handleExceptionInternal(ex, body, headers, status, request);
        }
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    /**
     * Utiliser pour les enums générés
     * Sinon, on utilise la propriété useNullForUnknownEnumValue
     *
     * @param ex HttpMessageConversionException
     * @return ResponseEntity
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ApiError> handleGenericException(final HttpMessageConversionException ex) {
        final Throwable cause = ex.getCause();
        final List<ErrorDetails> details = new ArrayList<>();
        if (cause instanceof ValueInstantiationException) {
            final Throwable throwable = cause.getCause();
            if (throwable instanceof IllegalArgumentException && throwable.getMessage().contains("Unexpected value '")) {
                final ValueInstantiationException exception = (ValueInstantiationException) cause;
                exception.getPath().stream()
                        .map(reference ->
                                new ErrorDetails()
                                        .setChamp(reference.getFieldName())
                                        .setValeur(cause.getCause()
                                                .getMessage()
                                                .replace("Unexpected value '", "")
                                                .replace("'", "")
                                        )
                        ).collect(Collectors.toCollection(() -> details));
                final GenericException genericException = new GenericException(
                        ErrorCode.SURFACE.CODE_ERREUR_DE_SURFACE_MULTI_CHAMPS,
                        ErrorCode.SURFACE.MESSAGE_GLOBAL_ERREUR_DE_SURFACE_MULTI_CHAMPS,
                        details);
                return handleAllExceptions(genericException, HttpStatus.BAD_REQUEST);
            }
        }
        log.error("Une exception de type HttpMessageConversionException est non traitée");
        return handleAnotherException(ex);
    }

    @ExceptionHandler({NullPointerException.class, OperationNotSupportedException.class, AmazonDynamoDBException.class})
    public ResponseEntity<ApiError> handleAnotherException(final Exception ex) {
        log.error(ErrorCode.ANOTHEREXCEPTION.CODE_ERREUR_INTERNE, ex);
        final GenericException exception = new GenericException(
                ErrorCode.ANOTHEREXCEPTION.CODE_ERREUR_INTERNE,
                ErrorCode.ANOTHEREXCEPTION.MESSAGE_ERREUR_INTERNE
        );
        return new ResponseEntity<>(genericExceptionToApiError.apply(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ApiError buildError(final GenericException ex) {
        return this.genericExceptionToApiError.apply(ex);
    }

    private ResponseEntity<ApiError> handleAllExceptions(final GenericException ex, final HttpStatus status) {
        return new ResponseEntity<>(buildError(ex), status);
    }

}
