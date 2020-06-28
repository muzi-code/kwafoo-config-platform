package com.github.dev.muzi.kwafoo.config.platform.domain.exception;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import lombok.Data;

/**
 * 【异常】数据库事务异常
 * Create by Muzi Li on 2019-11-26
 */
@Data
public class MysqlException extends RuntimeException {

    private Integer code;
    private String errMsg;

    public MysqlException() {
        super(CommonStatus.MYSQL_ERROR.getMsg());
        this.code = CommonStatus.MYSQL_ERROR.getCode();
        this.errMsg = CommonStatus.MYSQL_ERROR.getMsg();
    }

    public MysqlException(String errMsg) {
        super(errMsg);
        this.code = CommonStatus.MYSQL_ERROR.getCode();
        this.errMsg = errMsg;
    }
}
