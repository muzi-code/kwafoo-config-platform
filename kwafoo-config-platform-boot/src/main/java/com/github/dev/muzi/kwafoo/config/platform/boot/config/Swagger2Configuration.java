package com.github.dev.muzi.kwafoo.config.platform.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【配置】 swagger2 动态生成http接口文档界面
 * Create by Muzi Li on 2019-07-25
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        String CONTROLLER_PACKAGES = "com.github.dev.muzi.kwafoo.config.platform.boot.controller";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLER_PACKAGES))
                .paths(PathSelectors.any())
                .build().enable(true);
    }

    private ApiInfo apiInfo() {
        String DOCUMENT_VERSION = "V1.0";
        String DOCUMENT_TITLE = "【夸父】配置平台API";
        String TERMS_OF_SERVICE_URL = "http://nlp-knowledge-graph.jd.local/";
        String DOCUMENT_DESCRIPTION = "系统主要开发夸父】配置平台API相关业务，更偏向于提供系统的基础配置功能。";
        return new ApiInfoBuilder()
                .title(DOCUMENT_TITLE)
                .description(DOCUMENT_DESCRIPTION)
                .termsOfServiceUrl(TERMS_OF_SERVICE_URL)
                .version(DOCUMENT_VERSION)
                .build();
    }
}