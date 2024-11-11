package com.dev.electricity.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(404, "Uncategorized Error"),
    TIER_EXISTED(1000, "Tier already existed"),

    ;


    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
