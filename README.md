# Skeleton Generator
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)

Nepxion Skeleton是一款基于FreeMarker的对任何文本格式的代码和文件的生成器，可以轻松快速实现对框架代码的一键创建，并提供Docker化的基于Spring Cloud的脚手架一键生成

## 简介
支持如下功能

    1. 严格遵照Maven结构进行脚手架编排
    2. 支持任何文件文件的逆向创建，包括Java类文件，配置文件，脚本文件，XML文件，YAML文件等
    3. 使用者只需要关注模板原型文件的编辑（遵循FreeMarker语法），并设置动态变量
    4. 使用者根据模板原型文件创建Generator类，进行动态创建和替换
    5. 基于Spring Cloud的调用
    6. 支持Docker化一键部署
    7. 支持Swagger
    8. 支持在线生成代码并下载

## 在线访问

    1. 在浏览器里输入http://start.springcloud.cn/访问
    2. 解压后，可获取到脚手架代码
       2.1 导入IDE，即可运行
       2.2 执行脚本，即可一键部署到Docker容器里
       2.3 集成Spring Cloud主要组件
       2.4 支持Swagger操作

## 本地部署
### 服务端部署

    1. 部署在IDE
       导入IDE，运行skeleton-spring-cloud下的SkeletonApplication即可
    2. 部署到Docker
       2.1 Win10配置参考：https://github.com/Nepxion/Thunder/tree/master/thunder-spring-boot-docker-example中“Win10 Docker部署”->“部署前准备工作”
       2.2 Mac配置参考：http://www.liumapp.com/articles/2017/12/27/1514347974172.html
       2.3 在根目录下执行install-docker.bat或者install-docker.sh里的语句，一键创建镜像和容器
    3. 运行Swagger检验是否工作
       在浏览器里输入http://localhost:2222/swagger-ui.html访问

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/Docker.jpg)
![Alt text](https://github.com/Nepxion/Skeleton/blob/master/Swagger.jpg)

### 客户端部署

    1. 下载界面代码 https://github.com/SpringCloud/spring-cloud-codegen
    2. 在代码目录中执行npm install，结束后执行npm start
    3. 在浏览器里输入http://localhost:8080/#/codegen访问

## 二次开发
### 介绍

    1. skeleton-engine是脚手架的生成引擎，不依赖Spring环境，你只需要在Java环境下可以对任何格式的文本文件进行模板化创建
    2. skeleton-framework是基于Spring环境的脚手架框架，并抽象在Spring环境下的配置和行为
    3. skeleton-spring-cloud是最终的“业务”实现，基于Spring Cloud，如果你想二次开发，引入skeleton-framework即可，然后按照skeleton-spring-cloud的结构和代码进行开发

### 配置

    1. skeleton-data.properties，见skeleton-spring-cloud/src/main/resources/config下
       用来描述模板文件的全局配置值，里面的值替换模板文件里的动态变量(用${}表示)，脚手架生成需要依赖这个文件

    2. skeleton-description.xml，见skeleton-spring-cloud/src/main/resources/config下
       用来描述模界面驱动的数据结构，渲染和布局组件，它里面定义的组件里的value值则取值于skeleton-data.properties
       分为Group和Entity结构，一个Group包含多个Entity

### 规则
    1. 一个Generator类对应一个template模板文件
    2. 提供SkeletonFileGenerator和SkeletonJavaGenerator两种方式，前者可以生成任何类型的文本文件，后者因为Java文件相对比较特殊，所以做了一些封装
    3. 模板文件(*.template)有如下两种放置方式
       3.1 模板文件resources/template目录下(模板文件所在的前置目录名必须设置为"template"，在application.properties可以修改)，目录结构参照第一张图片
       3.2 Generator类和对应的模板文件必须放在同一个目录下(模板文件所在的前置目录名必须设置为null)，目录结构参照第二张图片   
![Alt text](https://github.com/Nepxion/Skeleton/blob/master/Template1.jpg)
![Alt text](https://github.com/Nepxion/Skeleton/blob/master/Template2.jpg)

### 示例

#### 本地使用方式
运行com.nepxion.skeleton.springcloud.generator.SkeletonTest.java类，可在本地创建脚手架文件
```java
package com.nepxion.skeleton.springcloud.generator;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;
import com.nepxion.skeleton.framework.service.SkeletonService;
import com.nepxion.skeleton.springcloud.service.SkeletonServiceImpl;

public class SkeletonTest {
    public static void main(String[] args) {
        try {
            // 创建文件的输出的路径
            // 放在操作系统的临时目录下
            String generatePath = SkeletonUtil.getTempGeneratePath();
            // String generatePath = "E:/Download/skeleton/";

            // 如何理解prefixTemplatePath和reducedTemplatePath含义？
            // FreeMarker规定，模板文件必须放在classpath下，即以com/...开头的路径为其classpath
            // 1. 有需求要求，模板文件移动到resources中，并希望放在template/com/a/b/c...，那么template就是prefixTemplatePath，模板的前置路径
            // 2. 有需求要求，模板文件路径如果template/com/a/b/c/...，觉得路径太长，那么a/b/c/就是reducedTemplatePath，模板路径缩减，把这部分裁剪掉           
            // 3. 如果把模板文件和Generator类放在一起，则prefixTemplatePath和reducedTemplatePath同时为null即可

            // 模板文件所在的前置路径
            String prefixTemplatePath = "template";
            // String prefixTemplatePath = null;

            // 模板路径缩减
            String reducedTemplatePath = "com/nepxion/skeleton/springcloud/generator/";
            // String reducedTemplatePath = null;

            // 描述规则的配置文件所在的路径
            // 配置文件含中文，stringEncoding必须为GBK，readerEncoding必须为UTF-8，文本文件编码必须为ANSI
            String propertiesPath = "config/skeleton-data.properties";

            // 构造全局配置文件对象
            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath, SkeletonConstant.ENCODING_GBK, SkeletonConstant.ENCODING_UTF_8);

            // 输出脚手架文件
            SkeletonService skeletonService = new SkeletonServiceImpl();
            skeletonService.generate(generatePath, prefixTemplatePath, reducedTemplatePath, skeletonProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### Spring Cloud使用方式

Spring Cloud配置文件(application.properties)
```java
# Spring cloud config
spring.application.name=skeleton-spring-cloud
server.port=2222

eureka.instance.lease-renewal-interval-in-seconds=10
eureka.instance.lease-expiration-duration-in-seconds=30

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=false

eureka.client.serviceUrl.defaultZone=http://cluster-1:1111/eureka/,http://cluster-2:1112/eureka/,http://cluster-3:1113/eureka/

# Skeleton config
# 模板文件所在的前置路径
skeleton.prefix.template.path=template
# 模板文件所在的路径根据传入的Key动态切换，不设置则表示不切换
skeleton.dynamic.template.path.key=
# 模板路径缩减，考虑到模板路径和类路径必须一致，会导致路径太长，可以缩减掉一部分
skeleton.reduced.template.path=com/nepxion/skeleton/springcloud/generator/
# 在前端下载zip包名
skeleton.generate.file.name=spring-cloud-skeleton
# 在后端生成zip包的放置路径，不设置则放在操作系统的临时目录下
skeleton.generate.path=

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

Spring Cloud接口

    1. 根据配置文件进行界面驱动的元数据接口
    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    public List<SkeletonGroup> getMetaData()

    返回JSON格式的文件，简单介绍一下格式：
    [
      {
        "key": "project", // 组所对应的唯一Key
        "label": "工程配置", // 组的标签
        "description": "工程配置", // 组的描述
        "type": "MIX_GROUP", // 组的类型，包括MIX_GROUP(默认，组里可以放任何种类的组件，混合组)，CHECKBOX_GROUP(组里只能放CHECKBOX)，RADIO_GROUP(组里只能放RADIO)，COMBOBOX_GROUP(组里只能放COMBOBOX)
        "layout": "VERTICAL", // 组的布局，包括VERTICAL(默认，组里组件以垂直方向布局)，HORIZONTAL(默认，组里组件以水平方向布局)
        "titledBorder": "true", // 是否需要显示组标题(默认显示)
        "entityList": [
          {
            "key": "moduleName", // 组件所对应的唯一Key
            "label": "工程的模块名", // 组件的标签
            "description": "moduleName", // 组件的描述
            "note": "【必改项】首字母必须小写，中间只允许出现“-”", // 组件的使用提示
            "value": "sales", // 组件内容
            "type": "TEXTFIELD", // 组件类型，包括TEXTFIELD(默认)，CHECKBOX，RADIO，COMBOBOX
            "options": null, // 对应项如果是下来菜单(COMBOBOX)方式的时候，里面的值列表，可以为null
            "highlightable": true, // 标识为高亮项，一般组件渲染成高亮方式，例如Label红色字体，提示使用者着重关注
            "defaultable": false, // 标识为默认项，一般组件渲染成默认项方式，提示使用者可以不修改对应值
            "emptiable": false, // 标识为留空项，一般组件渲染成留空项方式，提示使用者对应值可以为空
            "editable": true // 标识为不可编辑项，一般组件渲染成不可编辑项方式，如果false则把组件灰掉，提示使用者对应值不可编辑
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