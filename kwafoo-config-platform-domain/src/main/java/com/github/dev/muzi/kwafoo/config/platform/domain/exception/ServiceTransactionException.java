package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import lombok.Data;

/**
 * 【异常】数据库事务异常
 * Create by Muzi Li on 2019-11-26
 */
@Data
public class ServiceTransactionException extends RuntimeException {
    private Integer code;
    private String errMsg;

    public ServiceTransactionException() {
        super();
        this.code = CommonStatus.TRANSACTION.getCode();
        this.errMsg = CommonStatus.TRANSACTION.getMsg();
    }

    public ServiceTransactionException(String errMsg) {
        super(errMsg);
        this.code = CommonStatus.TRANSACTION.getCode();
        this.errMsg = errMsg;
    }
}
