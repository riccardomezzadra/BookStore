package com.model.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class HTTPResponseException extends RuntimeException {
    private String errorCode;
    private String message;
    private HttpStatus httpStatus;
    private Object data;

    public HTTPResponseException(String message, HttpStatus httpStatus) {
        super(message);
        this.setErrorCode("-1");
        this.setMessage(message);
        this.setHttpStatus(httpStatus);
        this.setData("No further information");
    }

    public HTTPResponseException(String message, HttpStatus httpStatus, Object data) {
        super(message);
        this.setErrorCode("-1");
        this.setMessage(message);
        this.setHttpStatus(httpStatus);
        this.setData(data);
    }

    public HTTPResponseException(String errorCode, String message, HttpStatus httpStatus, Object data) {
        super(message);
        this.setErrorCode(errorCode);
        this.setMessage(message);
        this.setHttpStatus(httpStatus);
        this.setData(data);
    }


}
