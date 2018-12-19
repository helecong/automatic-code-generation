package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.constant.ApplicationConstant;
import com.dev999.maven.genertation.property.ClassProperty;

/**
 *  基于 dev999 的Service 接口 java file
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultServiceInterfaceJavaFile extends ClassProperty{

    /**
     * dao包路径
     */
    private String daoPackage;
    /**
     * DaoBean 的名称
     */
    private String daoBeanName;

    /**
     * daoBean的别名，用于注释生成，如果没有默认为 daobean的名称
     */
    private String daoBeanAlias;

    /**
     * 实体类包结构
     */
    private String entityPackage;
    /**
     * 实体类的名称
     */
    private String entityName;
    /**
     * 实体类的别名，用于注释生成，如果没有默认为 entityName的名称
     */
    private String entityAlias;

    /**
     * 初始化文件
     * @return
     */
    public DefaultServiceInterfaceJavaFile initSource(){

        initPatam();

        this.setImportClasss(entityPackage+"."+entityName);
        this.setImportClasss("java.util.List");
        this.setImportClasss("java.util.Map");

        this.setDoc(entityAlias+" service接口");
        this.setClassTyoe(ApplicationConstant.CLASS_TYPE_INTERFACE);

        this.setMethods(new DefaultServiceMethods(daoBeanName,entityName,true).getDefaultMethods());

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
     * 设置 daobean的名称
     * @param daoBeanName
     * @return
     */
    public DefaultServiceInterfaceJavaFile setDaoBeanName(String daoBeanName){
        this.daoBeanName = daoBeanName;
        return this;
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

    public DefaultServiceInterfaceJavaFile setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
        return this;
    }

    public DefaultServiceInterfaceJavaFile setDaoBeanAlias(String daoBeanAlias) {
        this.daoBeanAlias = daoBeanAlias;
        return this;
    }

    public DefaultServiceInterfaceJavaFile setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
        return this;
    }

    public DefaultServiceInterfaceJavaFile setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
        return this;
    }
}
