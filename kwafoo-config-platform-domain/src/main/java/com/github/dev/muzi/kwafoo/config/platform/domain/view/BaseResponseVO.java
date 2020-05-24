package com.github.dev.muzi.kwafoo.config.platform.domain.view;

import com.github.dev.muzi.kwafoo.config.platform.domain.constant.CommonStatusConstant;
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
public class BaseResponseVO<M> {

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



    public static<M> BaseResponseVO success(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(CommonStatusConstant.SUCCESS.getCode());
        baseResponseVO.setMsg(CommonStatusConstant.SUCCESS.getMsg());
        return baseResponseVO;
    }

    public static<M> BaseResponseVO success(M data){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(CommonStatusConstant.SUCCESS.getCode());
        baseResponseVO.setMsg(CommonStatusConstant.SUCCESS.getMsg());
        baseResponseVO.setData(data);
        return baseResponseVO;
    }

    public static<M> BaseResponseVO failure(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(CommonStatusConstant.FAILURE.getCode());
        baseResponseVO.setMsg(CommonStatusConstant.FAILURE.getMsg());
        return baseResponseVO;
    }

    public static<M> BaseResponseVO failure(String message){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(CommonStatusConstant.FAILURE.getCode());
        baseResponseVO.setMsg(message);
        return baseResponseVO;
    }

    public static<M> BaseResponseVO failure(Integer status,String message){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(status);
        baseResponseVO.setMsg(message);
        return baseResponseVO;
    }

    public static<M> BaseResponseVO noLogin(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(CommonStatusConstant.USER_NO_LOGIN_ERROR.getCode());
        baseResponseVO.setMsg(CommonStatusConstant.USER_NO_LOGIN_ERROR.getMsg());
        return baseResponseVO;
    }

    public static<M> BaseResponseVO systemError(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(CommonStatusConstant.OTHER_ERROR.getCode());
        baseResponseVO.setMsg(CommonStatusConstant.OTHER_ERROR.getMsg());
        return baseResponseVO;
    }


}
