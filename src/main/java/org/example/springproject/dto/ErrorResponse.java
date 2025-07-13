package org.example.springproject.dto;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldError> fieldErrorList) {

    public record FieldError(String field, Object rejectedValue, String reason) {}
}
