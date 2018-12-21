package com.dev999.maven.genertation.dom.dev999.method;

import com.dev999.maven.genertation.constant.ApplicationConstant;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.MethodProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.GeneratorCentext;
import com.dev999.maven.genertation.service.GeneratorCentextParam;
import com.dev999.maven.genertation.utils.NameUtils;

/**
 * @author helecong
 * @date 2018/12/17
 */
public abstract class BaseMethod extends MethodProperty {
    /**
     * service变量
     */
    protected VariableProperty serviceVariable;
    /**
     * entity变量
     */
    protected VariableProperty entityVariable;
    /**
     * dao变量
     */
    protected VariableProperty daoVariable;
    /**
     * 是否是接口
     */
    protected boolean interfaceClass = false;
    /**
     * 顶层类是什么
     */
    protected ClassProperty topClassProperty;

    /**
     * 上下文
     */
    protected GeneratorCentext generatorCentext;

    private String entityName;


    public BaseMethod(GeneratorCentext generatorCentext, ClassProperty topClassProperty, VariableProperty entityVariable){
        this.generatorCentext = generatorCentext;
        this.entityVariable = entityVariable;
        this.topClassProperty = topClassProperty;

        init();
    }
    private void init(){
        this.interfaceClass = ApplicationConstant.CLASS_TYPE_INTERFACE.equals(topClassProperty.getClassTyoe());
        entityName = entityVariable.getVariableType();

        initServiceVariable();
        initDaoVariable();


        if(!interfaceClass){
            this.setAnnotations("@Override");
        }

        initMethod();

        this.topClassProperty.setMethods(this);
    }

    protected abstract void initMethod();

    public void initDaoVariable(){
        this.daoVariable = new VariableProperty(NameUtils.daoName(entityName),NameUtils.daoVariableName(entityName));
        this.daoVariable.setFullyQualifiedName(NameUtils.daoInterfaceFullPath(generatorCentext.getTargetDaoPackage(),entityName));
    }


    public void initServiceVariable() {
        this.serviceVariable = new VariableProperty(NameUtils.serviceInterfaceName(entityName),NameUtils.serviceVariableName(entityName));
        this.serviceVariable.setFullyQualifiedName(NameUtils.serviceInterfaceFullPath(generatorCentext.getServicePackagePath(),entityName));
    }
}
