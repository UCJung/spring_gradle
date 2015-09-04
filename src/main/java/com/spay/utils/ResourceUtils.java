package com.spay.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.spay.exception.SPayException;

public class ResourceUtils {

    private static final String PACKAGE_PATTERN = "classpath*:com/spay/**/";
    private static final String PACKAGE_PREFIX = "com.spay";

    public static Resource[] getDistinctResources(Resource[] resources) {
        List<Resource> resourceList = new ArrayList<Resource>();
        Set<String> fileNameSet = new HashSet<String>();
        for (Resource resource : resources) {
            if (!fileNameSet.contains(resource.getFilename())) {
                fileNameSet.add(resource.getFilename());
                resourceList.add(resource);
            }
        }
        return resourceList.toArray(new Resource[resourceList.size()]);
    }

    public static Class<?> getClassTypeByName(String className) throws IOException, ClassNotFoundException {
        PathMatchingResourcePatternResolver typeAliasPackageResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources;
        resources = typeAliasPackageResolver.getResources(PACKAGE_PATTERN + className + ".class");

        if (resources == null || resources.length == 0) {
            throw new SPayException(className + " class does not exist");
        } else if (resources.length > 1) {
            throw new SPayException(className + " class name is duplicated");
        } else {
            Resource resource = resources[0];
            String absolutePath = resource.getURI().toString();
            String clazzStr = PACKAGE_PREFIX
                + StringUtils.substringAfter(absolutePath.replace("/", "."), PACKAGE_PREFIX).replace(".class", "");
            Class<?> javaTypeClass = ClassUtils.getClass(clazzStr);
            return javaTypeClass;
        }
    }
}
