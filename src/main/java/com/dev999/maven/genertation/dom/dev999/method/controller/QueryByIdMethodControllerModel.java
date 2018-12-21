package com.dev999.maven.genertation.dom.dev999.method.controller;

import com.dev999.maven.genertation.dom.dev999.method.BaseMethod;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.GeneratorCentext;
import com.dev999.maven.genertation.utils.NameUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author helecong
 * @date 2018/12/17
 */
public class QueryByIdMethodControllerModel extends BaseMethod {

    private static final String methodName = "queryById";
    private static final String methodDoc = "通过Id查询数据";
    private String resultClassName = "";
    private String requestClassName = "";

    public QueryByIdMethodControllerModel(GeneratorCentext generatorCentext, ClassProperty topClassProperty, VariableProperty entityVariable) {
        super(generatorCentext, topClassProperty, entityVariable);
    }

    @Override
    protected void initMethod() {
        resultClassName = createResultClass();
        requestClassName = createRequestClass();
        this.setMothodName(methodName);
        this.setResultClass(resultClassName);
        this.setDoc(methodDoc);

        if(interfaceClass){
            this.setAnnotations("@RequestMapping(value = \""+methodName+"\", method = RequestMethod.POST)");
            if(generatorCentext.isAddSwaggerAPIAnnotation()){
                topClassProperty.setImportClasss("io.swagger.annotations.ApiOperation");
                this.setAnnotations("@ApiOperation(value = \""+methodDoc+entityVariable.getDoc()+"\")");
            }
        }else{
        }

        List<VariableProperty> params = new ArrayList<VariableProperty>();
        VariableProperty variableProperty = new VariableProperty(requestClassName, "request");
        if(!interfaceClass){
            variableProperty.setAnnotations("@RequestBody ");
            topClassProperty.setImportClasss("org.springframework.web.bind.annotation.RequestBody");
        }

        params.add(variableProperty);

        this.setParams(params);

        String body = body();
        this.setBody(body);
    }

    public String body(){
        sb = new StringBuilder();

        newLine();
        table(2).append(resultClassName).append(" response = new ").append(resultClassName).append("();");
        newLine();
        table(2).append(entityVariable.getVariableType() + " "+ entityVariable.getVariableName() +" = "+serviceVariable.getVariableName()+"."+methodName+"(request.getId());");
        newLine();
        table(2).append("response.setData(").append(entityVariable.getVariableName()).append(");");
        newLine();
        table(2).append("return response;");
        newLine();

        String s = sb.toString();
        sb = null;
        return s;
    }

    private String createRequestClass() {
        String resultClass = NameUtils.requestClassName(methodName);

        ClassProperty classProperty = new ClassProperty();
        classProperty.setClassName(resultClass);
        String packagePath = generatorCentext.getControllerPackagePath()+"."+entityVariable.getVariableName().toLowerCase()+".vo";

        classProperty.setPackagePath(packagePath);
        classProperty.setImportClasss("com.dev999.framework.common.AbstractServiceRequest");

        classProperty.setExtensClass("AbstractServiceRequest");

        VariableProperty variableProperty = new VariableProperty("Long","id");
        classProperty.setGlobalVariables(variableProperty);

        if(generatorCentext.isAddSwaggerAPIAnnotation()){
            classProperty.setImportClasss("io.swagger.annotations.ApiModel");
            classProperty.setImportClasss("io.swagger.annotations.ApiModelProperty");
            variableProperty.setAnnotations("@ApiModelProperty(value = \"id\")");
            classProperty.setAnnotations("@ApiModel(value = \""+methodName+"请求参数\")");
        }
        generatorCentext.getSourceList().add(classProperty);

        topClassProperty.setImportClasss(packagePath+"."+resultClass);
        return resultClass;
    }

    private String createResultClass() {
        String resultClass = NameUtils.responseClassName(methodName);

        ClassProperty classProperty = new ClassProperty();
        classProperty.setClassName(resultClass);
        String packagePath = generatorCentext.getControllerPackagePath()+"."+entityVariable.getVariableName().toLowerCase()+".vo";

        classProperty.setPackagePath(packagePath);
        classProperty.setImportClasss("com.dev999.framework.common.AbstractServiceResponse");
        classProperty.setImportClasss(entityVariable.getFullyQualifiedName());

        classProperty.setExtensClass("AbstractServiceResponse");

        VariableProperty variableProperty = new VariableProperty(entityVariable.getVariableType(),"data");
        classProperty.setGlobalVariables(variableProperty);

        if(generatorCentext.isAddSwaggerAPIAnnotation()){
            classProperty.setImportClasss("io.swagger.annotations.ApiModel");
            classProperty.setImportClasss("io.swagger.annotations.ApiModelProperty");
            variableProperty.setAnnotations("@ApiModelProperty(value = \""+entityVariable.getVariableName()+"返回数据\")");
            classProperty.setAnnotations("@ApiModel(value = \""+methodName+"返回结果\")");
        }
        generatorCentext.getSourceList().add(classProperty);

        topClassProperty.setImportClasss(packagePath+"."+resultClass);
        return resultClass;
    }




}
