package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import lombok.Data;

/**
 * 【异常】通用的VO参数校验异常
 * Create by Muzi Li on 2019-11-27
 */
@Data
public class RequestArgumentsException extends Exception {
    private Integer code;
    private String errMsg;

    public RequestArgumentsException(){}

    public RequestArgumentsException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
