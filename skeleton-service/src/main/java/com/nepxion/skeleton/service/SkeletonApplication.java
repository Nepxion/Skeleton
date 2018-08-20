package com.nepxion.skeleton.service;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import com.nepxion.skeleton.annotation.EnableSkeleton;
import com.nepxion.skeleton.plugin.springcloud.configuration.SpringCloudPluginConfiguration;

@SpringBootApplication
@EnableSkeleton
@Import(SpringCloudPluginConfiguration.class)
public class SkeletonApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SkeletonApplication.class).run(args);
    }
}