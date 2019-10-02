package com.academy.java.spring.dto;

public class ErrorDTO {

    private final String message;
    private final String traceId;

    public String getMessage() {
        return message;
    }

    public String getTraceId() {
        return traceId;
    }

    private ErrorDTO(String traceId, String message) {
        this.message = message;
        this.traceId = traceId;
    }

    public static class Builder {

        public static ErrorDTO newError(String traceId, String message) {
            return new ErrorDTO(traceId, message);
        }
    }
}
