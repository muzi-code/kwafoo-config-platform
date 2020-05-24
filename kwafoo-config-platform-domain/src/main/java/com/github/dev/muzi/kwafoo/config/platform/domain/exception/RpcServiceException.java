package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatusConstant;
import lombok.Data;

import javax.naming.CommunicationException;


/**
 * 【异常】RPC业务异常信息
 * Create by Muzi Li on 2020-03-10
 */
@Data
public class RpcServiceException extends CommunicationException {
    private Integer code;
    private String errMsg;

    public RpcServiceException() {
    }

    public RpcServiceException(String errMsg) {
        super(errMsg);
        this.code = CommonStatusConstant.SERVICE_RPC_ERROR.getCode();
        this.errMsg = errMsg;
    }

    public RpcServiceException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
