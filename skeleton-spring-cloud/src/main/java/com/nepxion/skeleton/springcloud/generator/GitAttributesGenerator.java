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

import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

public class GitAttributesGenerator extends SkeletonFileGenerator {
    public GitAttributesGenerator(String generatePath, String projectType, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, GitAttributesGenerator.class, skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return ".gitattributes";
    }

    @Override
    protected String getTemplateName() {
        return "file.gitattributes.template";
    }

    @Override
    protected Object getDataModel() {
        return null;
    }
}