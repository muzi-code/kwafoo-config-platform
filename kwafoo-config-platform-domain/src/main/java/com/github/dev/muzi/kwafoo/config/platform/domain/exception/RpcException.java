package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import lombok.Data;



/**
 * 【异常】RPC业务异常信息
 * Create by Muzi Li on 2020-03-10
 */
@Data
public class RpcException extends RuntimeException {
    private Integer code;
    private String errMsg;

    public RpcException() {
        super(CommonStatus.RPC_ERROR.getMsg());
        this.code = CommonStatus.RPC_ERROR.getCode();
        this.errMsg = CommonStatus.RPC_ERROR.getMsg();
    }

    public RpcException(String errMsg) {
        super(errMsg);
        this.code = CommonStatus.RPC_ERROR.getCode();
        this.errMsg = errMsg;
    }
}
