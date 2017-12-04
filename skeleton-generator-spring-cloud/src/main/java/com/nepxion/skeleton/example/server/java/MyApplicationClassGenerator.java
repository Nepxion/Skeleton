package com.nepxion.skeleton.example.server.java;

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