package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.CommonGeneratingParam;
import com.dev999.maven.genertation.utils.NameUtils;
import com.dev999.maven.genertation.utils.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 默认的controller层java文件
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultControllerJavaFile extends ClassProperty {

    /**
     * 实体类的名称
     */
    private String entityName;
    /**
     * 实体类的别名，用于注释生成，如果没有默认为 entityName的名称
     */
    private String entityAlias;
    private CommonGeneratingParam commonGeneratingParam;

    public DefaultControllerJavaFile(CommonGeneratingParam commonGeneratingParam){
        this.commonGeneratingParam = commonGeneratingParam;
    }

    /**
     * 初始化文件
     * @return
     */
    public DefaultControllerJavaFile initSource(){

        initPatam();
        String entityFullPath = commonGeneratingParam.getEntityJavaFiles().get(entityName).getCompilationUnit().getType().getFullyQualifiedName();

        this.setClassName(NameUtils.controllerName(entityName));
        this.setPackagePath(NameUtils.controllerPackagePath(commonGeneratingParam.getControllerPackagePath(),entityName));

        this.setImportClasss(entityFullPath);
        this.setImportClasss("org.springframework.web.bind.annotation.RestController");
        this.setImportClasss("org.springframework.web.bind.annotation.RequestBody");
        this.setImportClasss("org.springframework.beans.factory.annotation.Autowired");
        this.setImportClasss(NameUtils.controllerInterfaceFullPath(commonGeneratingParam.getControllerPackagePath(),entityName));

        this.setDoc(entityAlias+" Controller实现类");
        this.setAnnotations("@RestController");
        this.setInterfaceClasss(NameUtils.controllerInterfaceName(entityName));


        String serviceName = NameUtils.serviceInterfaceName(entityName);
        String serviceVariableName = NameUtils.serviceVariableName(entityName);

        VariableProperty serviceBean = new VariableProperty(serviceName, serviceVariableName);
        serviceBean.setAnnotations("@Autowired");
        serviceBean.setFullyQualifiedName(NameUtils.serviceInterfaceFullPath(commonGeneratingParam.getServicePackagePath(),entityName));
        this.setGlobalVariables(serviceBean);
        this.setImportClasss(serviceBean.getFullyQualifiedName());

        VariableProperty entityBean = new VariableProperty(entityName, StringUtils.firstNameLower(entityName));
        entityBean.setFullyQualifiedName(entityFullPath);
        entityBean.setDoc(entityAlias);

        this.setMethods(new DefaultControllerMethods(this,serviceBean,entityBean,commonGeneratingParam,false).getDefaultMethods());

        this.setGeneratedGetter(false);
        this.setGeneratedSetter(false);

        return this;
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
    public DefaultControllerJavaFile setEntityName(String entityName){
        this.entityName = entityName;
        return this;
    }

    public DefaultControllerJavaFile setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
        return this;
    }
}
