package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import lombok.Data;

/**
 * 【异常】通用的VO参数校验异常
 * Create by Muzi Li on 2019-11-27
 */
@Data
public class ParameterException extends RuntimeException {
    private Integer code;
    private String errMsg;

    public ParameterException() {
        super(CommonStatus.PARAMETER_ERROR.getMsg());
        this.code = CommonStatus.PARAMETER_ERROR.getCode();
        this.errMsg = CommonStatus.PARAMETER_ERROR.getMsg();
    }

    public ParameterException(String errMsg) {
        super(errMsg);
        this.code = CommonStatus.PARAMETER_ERROR.getCode();
        this.errMsg = errMsg;
    }
}
