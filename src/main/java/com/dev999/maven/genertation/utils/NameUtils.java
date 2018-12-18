package com.dev999.maven.genertation.utils;

/**
 * 名称生成  基于实体类
 * @author helecong
 * @date 2018/12/18
 */
public class NameUtils {

    public static String serviceInterfaceName(String entityName){
        return "I"+entityName+"Service";
    }

    public static String serviceInterfaceFullPath(String servicePackagePath,String entityName){
        return servicePackagePath+"."+entityName.toLowerCase()+"."+serviceInterfaceName(entityName);
    }

    public static String serviceVariableName(String entityName){
        return StringUtils.firstNameLower(entityName)+"Service";
    }

    public static String serviceName(String entityName){
        return entityName+"ServiceImpl";
    }

    public static String controllerName(String entityName){
        return entityName+"ControllerImpl";
    }

    public static String controllerInterfaceName(String entityName){
        return "I"+entityName+"Controller";
    }

    public static String requestClassName(String name){
        return StringUtils.firstNameUpper(name)+"Request";
    }

    public static String responseClassName(String name){
        return StringUtils.firstNameUpper(name)+"Response";
    }

    public static String controllerPackagePath(String controllerPackagePath, String entityName) {
        return controllerPackagePath+"."+entityName.toLowerCase()+".impl";
    }

    public static String controllerInterfacePackagePath(String controllerPackagePath, String entityName) {
        return controllerPackagePath+"."+entityName.toLowerCase();
    }

    public static String controllerInterfaceFullPath(String controllerPackagePath,String entityName){
        return controllerPackagePath+"."+entityName.toLowerCase()+"."+controllerInterfaceName(entityName);
    }
}
