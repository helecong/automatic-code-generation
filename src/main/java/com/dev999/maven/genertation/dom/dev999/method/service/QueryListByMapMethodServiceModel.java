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
 * 默认的查询列表方法 service 层Mode
 * @author helecong
 * @date 2018/12/17
 */
public class QueryListByMapMethodServiceModel extends BaseMethod {


    public QueryListByMapMethodServiceModel(GeneratorCentext generatorCentext, ClassProperty topClassProperty, VariableProperty entityVariable) {
        super(generatorCentext, topClassProperty, entityVariable);
    }

    @Override
    protected void initMethod() {
        this.setMothodName("queryListByMap");
        this.setResultClass("List<"+ entityVariable.getVariableType()+">");
        this.setDoc("通过Map条件查询数据");

        List<VariableProperty> params = new ArrayList<VariableProperty>();
        VariableProperty variableProperty = new VariableProperty("Map<String,Object>", "queryMap");
        params.add(variableProperty);

        this.setParams(params);

        String body = body();
        this.setBody(body);
    }

    public String body(){
        sb = new StringBuilder();

        newLine();
        table(2).append("Example example = new Example("+entityVariable.getVariableType()+".class);");
        newLine();
        table(2).append("Criteria criteria = example.createCriteria();");
        newLine();
        table(2).append("criteria.andAllEqualTo(queryMap);");
        newLine();
        table(2).append("return "+daoVariable.getVariableName()+".selectByExample(example);");

        String s = sb.toString();
        sb = null;
        return s;
    }

}
