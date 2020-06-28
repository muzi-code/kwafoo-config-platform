package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import lombok.Data;


/**
 * 【异常】业务参数异常信息
 * Create by Muzi Li on 2019-11-26
 */
@Data
public class RedisException extends RuntimeException {
    private Integer code;
    private String errMsg;


    public RedisException() {
        super(CommonStatus.REDIS_ERROR.getMsg());
        this.code = CommonStatus.REDIS_ERROR.getCode();
        this.errMsg = CommonStatus.REDIS_ERROR.getMsg();
    }

    public RedisException(String errMsg) {
        super(errMsg);
        this.code = CommonStatus.REDIS_ERROR.getCode();
        this.errMsg = errMsg;
    }
}
