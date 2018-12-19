package com.dev999.maven.genertation.dom.dev999.model;

import com.dev999.maven.genertation.property.MethodProperty;

import com.dev999.maven.genertation.property.MethodProperty;

/**
 * @author helecong
 * @date 2018/12/17
 */
public abstract class BaseDefaultModel extends MethodProperty {
    protected String daoBeanName;
    protected String entityName;
    protected boolean interfaceClass = false;

    public BaseDefaultModel(String daoBeanName, String entityName,boolean interfaceClass){
        this.daoBeanName = daoBeanName;
        this.entityName = entityName;
        this.interfaceClass = interfaceClass;

        if(!interfaceClass){
            this.setAnnotations("@Override");
        }

    }
}
