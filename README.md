# Skeleton Generator
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Nepxion/Skeleton/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/Nepxion/Skeleton.svg?branch=master)](https://travis-ci.org/Nepxion/Skeleton)

Nepxion Skeleton是一款基于FreeMarker的对任何文本格式的代码和文件的生成器，可以轻松快速实现对框架代码的一键创建，并提供Docker化的基于Spring Cloud的脚手架一键生成

## 简介
支持如下功能

    1. 严格遵照Maven结构进行脚手架编排
    2. 支持任何文件文件的逆向创建，包括Java类文件，配置文件，脚本文件，XML文件，YAML文件等
    3. 使用者只需要关注模板原型文件的编辑（遵循FreeMarker语法），并设置动态变量
    4. 使用者根据模板原型文件创建Generator类，进行动态创建和替换
    5. 采用插件式部署方式，可以多个脚手架系统部署在同一个平台上，也可以一个平台部署一个脚手架系统，自由灵活，动态切换
    6. 基于Spring Cloud的调用
    7. 支持Docker化一键部署
    8. 支持Swagger
    9. 支持在线生成代码并下载

## 在线创建

    1. 获取脚手架代码 
       在浏览器里输入http://skeleton.springcloud.cn访问，填入相关参数，点“生成工程”，保存代码压缩包。解压后，可获取到脚手架代码
    2. 代码运行
       2.1 在IDE里运行：导入IDE，即可运行，脚手架代码，代码执行顺序一般为EurekaApplication，ServerApplication，ClientApplication，如图1
       2.2 在Docker里运行：执行三个脚本，即可一键部署到Docker容器里，脚本执行顺序必须为install-eureka-docker，install-server-docker，install-client-docker，如图2
       2.3 提供Rpc和Rest两种测试(启动ClientApplication服务即可启动定时测试)，提供正常调用和熔断调用场景(停止ServerApplication服务即可)
    3. 组件集成
       3.1 集成Spring Cloud基础组件，包括Eureka，Ribbon，Feign，Hystrix等
       3.2 集成Swagger组件

图1

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Generator1.jpg)

图2

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Generator2.jpg)

图3

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Generator3.jpg)

图4

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Generator4.jpg)

图5

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Generator5.jpg)

## 本地部署
### 服务端部署

    1. 部署在IDE
       导入IDE，运行skeleton-service下的SkeletonApplication即可
    2. 部署到Docker
       2.1 Win10配置参考：https://github.com/Nepxion/Thunder/tree/master/thunder-spring-boot-docker-example中“Win10 Docker部署”->“部署前准备工作”
       2.2 Mac配置参考：http://www.liumapp.com/articles/2017/12/27/1514347974172.html
       2.3 在根目录下执行install-docker.bat或者install-docker.sh里的语句，一键创建镜像和容器
    3. 运行Swagger检验是否工作
       在浏览器里输入http://localhost:2222/swagger-ui.html访问

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Docker.jpg)
![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Swagger.jpg)

### 客户端部署

    1. 在skeleton-ui目录中执行npm install，结束后执行npm run dev，具体参考该目录下的README.md
    2. 在浏览器里输入http://localhost:7777/访问

## 二次开发
### 介绍

    1. skeleton-engine是脚手架的生成引擎，不依赖Spring环境，你只需要在Java环境下可以对任何格式的文本文件进行模板化创建
    2. skeleton-framework是基于Spring环境的脚手架框架，并抽象在Spring环境下的配置和行为
    3. skeleton-service是脚手架的平台，是多个脚手架生成插件的汇集之处
    4. skeleton-plugin-springcloud是基于Spring Cloud的脚手架生成插件，您可以扩展出skeleton-plugin-dubbo，skeleton-plugin-motan等
    5. skeleton-ui是基于vue的脚手架界面

图6

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Architecture.jpg)

### 配置

    1. skeleton-data.properties，见skeleton-plugin-springcloud/src/main/resources/springcloud/config/skeleton-data.properties
       用来描述模板文件的全局配置值，里面的值替换模板文件里的动态变量(用${}表示)，脚手架生成需要依赖这个文件
       配置文件中，“工程配置”下的productName和basePackage是必需的，其他字段可自己随便定义，建议驼峰形式命名

    2. skeleton-description.xml，见skeleton-plugin-springcloud/src/main/resources/springcloud/config/skeleton-description.xml
       用来描述模界面驱动的数据结构，渲染和布局组件，它里面定义的组件里的value值则取值于skeleton-data.properties
       分为Group和Entity结构，一个Group包含多个Entity

    3. skeleton-context.properties，见skeleton-plugin-springcloud/src/main/resources/springcloud/config/skeleton-context.properties
       用来配置脚手架全局上下文参数

    4. 三个配置文件它的上级目录必须为config

