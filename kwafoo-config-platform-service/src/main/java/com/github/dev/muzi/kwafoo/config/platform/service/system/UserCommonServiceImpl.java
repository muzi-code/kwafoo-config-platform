package com.github.dev.muzi.kwafoo.config.platform.service.system;

import com.github.dev.muzi.kwafoo.config.platform.dao.user.model.BasicUserInfo;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.MysqlException;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.ParameterException;
import com.github.dev.muzi.kwafoo.config.platform.domain.exception.ProcessException;
import com.github.dev.muzi.kwafoo.config.platform.domain.system.RegisterUserInfo;
import com.github.dev.muzi.kwafoo.config.platform.domain.system.UserDetailInfo;
import com.github.dev.muzi.kwafoo.config.platform.service.basic.user.IUserDataOperationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


/**
 * 【业务层接口实现类】 用户通用业务
 * Create by Muzi Li on 2019-10-31
 */
@Service("userCommonService")
public class UserCommonServiceImpl implements IUserCommonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCommonServiceImpl.class);

    @Autowired
    private IUserDataOperationService userDao;

    @Override
    public void register(RegisterUserInfo user) throws ParameterException, MysqlException {
        if (user == null) {
            throw new ParameterException("参数不合法");
        }
        // 校验
        // EnrollUserVO -> BasicUserInfo转换
        BasicUserInfo basicUserInfo = new BasicUserInfo();
        // spring自带的反射工具，字段名称类型相同
        BeanUtils.copyProperties(user, basicUserInfo);

        System.out.println(basicUserInfo.toString());

        // 补全用户其他字段
        Date now = new Date();
        basicUserInfo.setUpdateTime(now);
        basicUserInfo.setUpdateTime(now);
        basicUserInfo.setYn(1);

        System.out.println(basicUserInfo.toString());
        // 数据插入
        userDao.add(basicUserInfo);
    }

    @Override
    public boolean check(String userName) throws ParameterException {
        if (StringUtils.isBlank(userName)) {
            throw new ParameterException("用户名不能为空");
        }

        Integer hasUserName = userDao.getUserCount(userName);

        return (hasUserName != null && hasUserName > 0);
    }

    @Override
    public UserDetailInfo getUser(String userName) throws ParameterException {
        if (StringUtils.isBlank(userName)) {
            throw new ParameterException("用户名不能为空");
        }
        BasicUserInfo userInfoDO = userDao.getValidUser(userName);
        UserDetailInfo user = new UserDetailInfo();
        BeanUtils.copyProperties(userInfoDO, user);

        return user;
    }

    @Override
    public boolean verify(String userName, String userPassword) throws ParameterException {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPassword)) {
            throw new ParameterException("用户名密码不合法");
        }
        List<BasicUserInfo> userInfoList = userDao.listUser(userName);

        boolean res = false;
        if (!CollectionUtils.isEmpty(userInfoList)) {
            BasicUserInfo user = userInfoList.get(0);
            //userPassword -> md5 -> equals user.password
            res = userPassword.equals(user.getUserPassword());
        }
        return res;
    }

    @Override
    public UserDetailInfo getUserDetail(String userId) throws ParameterException {
        if (StringUtils.isBlank(userId)) {
            throw new ParameterException("用户ID为空");
        }
        UserDetailInfo userVo = null;
        BasicUserInfo user = userDao.getUser(Long.parseLong(userId));
        if (user != null && user.getId() != null && user.getYn() == 1) {
            userVo = new UserDetailInfo();
            BeanUtils.copyProperties(user, userVo);
        }
        return userVo;
    }

    @Override
    public UserDetailInfo update(UserDetailInfo userInfoVO) throws ParameterException, MysqlException {
        if (userInfoVO == null || userInfoVO.getId() == null) {
            throw new ParameterException("用户信息不合法");
        }

        BasicUserInfo updateUser = new BasicUserInfo();
        BeanUtils.copyProperties(userInfoVO, updateUser);
        updateUser.setCreateTime(null);
        updateUser.setUpdateTime(new Date());
        userDao.update(updateUser);

        return userInfoVO;
    }
}
