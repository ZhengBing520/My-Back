package com.zb.generator.code.config;



import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Created by bzheng on 2019/11/26.
 */
public class CodeConfig {

    public static String  projectPath = System.getProperty("user.dir");

    private static String defaultPackagePrefix = "com.zb.";

    /**
     *
     * @param projectPath 项目根目录
     * @param moduleName 模块名
     * @param packageName 包名前缀
     * @return
     */
    public static String buildTargetPath(String projectPath,String moduleName, String packageName){
        StringBuilder builder = new StringBuilder();
        builder.append(projectPath);
        builder.append(File.separator);
        if(!StringUtils.isBlank(moduleName)) {
            builder.append(moduleName);
            builder.append(File.separator);
        }
        builder.append("src");
        builder.append(File.separator);
        builder.append("main");
        builder.append(File.separator);
        builder.append("java");
        builder.append(File.separator);
        while (StringUtils.isNotBlank(packageName) && packageName.contains(".")){
            packageName = packageName.replace(".", File.separator);
        }
        builder.append(packageName);
        return builder.toString();
    }

    /**
     *
     * @param moduleName 模块名
     * @return
     */
    public static String buildTargetPath(String moduleName, String packageName){
        return buildTargetPath(projectPath, moduleName, packageName);
    }

    /**
     *
     * @param moduleName 模块名
     * @return
     */
    public static String buildTargetPath(String moduleName){
        return buildTargetPath(projectPath, moduleName, getPackagePrefix() + moduleName.toLowerCase());
    }

    public static String getPackagePrefix() {
        return defaultPackagePrefix;
    }
}
