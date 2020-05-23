package com.github.dev.muzi.kwafoo.config.platform.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 【配置数据信息】JWT的配置信息
 * Create by Muzi Li on 2019-12-04
 */
@Data
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "defaultSecret";

    private Long expiration = 604800L;

    private String md5Key = "randomKey";
}
