package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.GeneratorCentext;
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
     * 实体类的名称
     */
    private String entityName;
    /**
     * 实体类的别名，用于注释生成，如果没有默认为 entityName的名称
     */
    private String entityAlias;
    private GeneratorCentext generatorCentext;
    private VariableProperty daoVariable;

    public DefaultServiceJavaFile(GeneratorCentext generatorCentext){
        this.generatorCentext = generatorCentext;
    }

    /**
     * 初始化文件
     * @return
     */
    public DefaultServiceJavaFile initSource(){

        initPatam();
        initDaoVariable();
        initClass();
        String entityFullPath = generatorCentext.getEntityJavaFiles().get(entityName).getCompilationUnit().getType().getFullyQualifiedName();

        this.setImportClasss(entityFullPath);
        this.setImportClasss(daoVariable.getFullyQualifiedName());
        this.setImportClasss("org.springframework.stereotype.Service");
        this.setImportClasss("org.springframework.beans.factory.annotation.Autowired");
        this.setImportClasss("java.util.List");
        this.setImportClasss("java.util.Map");
        this.setImportClasss("tk.mybatis.mapper.entity.Example");
        this.setImportClasss("tk.mybatis.mapper.entity.Example.Criteria");
        this.setImportClasss(NameUtils.serviceInterfaceFullPath(generatorCentext.getServicePackagePath(),entityName));

        this.setDoc(entityAlias+" service实现类");
        this.setAnnotations("@Service");
        this.setInterfaceClasss(NameUtils.serviceInterfaceName(entityName));

        this.setGlobalVariables(daoVariable);

        VariableProperty entityBean = new VariableProperty(entityName, StringUtils.firstNameLower(entityName));
        entityBean.setFullyQualifiedName(entityFullPath);
        entityBean.setDoc(entityAlias);

        new DefaultServiceMethods(generatorCentext,this,entityBean).getDefaultMethods();

        this.setGeneratedGetter(false);
        this.setGeneratedSetter(false);

        return this;
    }

    private void initClass() {
        this.setClassName(NameUtils.serviceName(entityName));
        this.setPackagePath(NameUtils.servicePackagePath(generatorCentext.getServicePackagePath(),entityName));
    }

    private void initPatam() {
        if(entityAlias==null||"".equals(entityAlias)){
            entityAlias = entityName;
        }

    }



    public VariableProperty initDaoVariable(){
        this.daoVariable = new VariableProperty(NameUtils.daoName(entityName),NameUtils.daoVariableName(entityName));
        this.daoVariable.setFullyQualifiedName(NameUtils.daoInterfaceFullPath(generatorCentext.getTargetDaoPackage(),entityName));
        this.daoVariable.setAnnotations("@Autowired");
        return daoVariable;
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

    public DefaultServiceJavaFile setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
        return this;
    }
}
