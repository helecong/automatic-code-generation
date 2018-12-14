package com.dev999.maven.genertation.service;

import com.dev999.maven.genertation.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * 服务端文件处理
 * @author helecong
 * @date 2018/12/14
 */
public class ServiceFileDispose {
    private static ServiceFileDispose serviceFileDispose;

    private static String INTERFACE_PRFIX = "I";
    private static String INTERFACE_SUFFIX = "service";

    private ServiceFileDispose(){}

    public static ServiceFileDispose getServiceFileDispose(){
        if(serviceFileDispose == null){
            serviceFileDispose = new ServiceFileDispose();
        }
        return serviceFileDispose;
    }

    /**
     * 创建service层文件
     * @param srcRootPath
     * @param basePagePath
     * @param entityNames
     */
    public void createServices(String srcRootPath, String basePagePath, List<String> entityNames){
        for (String entityName : entityNames){
            createService(srcRootPath,basePagePath,entityName);
        }
    }

    /**
     * 创建单个service文件
     * @param basePagePath
     * @param entityName
     */
    private void createService(String srcRootPath,String basePagePath, String entityName) {
        String servicePagePath = getServicePagePath(basePagePath,entityName);
        createInterfaceFile(srcRootPath,servicePagePath,entityName);
    }

    /**
     * 创建接口文件
     * @param srcRootPath
     * @param servicePagePath
     * @param entityName
     */
    private void createInterfaceFile(String srcRootPath, String servicePagePath, String entityName) {

        File file = FileUtils.createFile(srcRootPath+servicePagePath.replaceAll("\\.","/")+INTERFACE_PRFIX+entityName+INTERFACE_SUFFIX);

    }

    /**
     * 获取service包路径
     * @param basePagePath
     * @param entityName
     * @return
     */
    private String getServicePagePath(String basePagePath, String entityName) {
        return basePagePath+"."+entityName.toLowerCase();
    }

}
