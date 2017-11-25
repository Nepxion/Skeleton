# Skeleton Generator
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)

基于FreeMarker的框架脚手架生成组件，可以轻松快速实现对框架代码的一键创建（例如业务部门实现对基础架构部提供的框架快速搭建）

## 介绍

    1. 严格遵照Maven结构进行脚手架编排
    2. 支持任何文件文件的逆向创建，包括Java类文件，配置文件，脚本文件，XML文件，YAML文件等
    3. 使用者只需要关注模板原型文件的编辑（遵循FreeMarker语法），并设置动态变量
    4. 使用者根据模板原型文件创建Generator类，进行动态创建和替换

## 使用
模板文件示例，用${}表示为动态变量
```java
package ${package};

/**
 * <p>Title: ${title}</p>
 * <p>Description: ${description}</p>
 * <p>Copyright: ${copyright}</p>
 * <p>Company: ${company}</p>
 * @author ${author}
 * @email ${email}
 * @version ${version}
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import ${MyContextAwareClassPath};
import ${MyServiceClassPath};

@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.nepxion.matrix.test.simple" })
public class MyApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyApplication.class, args);

        MyService1 myService = MyContextAware.getBean(MyService.class);
        myService1.doA("A");
        myService1.doB("B");
    }
}
```

Generator类示例
```java
package com.nepxion.skeleton.demo.server.java;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.Map;

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

public class MyApplicationClassGenerator extends SkeletonJavaGenerator {
    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param skeletonProperties 全局配置文件对象
     */
    public MyApplicationClassGenerator(String generatePath, String projectType, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, MyApplicationClassGenerator.class, skeletonProperties);
    }

    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param baseTemplatePath 模板文件的等级路径
     * @param skeletonProperties 全局配置文件对象
     */
    public MyApplicationClassGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, baseTemplatePath, skeletonProperties);
    }

    /**
     * 设置Java类的包路径，如果没特殊处理，则按照默认顶级包路径来处理，不需要Override该方法
     */
    /*@Override
    protected String getPackage() {
        return super.getPackage() + "." + "abc";
    }*/

    /**
     * 设置Java类名
     */
    @Override
    protected String getClassName() {
        return "MyApplication";
    }

    /**
     * 设置模板名
     */
    @Override
    protected String getTemplateName() {
        return "MyApplication.java.template";
    }

    /**
     * 设置Java类的输出路径，如果没特殊处理，则按照默认输出路径来处理，不需要Override该方法
     */
    /*@Override
    protected String getOutputPath() {
        return super.getOutputPath() + "/" + "xyz";
    }*/

    /**
     * 设置Java类到main目录下，还是在test目录下
     */
    @Override
    protected boolean isMainCode() {
        return true;
    }

    /**
     * 设置Java类创建的所依赖数据模型，主要做动态变量到原型模板的替换（任何文本的替换都支持）
     */
    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = generateDataModel();
        // 注意：根据freemarker的规范，dataModel中的key似乎只能支持字母和数字，不支持符号，例如MyContextAware.ClassPath，MyContextAware-ClassPath都会抛错
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());
        dataModel.put("MyContextAwareClassPath", "com.nepxion.matrix.test.simple.context.MyContextAware");
        dataModel.put("MyServiceClassPath", "com.nepxion.matrix.test.simple.service.MyService");

        return dataModel;
    }
}
```

Generator类示例
配置全局配置文件my-properties.properties
```java
# 工程的模块名，首字母必须小写，中间只允许出现“-”
# moduleName=payment
moduleName=payment-ccb

# 上层包路径，该路径会作为所有Java代码的上层路径。moduleName、basePackage和projectType三者组合起来解析出相关目录和结构规则，例如moduleName=payment-ccb，basePackage=com.nepxion，projectType=server，那么工程名为payment-ccb-server，类路径为com.nepxion.payment.ccb.server
basePackage=com.nepxion

# 类信息-标题
title=Nepxion Skeleton Generator
# 类信息-描述
description=Nepxion Skeleton Generator For Freemarker
# 类信息-版权信息
copyright=Copyright (c) 2017
# 类信息-公司
company=Nepxion
# 类信息-作者
author=Haojun Ren
# 类信息-作者邮箱
email=1394997@qq.com
# 类信息-版本
version=1.0
```

运行程序
```java
package com.nepxion.skeleton.demo;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.server.java.MyApplicationClassGenerator;
import com.nepxion.skeleton.service.resources.MybatisGeneratorXmlGenerator;

public class MyGenerator1 {
    public static void main(String[] args) {
        try {
            // 创建文件的输出的路径
            String generatePath = "E:/Download/Skeleton/";

            // 描述规则的配置文件所在的路径
            String propertiesPath = "properties/my-properties.properties";

            // 构造全局配置文件对象
            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath);

            // 创建Java类文件
            // 模板文件MyApplication.java.template必须和MyApplicationClassGenerator放在同一个目录下
            new MyApplicationClassGenerator(generatePath, "server", skeletonProperties).generate();

            // 创建文件到resources目录下
            // 模板文件mybatis-generator.xml.template必须和MybatisGeneratorXmlGenerator放在同一个目录下
            new MybatisGeneratorXmlGenerator(generatePath, "service", skeletonProperties).generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```