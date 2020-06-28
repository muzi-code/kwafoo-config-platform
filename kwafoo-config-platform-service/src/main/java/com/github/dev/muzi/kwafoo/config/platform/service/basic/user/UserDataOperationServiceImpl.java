package com.github.dev.muzi.kwafoo.config.platform.service.basic.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.dev.muzi.kwafoo.config.platform.common.NumUtils;
import com.github.dev.muzi.kwafoo.config.platform.dao.user.mapper.BasicUserMapper;
import com.github.dev.muzi.kwafoo.config.platform.dao.user.model.BasicUserInfo;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.MysqlException;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.ParameterException;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.ProcessException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 【业务层接口实现类】 基础用户数据业务
 * Create by Muzi Li on 2019-12-04
 */
@Service("userDataOperationServiceImpl")
public class UserDataOperationServiceImpl implements IUserDataOperationService {

    /*
     * 用户信息表具备 yn 字段
     *  1.yn = 1 是有效用户
     *  2.yn = 0 是无效用户
     */
    private static final Integer IS_VALID_FLAG = 1;
    private static final Integer ISNOT_VALID_FLAG = 0;

    /*
     * 用户基础信息dao对象
     */
    @Resource
    private BasicUserMapper userDao;


    @Override
    public List<BasicUserInfo> listUser(String name) throws ParameterException {
        if (StringUtils.isBlank(name)) {
            throw new ParameterException("用户名不能为空");
        }
        QueryWrapper<BasicUserInfo> queryWrapper = new QueryWrapper<BasicUserInfo>();
        queryWrapper.eq("user_name", name);

        return userDao.selectList(queryWrapper);
    }

    @Override
    public BasicUserInfo getValidUser(String name) throws ParameterException {
        if (StringUtils.isBlank(name)) {
            throw new ParameterException("用户名不能为空");
        }
        QueryWrapper<BasicUserInfo> queryWrapper = new QueryWrapper<BasicUserInfo>();
        queryWrapper.eq("user_name", name);
        queryWrapper.eq("yn", IS_VALID_FLAG);

        return userDao.selectOne(queryWrapper);
    }

    @Override
    public Integer getUserCount(String name) throws ParameterException {
        if (StringUtils.isBlank(name)) {
            throw new ParameterException("用户名不能为空");
        }
        QueryWrapper<BasicUserInfo> queryWrapper = new QueryWrapper<BasicUserInfo>();
        queryWrapper.eq("user_name", name);

        return userDao.selectCount(queryWrapper);
    }

    @Override
    public BasicUserInfo getUser(Long id) throws ParameterException {
        if (!NumUtils.greatThan(id, 0)) {
            throw new ParameterException("用户ID不合法");
        }

        return userDao.selectById(id);
    }


    /*
     * 用户数据表结构性更改操作写在查询之后
     */

    @Override
    @Transactional
    public void add(BasicUserInfo userInfoDO) throws ParameterException, MysqlException {
        if (userInfoDO == null) {
            throw new ParameterException("用户信息不能为空");
        }
        int num = userDao.insert(userInfoDO);
        if (1 != num) {
            throw new MysqlException();
        }
    }

    @Override
    @Transactional
    public void update(BasicUserInfo userInfoDO) throws ParameterException, MysqlException {
        if (userInfoDO == null || !NumUtils.greatThan(userInfoDO.getId(), 0)) {
            throw new ParameterException("用户ID不合法");
        }
        int num = userDao.updateById(userInfoDO);
        if (1 != num) {
            throw new MysqlException();
        }
    }
}
