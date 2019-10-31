package com.it.itoken.common.web.config;

import com.it.itoken.common.web.interceptor.ConstantsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wjh
 * @create 2019-10-02 16:48
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ConstantsInterceptor()).addPathPatterns("/**");
    }
}
