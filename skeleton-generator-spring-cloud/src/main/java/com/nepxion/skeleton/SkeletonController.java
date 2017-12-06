package com.nepxion.skeleton;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.skeleton.entity.SkeletonGroup;
import com.nepxion.skeleton.example.server.java.MyApplicationClassGenerator;
import com.nepxion.skeleton.example.service.resources.MybatisGeneratorXmlGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.transport.SkeletonConfigTransport;
import com.nepxion.skeleton.transport.SkeletonDataTransport;

@RestController
@Api(value = "脚手架操作")
public class SkeletonController {
    public static final String SPRING_CLOUD_SKELETON = "spring-cloud-skeleton-";

    @Value("${skeleton.generate.path}")
    private String skeletonGeneratePath;

    private SkeletonDataTransport dataTransport;
    private SkeletonConfigTransport configTransport;

    @PostConstruct
    private void initialize() {
        dataTransport = new SkeletonDataTransport() {
            @Override
            public void generate(String path, SkeletonProperties skeletonProperties) throws Exception {
                new MyApplicationClassGenerator(path, "server", skeletonProperties).generate();
                new MybatisGeneratorXmlGenerator(path, "service", skeletonProperties).generate();
            }
        };

        configTransport = new SkeletonConfigTransport();
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "下载脚手架Zip文件的接口，返回Zip文件的byte数组方式", response = byte[].class, httpMethod = "POST")
    public byte[] download(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/skeleton-data.properties的内容", required = true) String config) {
        return dataTransport.download(skeletonGeneratePath, SPRING_CLOUD_SKELETON, config);
    }

    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    @ApiOperation(value = "获取元数据接口", notes = "获取根据配置文件进行界面驱动的元数据接口", response = Map.class, httpMethod = "GET")
    public Map<String, SkeletonGroup> getMetaData() {
        return configTransport.getMetaData();
    }
}