package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.constant.ApplicationConstant;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.GeneratorCentext;
import com.dev999.maven.genertation.utils.NameUtils;
import com.dev999.maven.genertation.utils.StringUtils;

/**
 * 默认的controller层接口java文件
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultControllerInterfaceJavaFile extends ClassProperty {
    /**
     * 实体类的名称
     */
    private String entityName;
    /**
     * 实体类的别名，用于注释生成，如果没有默认为 entityName的名称
     */
    private String entityAlias;
    private GeneratorCentext generatorCentext;

    public DefaultControllerInterfaceJavaFile(GeneratorCentext generatorCentext){
        this.generatorCentext = generatorCentext;
    }

    /**
     * 初始化文件
     * @return
     */
    public DefaultControllerInterfaceJavaFile initSource(){

        initPatam();
        initClass();

        String entityFullPath = generatorCentext.getEntityJavaFiles().get(entityName).getCompilationUnit().getType().getFullyQualifiedName();

        this.setImportClasss("org.springframework.web.bind.annotation.RequestMapping");
        this.setImportClasss("org.springframework.web.bind.annotation.ResponseBody");
        this.setImportClasss("org.springframework.web.bind.annotation.RequestMethod");

        this.setDoc(entityAlias+" Controller接口");
        this.setClassTyoe(ApplicationConstant.CLASS_TYPE_INTERFACE);

        this.setAnnotations("@RequestMapping(value = \""+StringUtils.firstNameLower(entityName)+"\", method = RequestMethod.POST)");
        this.setAnnotations("@ResponseBody");

        // 添加swagger注解
        if (generatorCentext.isAddSwaggerAPIAnnotation()){
            this.setAnnotations("@Api(value = \""+entityAlias+" 接口\", tags = {\""+entityAlias+" 接口\"})");
            this.setImportClasss("io.swagger.annotations.Api");
        }

        String serviceName = NameUtils.serviceInterfaceName(entityName);
        String serviceVariableName = NameUtils.serviceVariableName(entityName);

        VariableProperty serviceBean = new VariableProperty(serviceName, serviceVariableName);
        serviceBean.setAnnotations("@Autowired");
        serviceBean.setFullyQualifiedName(NameUtils.serviceInterfaceFullPath(generatorCentext.getServicePackagePath(),entityName));

        VariableProperty entityBean = new VariableProperty(entityName, StringUtils.firstNameLower(entityName));
        entityBean.setFullyQualifiedName(entityFullPath);
        entityBean.setDoc(entityAlias);

        new DefaultControllerMethods(generatorCentext,this,entityBean).initDefaultMethods();

        this.setGeneratedGetter(false);
        this.setGeneratedSetter(false);

        return this;
    }

    private void initClass() {
        this.setClassName(NameUtils.controllerInterfaceName(entityName));
        this.setPackagePath(NameUtils.controllerInterfacePackagePath(generatorCentext.getControllerPackagePath(),entityName));
    }

    private void initPatam() {
        if(entityAlias==null||"".equals(entityAlias)){
            entityAlias = entityName;
        }

    }




    /**
     * 设置 实体类的全路径地址
     * @param entityName
     * @return
     */
    public DefaultControllerInterfaceJavaFile setEntityName(String entityName){
        this.entityName = entityName;
        return this;
    }

    public DefaultControllerInterfaceJavaFile setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
        return this;
    }
}
