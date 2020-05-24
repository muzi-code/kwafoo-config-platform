package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import lombok.Data;

/**
 * 【异常】控制器异常信息
 * Create by Muzi Li on 2019-11-26
 */
@Data
public class ControllerCommonException extends Exception{
    private Integer code;
    private String errMsg;

    public ControllerCommonException(){}

    public ControllerCommonException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
