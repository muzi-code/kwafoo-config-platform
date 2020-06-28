package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import lombok.Data;

/**
 * 【异常】数据库事务异常
 * Create by Muzi Li on 2019-11-26
 */
@Data
public class MqException extends RuntimeException {

    private Integer code;
    private String errMsg;

    public MqException() {
        super(CommonStatus.MQ_ERROR.getMsg());
        this.code = CommonStatus.MQ_ERROR.getCode();
        this.errMsg = CommonStatus.MQ_ERROR.getMsg();
    }

    public MqException(String errMsg) {
        super(errMsg);
        this.code = CommonStatus.MQ_ERROR.getCode();
        this.errMsg = errMsg;
    }
}
