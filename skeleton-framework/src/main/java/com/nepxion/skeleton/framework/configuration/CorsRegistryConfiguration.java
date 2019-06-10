package com.nepxion.skeleton.framework.configuration;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsRegistryConfiguration implements WebMvcConfigurer {
    @Value("${cors.registry.enabled:true}")
    private Boolean corsRegistryEnabled;

    // 解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (corsRegistryEnabled) {
            registry.addMapping("/**")
                    .allowedHeaders("*")
                    .allowedMethods("*")
                    .allowedOrigins("*");
        }
    }
}