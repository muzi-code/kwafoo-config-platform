package com.github.dev.muzi.kwafoo.config.platform.dao.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/*
 * 【数据库实体类】基础用户信息表
 *  Create by Muzi Li on 2019-11-26
 */
@Data
@TableName("system_user")
public class BasicUserInfo {

    /*
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /*
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /*
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /*
     * 用户密码
     */
    @TableField("user_password")
    private String userPassword;

    /*
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /*
     * 性别：1男 2女 3中性 4其他
     */
    @TableField("sex")
    private Integer sex;

    /*
     * 邮箱
     */
    @TableField("email")
    private String email;

    /*
     * 地址信息
     */
    @TableField("address")
    private String address;

    /*
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /*
     * 个性宣言
     */
    @TableField("declaration")
    private String declaration;

    /*
     * 头像url
     */
    @TableField("head_image")
    private String headImage;

    /*
     * 生活状态 0单身 1热恋 2已婚 3为人父母
     */
    @TableField("life_state")
    private Integer lifeState;

    /*
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /*
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /*
     * 是否有效 1有效 0无效
     */
    @TableField("yn")
    private Integer yn;
}
