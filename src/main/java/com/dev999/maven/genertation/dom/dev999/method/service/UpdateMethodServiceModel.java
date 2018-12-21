package com.dev999.maven.genertation.dom.dev999.method.service;

import com.dev999.maven.genertation.dom.dev999.method.BaseDefaultModel;
import com.dev999.maven.genertation.dom.dev999.method.BaseMethod;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.GeneratorCentext;
import com.dev999.maven.genertation.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author helecong
 * @date 2018/12/17
 */
public class UpdateMethodServiceModel extends BaseMethod {


    public UpdateMethodServiceModel(GeneratorCentext generatorCentext, ClassProperty topClassProperty, VariableProperty entityVariable) {
        super(generatorCentext, topClassProperty, entityVariable);
    }

    @Override
    protected void initMethod() {
        this.setMothodName("update");
        this.setResultClass("int");
        this.setDoc("更新数据");

        List<VariableProperty> params = new ArrayList<VariableProperty>();
        params.add(entityVariable);

        this.setParams(params);

        String body = body();
        this.setBody(body);
    }

    public String body(){
        sb = new StringBuilder();

        newLine();
        table(2).append("return "+daoVariable.getVariableName()+".updateByPrimaryKeySelective("+entityVariable.getVariableName()+");");

        String s = sb.toString();
        sb = null;
        return s;
    }


}
