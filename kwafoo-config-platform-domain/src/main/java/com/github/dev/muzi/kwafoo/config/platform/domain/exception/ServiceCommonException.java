package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatusConstant;
import lombok.Data;

import javax.naming.CommunicationException;

/**
 * 【异常】业务参数异常信息
 * Create by Muzi Li on 2019-11-26
 */
@Data
public class ServiceCommonException extends CommunicationException {
    private Integer code;
    private String errMsg;

    public ServiceCommonException(){}

    public ServiceCommonException(String errMsg) {
        super(errMsg);
        this.code = CommonStatusConstant.SERVICE_COMMON_ERROR.getCode();
        this.errMsg = errMsg;
    }

    public ServiceCommonException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
