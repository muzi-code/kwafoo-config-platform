package com.github.dev.muzi.kwafoo.config.platform.domain.system;

import com.github.dev.muzi.kwafoo.config.platform.domain.view.IBaseValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 【页面展示实体类】用户详细信息
 * Create by Muzi Li on 2019-11-26
 */
@Data
@ApiModel(value = "用户详细信息", description = "用户详细信息")
public class UserDetailInfo implements IBaseValid {

    /*
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", name = "id", dataType = "Long", required = true, example = "1")
    private Long id;

    /*
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", name = "userName", dataType = "String", required = true, example = "18543542567")
    private String userName;

    /*
     * 昵称
     */
    @ApiModelProperty(value = "用户昵称", name = "nickName", dataType = "String", required = true, example = "飞来的火箭")
    private String nickName;

    /*
     * 年龄
     */
    @ApiModelProperty(value = "年龄", name = "age", dataType = "Integer", required = true, example = "28")
    private Integer age;

    /*
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "sex", dataType = "Integer", required = true, example = "1")
    private Integer sex;

    /*
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "email", dataType = "String", required = true, example = "736127775@qq.com")
    private String email;

    /*
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "address", dataType = "String", required = true, example = "北京市朝阳区")
    private String address;

    /*
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "phone", dataType = "String", required = true, example = "18611865656")
    private String phone;

    /*
     * 个性宣言
     */
    @ApiModelProperty(value = "个性签名", name = "declaration", dataType = "String", required = true, example = "我行我素")
    private String declaration;

    /*
     * 头像URL
     */
    @ApiModelProperty(value = "头像URL", name = "headImage", dataType = "String", required = true, example = "http://www.aaa.com/asd/asd.jpg")
    private String headImage;

    /*
     * 生活状态
     */
    @ApiModelProperty(value = "生活状态", name = "lifeState", dataType = "Integer", required = true, example = "1")
    private Integer lifeState;

    @Override
    public boolean isValidParams() {
        boolean res = true;
        if (id == null || id < 1) {
            res = false;
        }
        return res;
    }
}
