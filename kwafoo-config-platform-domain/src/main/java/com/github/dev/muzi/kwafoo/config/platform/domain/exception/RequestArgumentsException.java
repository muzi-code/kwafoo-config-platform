package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import lombok.Data;

/**
 * 【异常】通用的VO参数校验异常
 * Create by Muzi Li on 2019-11-27
 */
@Data
public class RequestArgumentsException extends RuntimeException {
    private Integer code;
    private String errMsg;

    public RequestArgumentsException() {
        super();
        this.code = CommonStatus.ARGUMENTS.getCode();
        this.errMsg = CommonStatus.ARGUMENTS.getMsg();
    }

    public RequestArgumentsException(String errMsg) {
        super(errMsg);
        this.code = CommonStatus.ARGUMENTS.getCode();
        this.errMsg = errMsg;
    }
}
