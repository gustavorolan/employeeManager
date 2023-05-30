package com.ciss.employeeManager.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig  implements WebMvcConfigurer   {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/employeeManager/v1/user/login").exposedHeaders("X-Auth-Token");
    }

}
