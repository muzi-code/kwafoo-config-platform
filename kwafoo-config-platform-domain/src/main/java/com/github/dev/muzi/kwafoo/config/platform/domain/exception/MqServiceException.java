package com.github.dev.muzi.kwafoo.config.platform.domain.exception;


import lombok.Data;

import javax.naming.CommunicationException;


/**
 * 【异常】MQ业务异常信息
 * Create by Muzi Li on 2020-03-10
 */
@Data
public class MqServiceException extends CommunicationException {

    private Integer code;
    private String errMsg;

    public MqServiceException(){}

    public MqServiceException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

    public MqServiceException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }
}
