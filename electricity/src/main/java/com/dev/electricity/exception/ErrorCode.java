package com.dev.electricity.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(404, "Uncategorized Error", HttpStatus.INTERNAL_SERVER_ERROR),
    TIER_EXISTED(1000, "Tier already existed",HttpStatus.BAD_REQUEST),

    ;


    ErrorCode(int code, String message,HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

}
