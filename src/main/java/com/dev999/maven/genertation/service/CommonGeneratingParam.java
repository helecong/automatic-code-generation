package com.dev999.maven.genertation.service;

import org.eclipse.aether.util.StringUtils;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 生成 代码需要的公共参数集合
 * @author helecong
 * @date 2018/12/12
 */
public class CommonGeneratingParam {

    /**
     * 是否生成controller层代码.默认为true
     */
    protected boolean generationController = true;

    /**
     * 是否生成service层代码.默认为true
     */
    protected boolean generationService = true;

    /**
     * 是否添加默认的swagger注解
     */
    protected boolean addSwaggerAPIAnnotation = true;

    /**
     * controller 层包路径
     */
    protected String controllerPackagePath = "";

    /**
     * service 层包路径
     */
    protected String servicePackagePath = "";

    /**
     * 实体类集合
     */
    protected List<String> entityNames;

    /**
     * 项目代码更目录
     */
    protected String targetProject;

    /**
     * 实体类存放包路径
     */
    protected String targetEntityPackage;


    private static final String FALSE = "false";
    /**
     * 解析配置文件
     * @param configuration
     */
    public void initParam(Configuration configuration){
        List<Context> contexts = configuration.getContexts();
        for(Context context : contexts){
            parseContext(context);
        }

        //TODO 创建文件流

    }


    private void parseContext(Context context) {
        Properties properties = context.getProperties();
        String generationControllerParam = properties.getProperty("generationController");
        if(FALSE.equals(generationControllerParam)){
            generationController = false;
        }
        String generationServiceParam = properties.getProperty("generationService");
        if(FALSE.equals(generationServiceParam)){
            generationService = false;
        }
        String addSwaggerAPIAnnotationParam = properties.getProperty("addSwaggerAPIAnnotation");
        if(FALSE.equals(addSwaggerAPIAnnotationParam)){
            addSwaggerAPIAnnotation = false;
        }
        controllerPackagePath = properties.getProperty("controllerPackagePath");
        servicePackagePath = properties.getProperty("servicePackagePath");

        //获取配置的实体名称
        entityNames = new ArrayList<String>();
        List<TableConfiguration> tableConfigurations = context.getTableConfigurations();
        for(TableConfiguration t: tableConfigurations){
            entityNames.add(t.getDomainObjectName());
        }

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = context.getJavaModelGeneratorConfiguration();
        targetProject = javaModelGeneratorConfiguration.getTargetProject();
        targetEntityPackage = javaModelGeneratorConfiguration.getTargetPackage();

        checkParam();
    }

    /**
     * 校验参数
     */
    private void checkParam() {
        if(generationService&& StringUtils.isEmpty(servicePackagePath)){
            throw new RuntimeException("if \"generationService\" is true mast have \"servicePackagePath\"");
        }
        if(generationController&& StringUtils.isEmpty(controllerPackagePath)){
            throw new RuntimeException("if \"generationController\" is true mast have \"controllerPackagePath\"");
        }
    }

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

    public String getControllerPackagePath() {
        return controllerPackagePath;
    }

    public void setControllerPackagePath(String controllerPackagePath) {
        this.controllerPackagePath = controllerPackagePath;
    }

    public String getServicePackagePath() {
        return servicePackagePath;
    }

    public void setServicePackagePath(String servicePackagePath) {
        this.servicePackagePath = servicePackagePath;
    }

    public List<String> getEntityNames() {
        return entityNames;
    }

    public void setEntityNames(List<String> entityNames) {
        this.entityNames = entityNames;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getTargetEntityPackage() {
        return targetEntityPackage;
    }

    public void setTargetEntityPackage(String targetEntityPackage) {
        this.targetEntityPackage = targetEntityPackage;
    }

    @Override
    public String toString() {
        return "CommonGeneratingParam{" +
                "generationController=" + generationController +
                ", generationService=" + generationService +
                ", addSwaggerAPIAnnotation=" + addSwaggerAPIAnnotation +
                ", controllerPackagePath='" + controllerPackagePath + '\'' +
                ", servicePackagePath='" + servicePackagePath + '\'' +
                ", entityNames=" + entityNames +
                ", targetProject='" + targetProject + '\'' +
                ", targetEntityPackage='" + targetEntityPackage + '\'' +
                '}';
    }
}
