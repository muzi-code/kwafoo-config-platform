package com.github.dev.muzi.kwafoo.config.platform.boot.controller.api.test;


import com.github.dev.muzi.kwafoo.config.platform.domain.controller.BaseResponse;
import com.github.dev.muzi.kwafoo.config.platform.service.redis.RedisOperation;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by lifuyi7 on 2020-02-21
 */
@Api(value = "[测试]系统测试接口", tags = {"[测试]系统测试接口"})
@RestController
@RequestMapping(value = "/test")
public class TestHelloWorldController {
    private static Logger LOGGER = LoggerFactory.getLogger(TestHelloWorldController.class);

    @Autowired
    private RedisOperation redisOperation;

    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    public BaseResponse hello() {
        boolean b = redisOperation.tokenIsLimitAllowed("test", 5, 5);
        return b ? BaseResponse.success("Hello World！") : BaseResponse.failure("系统繁忙");
    }

}