### 规则

    1. 一个Generator类对应一个template模板文件
    2. 提供SkeletonFileGenerator和SkeletonJavaGenerator两种方式，前者可以生成任何类型的文本文件，后者因为Java文件相对比较特殊，所以做了一些封装
    3. 模板文件(*.template)有如下两种放置方式
       3.1 模板文件resources/template目录下(模板文件所在的前置目录名必须设置为"template"，在application.properties可以修改)，目录结构参照第一张图片
       3.2 Generator类和对应的模板文件必须放在同一个目录下(模板文件所在的前置目录名必须设置为null)，目录结构参照第二张图片   
![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Template1.jpg)
![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Template2.jpg)

### 插件

    1. 如果多个脚手架系统部署在同一个平台上，需要采用独立工程的方式，同时避免同名类和同名配置文件冲突，规定如下，见图7
       1.1 工程名应该是xxx-plugin-[插件名]
       1.2 包名应该是xx.xx.xx...plugin.[插件名]
       1.3 配置文件目录应该是resources/[插件名]，下面放config和template
       1.4 脚手架总入口(实现SkeletonService)，上面加注解@SkeletonPlugin(name="[插件名]")
       1.5 上述四个插件名必须统一，在本项目中，我们知道插件名叫“springcloud”。按照这种规则，我们可以扩展出多个脚手架插件，例如skeleton-plugin-dubbo，skeleton-plugin-motan等
       1.6 插件名，将被SkeletonController接口中用到，见“Spring Cloud接口”章节

    2. 如果一个脚手架系统部署在一个平台上，那么则没有那么多讲究，规定如下，见图8
       2.1 脚手架总入口(实现SkeletonService)，上面加注解@SkeletonPlugin

图7

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Plugin1.jpg)

图8

![Alt text](https://github.com/Nepxion/Skeleton/blob/master/skeleton-doc/Plugin2.jpg)

### 示例

#### 本地使用方式
运行skeleton-plugin-springcloud/src/test/java/com.nepxion.skeleton.plugin.springcloud.SkeletonTest.java，可在本地创建脚手架文件，具体使用方式，参考该类里的中文注释

#### Spring Cloud使用方式

Spring Cloud配置文件，见skeleton-service/src/main/resources/application.properties

Spring Cloud接口，见skeleton-framework/src/main/java/com.nepxion.skeleton.framework.controller.SkeletonController.java

    1. 获取脚手架插件列表接口
    @RequestMapping(value = "/getPlugins", method = RequestMethod.GET)
    public List<String> getPlugins();

    2. 获取默认界面驱动的元数据接口
    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    public List<SkeletonGroup> getMetaData();
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
            "key": "productName", // 组件所对应的唯一Key
            "label": "产品名", // 组件的标签
            "description": "", // 组件的描述
            "note": "【必改项】首字母必须小写，中间只允许出现“-”", // 组件的使用提示
            "value": "sales", // 组件内容
            "type": "TEXTFIELD", // 组件类型，包括TEXTFIELD(默认)，CHECKBOX，RADIO，COMBOBOX，EDITABLE_COMBOBOX
            "options": null, // 对应项如果是下来菜单(COMBOBOX，EDITABLE_COMBOBOX)方式的时候，里面的值列表，可以为null
            "highlightable": true, // 标识为高亮项，一般组件渲染成高亮方式，例如Label红色字体，提示使用者着重关注
            "defaultable": false, // 标识为默认项，一般组件渲染成默认项方式，提示使用者可以不修改对应值
            "emptiable": false, // 标识为留空项，一般组件渲染成留空项方式，提示使用者对应值可以为空
            "editable": true // 标识为不可编辑项，一般组件渲染成不可编辑项方式，如果false则把组件灰掉，提示使用者对应值不可编辑
          }
        ]
      }
    ]

    3. 根据脚手架名称，获取对应的界面驱动的元数据接口。另一个不带skeletonName参数的接口，是默认获取方式，当脚手架非插件方式存在的时候，调用它。4. 5.的2个接口都存在这样的默认方式
    @RequestMapping(value = "/getMetaData/{skeletonName}", method = RequestMethod.GET)
    public List<SkeletonGroup> getMetaData(@PathVariable(value = "skeletonName") String skeletonName);

    4. 根据脚手架名称，下载脚手架Zip文件的接口，返回Zip文件的byte数组类型，配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容
    @RequestMapping(value = "/downloadBytes/{skeletonName}", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "", response = byte[].class, httpMethod = "POST")
    public byte[] downloadBytes(@PathVariable(value = "skeletonName") String skeletonName, @RequestBody String config);

    5. 根据脚手架名称，下载脚手架Zip文件的接口，返回Zip文件的ResponseEntity类型，配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容
    @RequestMapping(value = "/downloadResponse/{skeletonName}", method = RequestMethod.POST)
    public ResponseEntity<Resource> downloadResponse(@PathVariable(value = "skeletonName") String skeletonName, @RequestBody String config);