package com.dev999.maven.genertation.service;

/**
 * 生成 代码需要的公共参数集合
 * @author helecong
 * @date 2018/12/12
 */
public class CommonGeneratingParam {

    /**
     * 是否生成controller层代码.默认为true
     */
    private boolean generationController = true;

    /**
     * 是否生成service层代码.默认为true
     */
    private boolean generationService = true;

    /**
     * 是否添加默认的swagger注解
     */
    private boolean addSwaggerAPIAnnotation = true;

    public boolean isGenerationController() {
        return generationController;
    }

    public void setGenerationController(boolean generationController) {
        this.generationController = generationController;
    }

    public boolean isGenerationService() {
        return generationService;
    }

    public void setGenerationService(boolean generationService) {
        this.generationService = generationService;
    }

    public boolean isAddSwaggerAPIAnnotation() {
        return addSwaggerAPIAnnotation;
    }

    public void setAddSwaggerAPIAnnotation(boolean addSwaggerAPIAnnotation) {
        this.addSwaggerAPIAnnotation = addSwaggerAPIAnnotation;
    }
}
