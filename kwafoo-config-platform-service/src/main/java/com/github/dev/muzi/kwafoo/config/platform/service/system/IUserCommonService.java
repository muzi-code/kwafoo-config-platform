package com.github.dev.muzi.kwafoo.config.platform.service.system;


import com.github.dev.muzi.kwafoo.config.platform.domain.exception.MysqlException;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.ParameterException;
import com.github.dev.muzi.kwafoo.config.platform.domain.system.RegisterUserInfo;
import com.github.dev.muzi.kwafoo.config.platform.domain.system.UserDetailInfo;

/**
 * 【System业务层接口】 用户通用业务
 * Create by Muzi Li on 2019-10-31
 */
public interface IUserCommonService {

    /*
     * 用户注册接口
     */
    void register(RegisterUserInfo enrollUserVO) throws ParameterException, MysqlException;

    /*
     * 验证用户名是否存在
     */
    boolean check(String userName) throws ParameterException;

    /*
     * 获取该用户名下的用户信息【有效用户】
     */
    UserDetailInfo getUser(String userName) throws ParameterException;

    /*
     * 用户名密码验证
     */
    boolean verify(String userName, String userPassword) throws ParameterException;

    /*
     * 根据用户id获取用户详细信息（除用户密码之外的数据)
     */
    UserDetailInfo getUserDetail(String userId) throws ParameterException;

    /*
     * 修改用户的详细信息（除密码等敏感信息外的信息）
     */
    UserDetailInfo update(UserDetailInfo userInfoVO) throws ParameterException, MysqlException;
}
