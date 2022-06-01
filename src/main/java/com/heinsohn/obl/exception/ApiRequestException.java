package com.heinsohn.obl.exception;

import java.io.Serializable;


public class ApiRequestException
        extends Exception
        implements Serializable {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
