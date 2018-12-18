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

    public BaseDefaultModel(String daoBeanName, String entityName){
        this.daoBeanName = daoBeanName;
        this.entityName = entityName;
    }
}
