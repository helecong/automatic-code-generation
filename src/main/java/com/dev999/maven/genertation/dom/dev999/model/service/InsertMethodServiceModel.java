package com.dev999.maven.genertation.dom.dev999.model.service;

import com.dev999.maven.genertation.dom.dev999.model.BaseDefaultModel;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author helecong
 * @date 2018/12/17
 */
public class InsertMethodServiceModel extends BaseDefaultModel {
    public InsertMethodServiceModel(String daoBeanName, String entityName,boolean interfaceClass) {
        super(daoBeanName,entityName,interfaceClass);
        initMethod();
    }

    private void initMethod() {
        this.setMothodName("insert");
        this.setResultClass("int");
        this.setDoc("添加数据");

        List<VariableProperty> params = new ArrayList<VariableProperty>();
        VariableProperty variableProperty = new VariableProperty(StringUtils.firstNameUpper(entityName), StringUtils.firstNameLower(entityName));
        params.add(variableProperty);

        this.setParams(params);

        String body = body();
        this.setBody(body);
    }

    public String body(){
        sb = new StringBuilder();

        newLine();
        table(2).append("return "+StringUtils.firstNameLower(daoBeanName)+".insert("+StringUtils.firstNameLower(entityName)+");");

        String s = sb.toString();
        sb = null;
        return s;
    }


}
