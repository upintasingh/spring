package org.example.springproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springproject.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex,HttpServletRequest request) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildBody(HttpStatus.BAD_REQUEST, "not valid", ex.getMessage(), request.getRequestURI(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        ErrorResponse body = buildBody(HttpStatus.INTERNAL_SERVER_ERROR,"exception occurred",
                e.getMessage(),request.getRequestURI(), null);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private ErrorResponse buildBody(HttpStatus status,
                                    String error,
                                    String message,
                                    String path,
                                    List<ErrorResponse.FieldError> fields) {
        return new ErrorResponse(Instant.now(), status.value(), error, message,path,
                fields == null || fields.isEmpty() ? null : fields);
    }
}
