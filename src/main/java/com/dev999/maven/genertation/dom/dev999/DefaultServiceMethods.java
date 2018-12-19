package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.dom.dev999.model.service.*;
import com.dev999.maven.genertation.property.MethodProperty;

import java.util.*;

/**
 * 默认的service层方法
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultServiceMethods {
    private String daoBeanName;
    private String entityName;

    private List<MethodProperty> methodProperties ;
    private boolean interfaceClass = false;

    public DefaultServiceMethods(String daoBeanName,String entityName,boolean interfaceClass){
        this.daoBeanName = daoBeanName;
        this.entityName = entityName;
        this.interfaceClass = interfaceClass;
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

        methods.add(new UpdateMethodServiceModel(daoBeanName,entityName,interfaceClass));

        return methods;
    }

    private Collection<? extends MethodProperty> queryMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new QueryListByMapMethodServiceModel(daoBeanName,entityName,interfaceClass));
        methods.add(new QueryByIdMethodServiceModel(daoBeanName,entityName,interfaceClass));

        return methods;
    }

    private Collection<? extends MethodProperty> delectMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new DeleteByIdMethodServiceModel(daoBeanName,entityName,interfaceClass));

        return methods;
    }

    private Collection<? extends MethodProperty> insertMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new InsertMethodServiceModel(daoBeanName,entityName,interfaceClass));
        methods.add(new InsertListMethodServiceModel(daoBeanName,entityName,interfaceClass));

        return methods;
    }

}
