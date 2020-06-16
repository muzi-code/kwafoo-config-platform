package com.github.dev.muzi.kwafoo.config.platform.boot.handler;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import com.github.dev.muzi.kwafoo.config.platform.domain.controller.BaseResponse;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.RequestArgumentsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 【拦截器】 通用的全局的异常拦截器
 * 备注：程序中所有处理不了的异常都需要向外抛出，该拦截器会处理定义的异常信息，
 * 对于其他未定义的异常均用Exception拦截。
 * Create by Muzi Li on 2019-11-26
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestArgumentsException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponse paramsException(RequestArgumentsException e) {
        return BaseResponse.failure(e.getCode(), e.getErrMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponse exception(Exception e) {
        return BaseResponse.systemError();
    }
}
