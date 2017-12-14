# Skeleton Generator
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)

基于FreeMarker的框架脚手架生成组件，可以轻松快速实现对框架代码的一键创建（例如业务部门实现对基础架构部提供的框架快速搭建），实现对Spring Cloud的脚手架一键生成

## 介绍
    1. 严格遵照Maven结构进行脚手架编排
    2. 支持任何文件文件的逆向创建，包括Java类文件，配置文件，脚本文件，XML文件，YAML文件等
    3. 使用者只需要关注模板原型文件的编辑（遵循FreeMarker语法），并设置动态变量
    4. 使用者根据模板原型文件创建Generator类，进行动态创建和替换
    5. 基于Spring Cloud的调用，Spring Cloud中国社区的spring-cloud-codegen将采用本框架，参考https://github.com/SpringCloud/spring-cloud-codegen	
    6. 支持Swagger，打开http://localhost:2222/swagger-ui.html访问

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/Swagger.jpg)

## 配置文件使用规则
    1. skeleton-data.properties
       用来描述模板文件的全局配置值，里面的值替换模板文件里的动态变量(用${}表示)
    2. skeleton-description.xml
       用来描述模界面驱动和渲染的组件，分为Group和Entity结构，一个Group包含多个Entity，其他属性比较容易理解，主要介绍如下
       highlightable - 标识为高亮项，一般组件渲染成高亮方式，例如Label红色字体，提示使用者着重关注
       defaultable - 标识为默认项，一般组件渲染成默认项方式，提示使用者可以不修改对应值
       emptiable - 标识为留空项，一般组件渲染成留空项方式，提示使用者对应值可以为空
       editable - 标识为不可编辑项，一般组件渲染成不可编辑项方式，如果false则把组件灰掉，提示使用者对应值不可编辑

## 脚手架创建规则
    1. 一个Generator类对应一个template模板文件
    2. 模板文件(*.template)有如下两种放置方式
       2.1 模板文件resources/template目录下(templateDirectory = "template")，目录结构参照第一张图片
       2.2 Generator类和对应的模板文件必须放在同一个目录下(下面例子中，指定templateDirectory = null)，目录结构参照第二张图片   
![Alt text](https://github.com/Nepxion/Skeleton/blob/master/Template1.jpg)
![Alt text](https://github.com/Nepxion/Skeleton/blob/master/Template2.jpg)

## 本地使用方式
运行com.nepxion.skeleton.test.GeneratorTest.java类，可在本地创建脚手架文件
```java
package com.nepxion.skeleton.test;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.generator.GeneratorService;
import com.nepxion.skeleton.property.SkeletonProperties;

public class GeneratorTest {
    public static void main(String[] args) {
        try {
            // 创建文件的输出的路径
            String generatePath = "E:/Download/Skeleton/";

            // 模板文件所在的前置目录名
            String templateDirectory = "template";
            // String templateDirectory = null;

            // 描述规则的配置文件所在的路径
            String propertiesPath = "config/skeleton-data.properties";

            // 构造全局配置文件对象
            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath);

            // 输出脚手架文件
            GeneratorService generatorService = new GeneratorService();
            generatorService.generator(generatePath, templateDirectory, skeletonProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
## Spring Cloud使用方式

### Spring Cloud配置文件(application.properties)
```java
# Spring cloud config
spring.application.name=skeleton-generator-spring-cloud-service
server.port=2222

eureka.instance.lease-renewal-interval-in-seconds=10
eureka.instance.lease-expiration-duration-in-seconds=30

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=false

eureka.client.serviceUrl.defaultZone=http://cluster-1:1111/eureka/,http://cluster-2:1112/eureka/,http://cluster-3:1113/eureka/

# Skeleton config
# 模板文件所在的前置目录名
skeleton.template.prefix.template.directory=template
# 在前端下载zip包名
skeleton.generate.file.name=spring-cloud-skeleton
# 在后端创建zip包的目录
skeleton.generate.path=E:/Download/Skeleton/

# Swagger config
swagger.service.base.package=com.nepxion.skeleton
swagger.service.description=Skeleton Spring Cloud Restful APIs
swagger.service.version=1.0.0
swagger.service.license=Apache License 2.0
swagger.service.license.url=http://www.apache.org/licenses/LICENSE-2.0
swagger.service.contact.name=Haojun Ren
swagger.service.contact.url=https://github.com/Nepxion/Skeleton
swagger.service.contact.email=1394997@qq.com
```

### Spring Cloud接口
    1. 根据配置文件进行界面驱动的元数据接口
    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    public List<SkeletonGroup> getMetaData()

    返回JSON格式的文件，简单介绍一下格式：
    [
      {
        "key": "project", // 组所对应的唯一Key
        "label": "工程配置", // 组的标签
        "description": "工程配置", // 组的描述
        "column": 1,
        "entityList": [
          {
            "key": "moduleName", // 组件所对应的唯一Key
            "label": "工程的模块名", // 组件的标签
            "description": "moduleName", // 组件的描述
            "note": "【必改项】首字母必须小写，中间只允许出现“-”", // 组件的使用提示
            "value": "sales", // 组件内容
            "type": "TEXTFIELD", // 组件类型，包括TEXTFIELD，CHECKBOX，RADIO，COMBOBOX
            "options": null, // 对应项如果是下来菜单(COMBOBOX)方式的时候，里面的值列表，可以为null
            "highlightable": true, // 渲染成高亮方式
            "defaultable": false, // 渲染成默认项方式
            "emptiable": false, // 渲染成留空项方式
            "editable": true // 渲染成不可编辑项方式
          }
        ]
      }
    ]   

    2. 下载脚手架Zip文件的接口，返回Zip文件的byte数组类型，Body的内容为src\main\resources\config\skeleton-data.properties 
    @RequestMapping(value = "/downloadBytes", method = RequestMethod.POST)
    public byte[] downloadBytes(@RequestBody String config)

    3. 下载脚手架Zip文件的接口，返回Zip文件的ResponseEntity类型
    @RequestMapping(value = "/downloadResponse", method = RequestMethod.POST)
    public ResponseEntity<Resource> downloadResponse(@RequestBody String config)