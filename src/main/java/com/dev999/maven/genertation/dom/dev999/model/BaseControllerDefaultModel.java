package com.dev999.maven.genertation.dom.dev999.model;

import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.MethodProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.CommonGeneratingParam;

/**
 * @author helecong
 * @date 2018/12/17
 */
public abstract class BaseControllerDefaultModel extends MethodProperty {
    protected VariableProperty serviceVariable;
    protected VariableProperty entityVariable;
    protected CommonGeneratingParam commonGeneratingParam;
    protected boolean interfaceClass = false;
    protected ClassProperty topClassProperty;

    public BaseControllerDefaultModel(ClassProperty topClassProperty,VariableProperty serviceVariable, VariableProperty entityVariable,CommonGeneratingParam commonGeneratingParam,boolean interfaceClass){
        this.serviceVariable = serviceVariable;
        this.entityVariable = entityVariable;
        this.commonGeneratingParam = commonGeneratingParam;
        this.interfaceClass = interfaceClass;
        this.topClassProperty = topClassProperty;
    }
}
