package com.github.dev.muzi.kwafoo.config.platform;

import com.github.dev.muzi.kwafoo.config.platform.boot.config.Swagger2Configuration;
import com.github.dev.muzi.kwafoo.config.platform.boot.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;


/**
 * Create by lifuyi7 on 2020-02-21
 */
@SpringBootApplication
@ComponentScan(value = "com.github.dev.muzi.kwafoo.config.platform")
@ImportResource({"classpath:spring/spring-base.xml"})
@PropertySource(value = {"classpath:boot.properties", "classpath:config.properties"})
@EnableAspectJAutoProxy(proxyTargetClass = false, exposeProxy = true)
public class KwafooConfigPlatformApplicationStart extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(KwafooConfigPlatformApplicationStart.class, args);
    }
}
