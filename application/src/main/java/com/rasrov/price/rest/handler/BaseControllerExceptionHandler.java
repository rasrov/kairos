package com.rasrov.price.rest.handler;

import com.rasrov.price.domain.ErrorResponse;
import com.rasrov.price.domain.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class BaseControllerExceptionHandler {

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<PriceResponse> handleMissingParameter(final MissingServletRequestParameterException requestParameterException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new PriceResponse(null, null, null, null, null, new ErrorResponse(requestParameterException.getMessage())));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<PriceResponse> handleInvalidDataException(final MethodArgumentTypeMismatchException typeMismatchException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new PriceResponse(null, null, null, null, null, new ErrorResponse(typeMismatchException.getMessage())));
    }

}
