package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.CommonGeneratingParam;
import com.dev999.maven.genertation.utils.NameUtils;
import com.dev999.maven.genertation.utils.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 *  基于 dev999 的Service java file
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultServiceJavaFile extends ClassProperty{

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
    private CommonGeneratingParam commonGeneratingParam;

    public DefaultServiceJavaFile(CommonGeneratingParam commonGeneratingParam){
        this.commonGeneratingParam = commonGeneratingParam;
    }

    /**
     * 初始化文件
     * @return
     */
    public DefaultServiceJavaFile initSource(){

        initPatam();

        this.setImportClasss(entityPackage+"."+entityName);
        this.setImportClasss(daoPackage+"."+daoBeanName);
        this.setImportClasss("org.springframework.stereotype.Service");
        this.setImportClasss("org.springframework.beans.factory.annotation.Autowired");
        this.setImportClasss("java.util.List");
        this.setImportClasss("java.util.Map");
        this.setImportClasss("tk.mybatis.mapper.entity.Example");
        this.setImportClasss("tk.mybatis.mapper.entity.Example.Criteria");
        this.setImportClasss(NameUtils.serviceInterfaceFullPath(commonGeneratingParam.getServicePackagePath(),entityName));

        this.setDoc(entityAlias+" service实现类");
        this.setAnnotations("@Service");
        this.setInterfaceClasss(NameUtils.serviceInterfaceName(entityName));

        Set<VariableProperty> globalVariables = new HashSet<VariableProperty>();

        VariableProperty daoBean = new VariableProperty(StringUtils.firstNameUpper(daoBeanName), StringUtils.firstNameLower(daoBeanName));
        daoBean.setAnnotations("@Autowired");
        globalVariables.add(daoBean);

        this.setGlobalVariables(globalVariables);

        this.setMethods(new DefaultServiceMethods(daoBeanName,entityName,false).getDefaultMethods());

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
    public DefaultServiceJavaFile setDaoBeanName(String daoBeanName){
        this.daoBeanName = daoBeanName;
        return this;
    }

    /**
     * 设置 实体类的全路径地址
     * @param entityName
     * @return
     */
    public DefaultServiceJavaFile setEntityName(String entityName){
        this.entityName = entityName;
        return this;
    }

    public DefaultServiceJavaFile setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
        return this;
    }

    public DefaultServiceJavaFile setDaoBeanAlias(String daoBeanAlias) {
        this.daoBeanAlias = daoBeanAlias;
        return this;
    }

    public DefaultServiceJavaFile setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
        return this;
    }

    public DefaultServiceJavaFile setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
        return this;
    }
}
