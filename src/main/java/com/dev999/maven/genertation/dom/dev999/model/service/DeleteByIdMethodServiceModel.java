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
public class DeleteByIdMethodServiceModel extends BaseDefaultModel {
    public DeleteByIdMethodServiceModel(String daoBeanName, String entityName) {
        super(daoBeanName,entityName);
        initMethod();
    }

    private void initMethod() {
        this.setMothodName("deleteById");
        this.setResultClass("int");
        this.setDoc("通过id删除数据");

        List<VariableProperty> params = new ArrayList<VariableProperty>();
        VariableProperty variableProperty = new VariableProperty("Long", "id");
        params.add(variableProperty);

        this.setParams(params);

        String body = body();
        this.setBody(body);
    }

    public String body(){
        sb = new StringBuilder();

        newLine();
        table(2).append("return "+StringUtils.firstNameLower(daoBeanName)+".deleteByPrimaryKey(id);");

        String s = sb.toString();
        sb = null;
        return s;
    }


}
