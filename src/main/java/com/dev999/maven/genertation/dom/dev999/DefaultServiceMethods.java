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

    public DefaultServiceMethods(String daoBeanName,String entityName){
        this.daoBeanName = daoBeanName;
        this.entityName = entityName;
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

        methods.add(new UpdateMethodServiceModel(daoBeanName,entityName));

        return methods;
    }

    private Collection<? extends MethodProperty> queryMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new QueryListByMapMethodServiceModel(daoBeanName,entityName));
        methods.add(new QueryByIdMethodServiceModel(daoBeanName,entityName));

        return methods;
    }

    private Collection<? extends MethodProperty> delectMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new DeleteByIdMethodServiceModel(daoBeanName,entityName));

        return methods;
    }

    private Collection<? extends MethodProperty> insertMethod() {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        methods.add(new InsertMethodServiceModel(daoBeanName,entityName));
        methods.add(new InsertListMethodServiceModel(daoBeanName,entityName));

        return methods;
    }

}
