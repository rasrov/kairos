package com.rasrov.kairos.rest.handler;

import com.rasrov.kairos.domain.ErrorResponse;
import com.rasrov.kairos.domain.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BaseControllerExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<PriceResponse> handleInvalidDataException(final MethodArgumentNotValidException notValidException) {
        final List<String> errorMessages = new ArrayList<>();

        notValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();

            errorMessages.add(message);
        });

        final String message = String.join(", ", errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PriceResponse(null, null, null, null, null, new ErrorResponse(message)));
    }

}
