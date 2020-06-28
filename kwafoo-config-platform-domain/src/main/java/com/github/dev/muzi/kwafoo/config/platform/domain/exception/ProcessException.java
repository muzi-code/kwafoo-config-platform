package com.github.dev.muzi.kwafoo.config.platform.domain.exception;


import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import lombok.Data;

import javax.naming.CommunicationException;


/**
 * 【异常】MQ业务异常信息
 * Create by Muzi Li on 2020-03-10
 */
@Data
public class ProcessException extends RuntimeException {

    private Integer code;
    private String errMsg;

    public ProcessException() {
        super(CommonStatus.PROCESS_ERROR.getMsg());
        this.code = CommonStatus.PROCESS_ERROR.getCode();
        this.errMsg = CommonStatus.PROCESS_ERROR.getMsg();
    }

    public ProcessException(String errMsg) {
        super(errMsg);
        this.code = CommonStatus.PROCESS_ERROR.getCode();
        this.errMsg = errMsg;
    }
}
