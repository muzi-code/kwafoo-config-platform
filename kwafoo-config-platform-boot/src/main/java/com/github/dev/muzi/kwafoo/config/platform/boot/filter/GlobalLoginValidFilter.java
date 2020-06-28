package com.github.dev.muzi.kwafoo.config.platform.boot.filter;


import com.alibaba.fastjson.JSON;
import com.github.dev.muzi.kwafoo.config.platform.boot.properties.JwtProperties;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 【过滤器】请求权限过滤器
 * <p>
 * 备注：
 * 用户的请求先进行拦截，判断用户是否具备某一些条件，如果具备则可以访问，不具备则不能访问。
 * 主要是验证jwt，根据token去校验用户的能力。
 * Create by Muzi Li on 2019-09-12
 */
@Slf4j
public class GlobalLoginValidFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenTool jwtTokenTool;

    @Autowired
    private JwtProperties properties;


    /**
     * 过滤功能性接口必须验证用户是否登录了，未登录直接跳到登陆页
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getServletPath().equals("/api/basic/auth/verify")
                || request.getServletPath().startsWith("/pages")
                || request.getServletPath().startsWith("/api/basic")
                || request.getServletPath().startsWith("/adminLogin")
                || request.getServletPath().startsWith("/home")
                || request.getServletPath().startsWith("/loginPage")
                || request.getServletPath().equals("/swagger-ui.html")
                || request.getServletPath().startsWith("/swagger-resources")
                || request.getServletPath().startsWith("/v2/api-docs")
                || request.getServletPath().startsWith("/webjars/springfox-swagger-ui/")
                || request.getServletPath().startsWith("/layuiadmin/")
        ) {
            chain.doFilter(request, response);
            return;
        }

        final String requestHeader = request.getHeader(properties.getHeader());
        Cookie[] cookies = request.getCookies();
        String authorization = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    authorization = cookie.getValue();
                }
            }
        }

        boolean reqinfo = requestHeader != null && requestHeader.startsWith("Bearer_");
        boolean cookInfo = StringUtils.isNotBlank(authorization);
        if (reqinfo || cookInfo) {
            String authToken = "";
            if (reqinfo) {
                authToken = requestHeader.substring(7);
            } else {
                authToken = authorization.substring(7);
            }

            String userName = jwtTokenTool.getUsernameFromToken(authToken);
            log.info("userName:{}", userName);

            // 分布式场景下存ID需要使用 userName + 前缀拼接KEY存 id，设置和token差不多的过期时间存取封装一个新的TraceUtil


            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = jwtTokenTool.isTokenExpired(authToken);
                if (flag) {
                    response.sendRedirect(request.getContextPath() + "/loginPage");
                    // renderJson(response, BaseResponseVO.noLogin());
                    return;
                }
            } catch (JwtException e) {
                response.sendRedirect(request.getContextPath() + "/loginPage");
                //有异常就是token解析失败
                // renderJson(response, BaseResponseVO.noLogin());
                return;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/loginPage");
            // header没有带Bearer字段
            // renderJson(response, BaseResponseVO.noLogin());
            return;
        }
        chain.doFilter(request, response);
    }


    /**
     * 前后端分离需要渲染json对象，返回需要登录的信息
     */
    private static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}