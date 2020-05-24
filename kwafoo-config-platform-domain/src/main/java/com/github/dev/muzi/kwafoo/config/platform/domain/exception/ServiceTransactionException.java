package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import lombok.Data;

/**
 * 【异常】数据库事务异常
 * Create by Muzi Li on 2019-11-26
 */
@Data
public class ServiceTransactionException extends Exception {
    private static final String ERROR_MESSAGE = "业务参数异常";
    private static final Integer ERROR_CODE = 503;

    private Integer code;
    private String errMsg;

    public ServiceTransactionException() {
        super(ERROR_MESSAGE);
        this.code = ERROR_CODE;
        this.errMsg = ERROR_MESSAGE;
    }

    public ServiceTransactionException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
