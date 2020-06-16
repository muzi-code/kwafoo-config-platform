package com.github.dev.muzi.kwafoo.config.platform.domain.controller;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 【页面展示实体类】控制器返回值
 *
 *  Create by Muzi Li on 2019-11-26
 */
@Data
@ApiModel(value = "系统返回值封装",description = "系统返回值封装")
public class BaseResponse<M> {

    /*
     * 响应状态
     */
    @ApiModelProperty(value = "响应状态")
    private Integer status;

    /*
     * 响应信息
     */
    @ApiModelProperty(value = "响应信息")
    private String msg;

    /*
     * 数据实体
     */
    @ApiModelProperty(value = "数据实体")
    private M data;



    public static<M> BaseResponse success(){
        BaseResponse baseResponseVO = new BaseResponse();
        baseResponseVO.setStatus(CommonStatus.SUCCESS.getCode());
        baseResponseVO.setMsg(CommonStatus.SUCCESS.getMsg());
        return baseResponseVO;
    }

    public static<M> BaseResponse success(M data){
        BaseResponse baseResponseVO = new BaseResponse();
        baseResponseVO.setStatus(CommonStatus.SUCCESS.getCode());
        baseResponseVO.setMsg(CommonStatus.SUCCESS.getMsg());
        baseResponseVO.setData(data);
        return baseResponseVO;
    }

    public static<M> BaseResponse failure(){
        BaseResponse baseResponseVO = new BaseResponse();
        baseResponseVO.setStatus(CommonStatus.FAILURE.getCode());
        baseResponseVO.setMsg(CommonStatus.FAILURE.getMsg());
        return baseResponseVO;
    }

    public static<M> BaseResponse failure(String message){
        BaseResponse baseResponseVO = new BaseResponse();
        baseResponseVO.setStatus(CommonStatus.FAILURE.getCode());
        baseResponseVO.setMsg(message);
        return baseResponseVO;
    }

    public static<M> BaseResponse failure(Integer status, String message){
        BaseResponse baseResponseVO = new BaseResponse();
        baseResponseVO.setStatus(status);
        baseResponseVO.setMsg(message);
        return baseResponseVO;
    }

    public static<M> BaseResponse noLogin(){
        BaseResponse baseResponseVO = new BaseResponse();
        baseResponseVO.setStatus(CommonStatus.NO_LOGIN.getCode());
        baseResponseVO.setMsg(CommonStatus.NO_LOGIN.getMsg());
        return baseResponseVO;
    }

    public static<M> BaseResponse systemError(){
        BaseResponse baseResponseVO = new BaseResponse();
        baseResponseVO.setStatus(CommonStatus.ERROR.getCode());
        baseResponseVO.setMsg(CommonStatus.ERROR.getMsg());
        return baseResponseVO;
    }


}
