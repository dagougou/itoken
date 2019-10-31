package com.it.itoken.service.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author wjh
 * @create 2019-09-28 20:38
 */
@SpringBootApplication(scanBasePackages = "com.it.itoken")
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.it.itoken.service.sso.mapper")
public class ServiceSSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSSOApplication.class, args);
    }
}
