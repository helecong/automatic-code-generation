package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.constant.ApplicationConstant;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.GeneratorCentext;
import com.dev999.maven.genertation.utils.NameUtils;
import com.dev999.maven.genertation.utils.StringUtils;

/**
 *  基于 dev999 的Service 接口 java file
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultServiceInterfaceJavaFile extends ClassProperty{

    /**
     * 实体类的名称
     */
    private String entityName;
    /**
     * 实体类的别名，用于注释生成，如果没有默认为 entityName的名称
     */
    private String entityAlias;

    private GeneratorCentext generatorCentext;

    public DefaultServiceInterfaceJavaFile(GeneratorCentext generatorCentext){
        this.generatorCentext = generatorCentext;
    }

    /**
     * 初始化文件
     * @return
     */
    public DefaultServiceInterfaceJavaFile initSource(){

        initPatam();
        initClass();
        String entityFullPath = generatorCentext.getEntityJavaFiles().get(entityName).getCompilationUnit().getType().getFullyQualifiedName();

        this.setImportClasss(entityFullPath);
        this.setImportClasss("java.util.List");
        this.setImportClasss("java.util.Map");
        this.setClassTyoe(ApplicationConstant.CLASS_TYPE_INTERFACE);

        VariableProperty entityBean = new VariableProperty(entityName, StringUtils.firstNameLower(entityName));
        entityBean.setFullyQualifiedName(entityFullPath);
        entityBean.setDoc(entityAlias);

        new DefaultServiceMethods(generatorCentext,this,entityBean).getDefaultMethods();

        this.setGeneratedGetter(false);
        this.setGeneratedSetter(false);

        return this;
    }

    private void initClass() {
        this.setClassName(NameUtils.serviceInterfaceName(entityName));
        this.setPackagePath(NameUtils.serviceInterfacePackagePath(generatorCentext.getServicePackagePath(),entityName));
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
    public DefaultServiceInterfaceJavaFile setEntityName(String entityName){
        this.entityName = entityName;
        return this;
    }

    public DefaultServiceInterfaceJavaFile setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
        return this;
    }
}
