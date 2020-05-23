package com.github.dev.muzi.kwafoo.config.platform.boot.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.dev.muzi.kwafoo.config.platform.boot.filter.RequestAuthFilter;
import com.github.dev.muzi.kwafoo.config.platform.boot.properties.RestProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * 【配置】 WEB相关配置
 * Create by Muzi Li on 2019-07-25
 */
@Configuration
@PropertySource(value = {"classpath:boot.properties"})
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    /*
     * Spring mvc 的页面解析器配置
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/pages/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /*
     * Spring mvc 的静态资源路径配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /*
     * 项目序列化使用fastjson，因序列化解析器执行是列表由前至后的优先级，故把fastjson加入到序列0的位置
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //converters.add(fastJsonHttpMessageConverter());
        converters.set(0,fastJsonHttpMessageConverter());
    }

    /*
     * 生成FastJsonHttpMessageConverter返回值解析器
     */
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setFastJsonConfig(fastJsonConfig());
        converter.setSupportedMediaTypes(mediaTypes());
        return converter;
    }

    /*
     * 生成FastJsonHttpMessageConverter返回值解析器 - 解析详细配置
     */
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteEnumUsingToString
        );

        // 日期时间格式及字符集配置
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setCharset(Charset.forName("utf-8"));

        // 防止Long类型转json丢失精度的问题
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        return fastJsonConfig;
    }

    /*
     * 生成FastJsonHttpMessageConverter返回值解析器 - 解析格式组件 支持的mediaType的类型
     * 1.application/json;charset=UTF-8
     * 2.application/json
     */
    public List<MediaType> mediaTypes() {
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        return mediaTypeList;
    }

    /*
     * 配置是否开启JWT验证
     */
    @Bean
    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "auth-open", havingValue = "true", matchIfMissing = true)
    public RequestAuthFilter jwtAuthenticationTokenFilter() {
        return new RequestAuthFilter();
    }


    public static class OuterTomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return !getClass().getClassLoader().equals(org.apache.catalina.startup.Tomcat.class.getClassLoader());
        }

    }

}
