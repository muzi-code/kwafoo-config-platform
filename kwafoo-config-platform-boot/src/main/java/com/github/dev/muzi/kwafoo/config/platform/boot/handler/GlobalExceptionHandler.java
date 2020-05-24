package com.github.dev.muzi.kwafoo.config.platform.boot.handler;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatusConstant;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.*;
import com.github.dev.muzi.kwafoo.config.platform.domain.view.BaseResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 【拦截器】 通用的全局的异常拦截器
 * 备注：程序中所有处理不了的异常都需要向外抛出，该拦截器会处理定义的异常信息，
 *      对于其他未定义的异常均用Exception拦截。
 * Create by Muzi Li on 2019-11-26
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceCommonException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponseVO serviceException(ServiceCommonException e){
        return BaseResponseVO.failure(CommonStatusConstant.SERVICE_COMMON_ERROR.getCode(),
                CommonStatusConstant.SERVICE_COMMON_ERROR.getMsg());
    }

    @ExceptionHandler(ControllerCommonException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponseVO serviceException(ControllerCommonException e){
        return BaseResponseVO.failure(CommonStatusConstant.CONTROLLER_COMMON_ERROR.getCode(),
                CommonStatusConstant.CONTROLLER_COMMON_ERROR.getMsg());
    }

    @ExceptionHandler(ServiceTransactionException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponseVO transactionException(ServiceTransactionException e){
        return BaseResponseVO.failure(CommonStatusConstant.SERVICE_TRANSACTION_ERROR.getCode(),
                CommonStatusConstant.SERVICE_TRANSACTION_ERROR.getMsg());
    }

    @ExceptionHandler(RequestArgumentsException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponseVO paramsException(RequestArgumentsException e){
        return BaseResponseVO.failure(CommonStatusConstant.CONTROLLER_ARGUMENTS_ERROR.getCode(),
                CommonStatusConstant.CONTROLLER_ARGUMENTS_ERROR.getMsg());
    }

    @ExceptionHandler(RpcServiceException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponseVO paramsFoundException(RpcServiceException e){
        return BaseResponseVO.failure(CommonStatusConstant.SERVICE_RPC_ERROR.getCode(),
                CommonStatusConstant.SERVICE_RPC_ERROR.getMsg());
    }

    @ExceptionHandler(CommonMessageException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponseVO paramsException(CommonMessageException e){
        return BaseResponseVO.failure(e.getCode(), e.getErrMsg());
    }

    @ExceptionHandler(MqServiceException.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponseVO paramsException(MqServiceException e){
        return BaseResponseVO.failure(CommonStatusConstant.SERVICE_MQ_ERROR.getCode(), CommonStatusConstant.SERVICE_MQ_ERROR.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public BaseResponseVO exception(Exception e){
        return BaseResponseVO.systemError();
    }
}
