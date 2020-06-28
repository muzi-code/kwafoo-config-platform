package com.github.dev.muzi.kwafoo.config.platform.service.basic.user;

import com.github.dev.muzi.kwafoo.config.platform.dao.user.model.BasicUserInfo;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.MysqlException;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.ParameterException;

import java.util.List;

/**
 * 【基础数据业务接口】 用户数据业务
 * Create by Muzi Li on 2019-12-04
 */
public interface IUserDataOperationService {

    /*
     * 通过用户名查询全部用户信息
     */
    List<BasicUserInfo> listUser(String name) throws ParameterException;

    /*
     * 通过用户名查询有效用户信息
     * 同一个用户名下只会又一个有效的用户信息
     */
    BasicUserInfo getValidUser(String name) throws ParameterException;

    /*
     * 通过用户名查询用户数量
     */
    Integer getUserCount(String name) throws ParameterException;

    /*
     * 通过用户ID查询用户信息
     */
    BasicUserInfo getUser(Long id) throws ParameterException;


    /*
     * 新增用户信息
     */
    void add(BasicUserInfo userInfoDO) throws ParameterException, MysqlException;

    /*
     * 更新用户信息
     */
    void update(BasicUserInfo userInfoDO) throws ParameterException, MysqlException;

}
