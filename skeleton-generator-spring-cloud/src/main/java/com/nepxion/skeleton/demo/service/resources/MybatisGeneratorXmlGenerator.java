package com.nepxion.skeleton.demo.service.resources;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.entity.SkeletonFileType;
import com.nepxion.skeleton.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

public class MybatisGeneratorXmlGenerator extends SkeletonFileGenerator {
    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param skeletonProperties 全局配置文件对象
     */
    public MybatisGeneratorXmlGenerator(String generatePath, String projectType, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, MybatisGeneratorXmlGenerator.class, skeletonProperties);
    }

    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param baseTemplatePath 模板文件的等级路径
     * @param fileType 创建的文件类型
     * @param skeletonProperties 全局配置文件对象
     */
    public MybatisGeneratorXmlGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonFileType fileType, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, baseTemplatePath, fileType, skeletonProperties);
    }

    /**
     * 设置文件名
     */
    @Override
    protected String getFileName() {
        return "mybatis-generator.xml";
    }

    /**
     * 设置模板名
     */
    @Override
    protected String getTemplateName() {
        return "mybatis-generator.xml.template";
    }

    /**
     * 设置文件的输出路径
     */
    @Override
    protected String getOutputPath() {
        return super.getOutputPath() + SkeletonConstant.MAIN_RESOURCES_FILE_PATH;
    }

    /**
     * 设置文件创建的所依赖数据模型，主要做动态变量到原型模板的替换（任何文本的替换都支持）
     */
    @Override
    protected Object getDataModel() {
        return null;
    }
}