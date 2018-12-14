package com.dev999.maven.genertation.service;

import java.util.List;

/**
 * 公共生成代码处理
 *
 * 统一处理
 *
 * @author helecong
 * @date 2018/12/12
 */
public class CommonGeneratingDispose extends CommonGeneratingParam {

    private static ServiceFileDispose serviceFileDispose = ServiceFileDispose.getServiceFileDispose();
    /**
     * 开始创建
     */
    public void startCreate(){

        //TODO 创建service
        createServices();

        //TODO 创建controller

    }

    /**
     * 创建service层代码
     */
    private void createServices() {
        if(!generationService){
            return;
        }

        serviceFileDispose.createServices(targetProject,servicePackagePath,entityNames);

    }


}
