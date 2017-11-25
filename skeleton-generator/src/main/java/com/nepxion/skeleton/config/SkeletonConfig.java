package com.nepxion.skeleton.config;

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
import com.nepxion.skeleton.model.CharacterCaseModel;

import freemarker.template.Configuration;

public class SkeletonConfig extends Configuration {
    public SkeletonConfig(String templatePath) {
        super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        // 指定模板所在的classpath目录
        setClassForTemplateLoading(SkeletonConfig.class, templatePath);

        // 指定文件编码
        setDefaultEncoding(SkeletonConstant.ENCODING_UTF_8);

        // 添加一个“宏”共享变量，用来将属性名首字母大写
        setSharedVariable(SkeletonConstant.UPPER_CASE, new CharacterCaseModel(true));

        // 添加一个“宏”共享变量，用来将属性名首字母小写
        setSharedVariable(SkeletonConstant.LOWER_CASE, new CharacterCaseModel(false));
    }
}