package org.security.config;

import org.security.interceptor.MustaHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by admin on 20/06/2017.
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    // @Autowired
    //  MustaHandlerInterceptor mustaHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(mustaHandler());


    }

    @Bean
    public MustaHandlerInterceptor mustaHandler() {
        return new MustaHandlerInterceptor();
    }


}