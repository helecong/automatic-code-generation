package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.dom.dev999.model.controller.*;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.MethodProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.CommonGeneratingParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 默认的Controller层方法
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultControllerMethods {
    private VariableProperty serviceVariable;
    private VariableProperty entityVariable;
    private CommonGeneratingParam commonGeneratingParam;
    private boolean interfaceClass = false;

    private ClassProperty topClassProperty;

    private List<MethodProperty> methodProperties ;

    public DefaultControllerMethods(ClassProperty topClassProperty, VariableProperty serviceVariable, VariableProperty entityVariable, CommonGeneratingParam commonGeneratingParam, boolean interfaceClass){
        this.serviceVariable = serviceVariable;
        this.entityVariable = entityVariable;
        this.commonGeneratingParam = commonGeneratingParam;
        this.interfaceClass = interfaceClass;
        this.topClassProperty = topClassProperty;
    }

    public List<MethodProperty> getDefaultMethods(){
        methodProperties = new ArrayList<MethodProperty>();

        // 增加
        methodProperties.addAll(insertMethod());

        // 删除
        methodProperties.addAll(delectMethod());

        // 修改
        methodProperties.addAll(updateMethod());

        // 查询
        methodProperties.addAll(queryMethod());

        return methodProperties;
    }

    private Collection<? extends MethodProperty> updateMethod() {

        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new UpdateMethodControllerModel(topClassProperty,serviceVariable,entityVariable,commonGeneratingParam,interfaceClass));

        return methods;
    }

    private Collection<? extends MethodProperty> queryMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new QueryByIdMethodControllerModel(topClassProperty,serviceVariable,entityVariable,commonGeneratingParam,interfaceClass));
        methods.add(new QueryListByMapMethodControllerModel(topClassProperty,serviceVariable,entityVariable,commonGeneratingParam,interfaceClass));

        return methods;
    }

    private Collection<? extends MethodProperty> delectMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new DeleteByIdMethodControllerModel(topClassProperty,serviceVariable,entityVariable,commonGeneratingParam,interfaceClass));

        return methods;
    }

    private Collection<? extends MethodProperty> insertMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new InsertMethodControllerModel(topClassProperty,serviceVariable,entityVariable,commonGeneratingParam,interfaceClass));
        methods.add(new InstallListMethodControllerModel(topClassProperty,serviceVariable,entityVariable,commonGeneratingParam,interfaceClass));

        return methods;
    }

}
