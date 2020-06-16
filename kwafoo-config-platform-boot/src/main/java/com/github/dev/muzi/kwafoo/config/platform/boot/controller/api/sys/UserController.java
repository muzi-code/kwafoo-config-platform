package com.github.dev.muzi.kwafoo.config.platform.boot.controller.api.sys;

import com.github.dev.muzi.kwafoo.config.platform.boot.filter.JwtTokenTool;
import com.github.dev.muzi.kwafoo.config.platform.common.CookieUtil;
import com.github.dev.muzi.kwafoo.config.platform.domain.controller.BaseResponse;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "[系统]用户业务接口", tags = {"[系统]用户业务接口"})
@Controller
@RequestMapping(value = "/api/basic/user")
public class UserController {
    private static final String TOKEN_KEY = "Authorization";
    private static final String TOKEN_VALUE_PRE = "Bearer_";
    private static final int TOKEN_COOKIE_MAX_AGE = 60 * 60;

    @Autowired
    private JwtTokenTool jwtTokenTool;

    @RequestMapping(value = "/adminLogin", method = {RequestMethod.GET})
    @ResponseBody
    public BaseResponse adminLogin(String username, String password, HttpServletResponse response) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return BaseResponse.failure();
        }
        if (username.equals("aidaren") && password.equals("abc123456")) {
            String randomKey = jwtTokenTool.getRandomKey();
            String token = jwtTokenTool.generateToken("aidaren", randomKey);
            CookieUtil.set(response, TOKEN_KEY, TOKEN_VALUE_PRE + token, TOKEN_COOKIE_MAX_AGE);
            return BaseResponse.success();
        }
        return BaseResponse.failure();
    }

    @RequestMapping(value = "/loginOut", method = {RequestMethod.GET})
    public ModelAndView adminLoginOut(HttpServletRequest request,HttpServletResponse response) {
        //1.从cookie里面查询
        Cookie cookie = CookieUtil.get(request, "Authorization");
        if (cookie != null) {
            //清除cookie,设置过期时间为0
            CookieUtil.set(response,"Authorization", null, 0);
        }
        return new ModelAndView("redirect:/loginPage");
    }

}
