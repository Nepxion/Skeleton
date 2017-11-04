package com.nepxion.skeleton.springcloud.server.java;

/**
 * <p>Title: Nepxion Skeleton Generator Spring Cloud</p>
 * <p>Description: Nepxion Skeleton Generator For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.IOException;
import java.util.Map;

import com.nepxion.skeleton.config.SkeletonConfig;
import com.nepxion.skeleton.constant.SkeletonConstants;
import com.nepxion.skeleton.context.SkeletonContext;
import com.nepxion.skeleton.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

import freemarker.template.Template;

public class MyApplicationClassGenerator extends SkeletonJavaGenerator {
    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param skeletonProperties 全局配置文件对象
     * @param skeletonContext 上下文对象
     */
    public MyApplicationClassGenerator(String generatePath, SkeletonProperties skeletonProperties, SkeletonContext skeletonContext) {
        super(generatePath, skeletonProperties, skeletonContext);
    }

    // 设置Java类的包路径，如果没特殊处理，则按照默认顶级包路径来处理，不需要Override该方法
    /*@Override
    protected String getPackage() {
        return super.getPackage() + "." + "abc";
    }*/

    // 设置Java类名
    @Override
    protected String getClassName() {
        return "MyApplication";
    }

    // 设置Java类的输出路径，如果没特殊处理，则按照默认输出路径来处理，不需要Override该方法
    /*@Override
    protected String getOutputPath() {
        return super.getOutputPath() + "/" + "xyz";
    }*/

    // 设置Java类到main目录下，还是在test目录下
    @Override
    protected boolean isMainCode() {
        return true;
    }

    // 设置Java原型模板对象
    @Override
    protected Template getTemplate() throws IOException {
        SkeletonConfig skeletonConfig = skeletonContext.getJavaConfig();

        return skeletonConfig.getTemplate("MyApplication.java.template");
    }

    // 设置Java类创建的数目模型，主要做动态变量到原型模板的替换（任何文本的替换都支持）
    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = generateDataModel();
        // 注意：根据freemarker的规范，dataModel中的key似乎只能支持字母和数字，不支持符号，例如MyContextAware.ClassPath，MyContextAware-ClassPath都会抛错
        dataModel.put(SkeletonConstants.PACKAGE, getPackage());
        dataModel.put("MyContextAwareClassPath", "com.nepxion.matrix.test.simple.context.MyContextAware");
        dataModel.put("MyServiceClassPath", "com.nepxion.matrix.test.simple.service.MyService");

        return dataModel;
    }
}