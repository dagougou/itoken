package com.it.itoken.web.admin.config;

import com.it.itoken.web.admin.interceptor.WebAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wjh
 * @create 2019-10-02 17:18
 */
@Configuration
public class WebAdminInterceptorConfig implements WebMvcConfigurer {

    @Bean
    WebAdminInterceptor webAdminInterceptor() {
        return new WebAdminInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webAdminInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static");
    }
}
