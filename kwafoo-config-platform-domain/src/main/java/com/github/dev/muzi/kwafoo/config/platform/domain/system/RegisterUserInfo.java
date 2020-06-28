package com.github.dev.muzi.kwafoo.config.platform.domain.system;

import com.github.dev.muzi.kwafoo.config.platform.domain.controller.IBaseValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 【页面展示实体类】用户注册信息
 * Create by Muzi Li on 2019-11-26
 */
@Data
@ApiModel(value = "用户注册信息", description = "用户注册信息")
public class RegisterUserInfo implements IBaseValid {

    /*
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "userName", dataType = "String", required = true, example = "18543542567")
    private String userName;

    /*
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", name = "userPassword", dataType = "String", required = true, example = "加密字符串")
    private String userPassword;

    /*
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "email", dataType = "String", required = true, example = "401940101@qq.com")
    private String email;

    /*
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "email", dataType = "String", required = true, example = "18543542567")
    private String phone;

    /*
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "address", dataType = "String", required = true, example = "北京市朝阳区大屯路东")
    private String address;


    @Override
    public boolean isValidParams() {
        boolean res = true;
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPassword) || StringUtils.isBlank(email)
                || StringUtils.isBlank(phone) || StringUtils.isBlank(address)
        ) {
            res = false;
        }
        return res;
    }
}
