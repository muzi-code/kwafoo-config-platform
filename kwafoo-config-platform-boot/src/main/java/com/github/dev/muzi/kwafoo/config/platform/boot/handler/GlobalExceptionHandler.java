package com.github.dev.muzi.kwafoo.config.platform.boot.handler;

import com.github.dev.muzi.kwafoo.config.platform.domain.exception.*;
import com.github.dev.muzi.kwafoo.config.platform.domain.view.BaseResponse;
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

    @ExceptionHandler(RedisException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponse redisException(RedisException e) {
        return BaseResponse.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MysqlException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponse transactionException(MysqlException e) {
        return BaseResponse.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponse paramsException(ParameterException e) {
        return BaseResponse.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RpcException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponse paramsFoundException(RpcException e) {
        return BaseResponse.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ProcessException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponse paramsException(ProcessException e) {
        return BaseResponse.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponse exception(Exception e) {
        return BaseResponse.systemError();
    }
}
