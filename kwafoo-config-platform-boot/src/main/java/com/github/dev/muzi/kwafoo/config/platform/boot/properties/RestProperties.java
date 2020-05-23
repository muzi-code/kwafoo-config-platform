package com.github.dev.muzi.kwafoo.config.platform.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 【配置数据信息】JWT的配置信息
 *  Create by Muzi Li on 2019-12-04
 */
@Data
@Configuration
@ConfigurationProperties(prefix = RestProperties.REST_PREFIX)
public class RestProperties {
    public final static String REST_PREFIX = "rest";

    private boolean authOpen;
}
