package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import lombok.Data;

import javax.naming.CommunicationException;

/**
 * 【异常】业务参数异常信息
 * Create by Muzi Li on 2019-11-26
 */
@Data
public class CommonMessageException extends CommunicationException {
    private Integer code;
    private String errMsg;

    public CommonMessageException(){}

    public CommonMessageException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
