package com.nepxion.skeleton.framework.configuration;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    public static final String BASE_PACKAGE = "com.nepxion.skeleton.framework.controller";

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${swagger.service.description:Skeleton Spring Cloud Restful APIs}")
    private String description;

    @Value("${swagger.service.version:1.0.0}")
    private String version;

    @Value("${swagger.service.license.name:Apache License 2.0}")
    private String license;

    @Value("${swagger.service.license.url:http://www.apache.org/licenses/LICENSE-2.0}")
    private String licenseUrl;

    @Value("${swagger.service.contact.name:Haojun Ren}")
    private String contactName;

    @Value("${swagger.service.contact.url:https://github.com/Nepxion/Skeleton}")
    private String contactUrl;

    @Value("${swagger.service.contact.email:1394997@qq.com}")
    private String contactEmail;

    @Value("${swagger.service.termsOfServiceUrl:http://www.nepxion.com}")
    private String termsOfServiceUrl;

    @Bean("skeletonDocket")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("skeleton")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE)) // 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(serviceName)
                .description(description)
                .version(version)
                .license(license)
                .licenseUrl(licenseUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .termsOfServiceUrl(termsOfServiceUrl)
                .build();
    }

}