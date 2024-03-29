package com.jjikmuk.sikdorak.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class SikdorakRuntimeException extends RuntimeException {

    private final ExceptionCodeAndMessages codeAndMessages = ExceptionCodeAndMessages.findByExceptionClass(getClass());

    protected SikdorakRuntimeException() {
    }

    protected SikdorakRuntimeException(Throwable cause) {
        super(cause);
    }


    public abstract HttpStatus getHttpStatus();
}
