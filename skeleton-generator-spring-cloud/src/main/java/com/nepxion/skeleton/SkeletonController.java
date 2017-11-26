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

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.skeleton.demo.server.java.MyApplicationClassGenerator;
import com.nepxion.skeleton.demo.service.resources.MybatisGeneratorXmlGenerator;
import com.nepxion.skeleton.entity.SkeletonGroup;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.transport.SkeletonConfigTransport;
import com.nepxion.skeleton.transport.SkeletonDataTransport;

@RestController
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
    public byte[] download(@RequestBody String config) {
        return dataTransport.download(skeletonGeneratePath, SPRING_CLOUD_SKELETON, config);
    }

    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    public Map<String, SkeletonGroup> getMetaData() {
        return configTransport.getMetaData();
    }
}