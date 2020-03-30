package com.xxx.web.config;

import com.mickey.core.autoconfigure.interceptor.CorsInterceptor;
import com.mickey.core.config.AbstractConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebMvcConfig extends AbstractConfigurerAdapter {

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public HandlerInterceptor getCorsInterceptor() {
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (profile.equals("dev") || profile.equals("si")) {
            registry.addInterceptor(getCorsInterceptor()).addPathPatterns("/**");
        }
    }
}
