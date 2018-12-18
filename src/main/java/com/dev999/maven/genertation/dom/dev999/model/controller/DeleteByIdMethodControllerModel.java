package com.dev999.maven.genertation.dom.dev999.model.controller;

import com.dev999.maven.genertation.dom.dev999.model.BaseControllerDefaultModel;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.CommonGeneratingParam;
import com.dev999.maven.genertation.utils.FieldUtils;
import com.dev999.maven.genertation.utils.NameUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author helecong
 * @date 2018/12/17
 */
public class DeleteByIdMethodControllerModel extends BaseControllerDefaultModel {
    public DeleteByIdMethodControllerModel(ClassProperty topClassProperty, VariableProperty serviceVariable, VariableProperty entityVariable, CommonGeneratingParam commonGeneratingParam, boolean interfaceClass) {
        super(topClassProperty,serviceVariable,entityVariable,commonGeneratingParam,interfaceClass);
        initMethod();
    }

    private static final String methodName = "deleteById";
    private static final String methodDoc = "通过id删除数据";
    private String resultClassName = "";
    private String requestClassName = "";

    private void initMethod() {
        resultClassName = createResultClass();
        requestClassName = createRequestClass();
        this.setMothodName(methodName);
        this.setResultClass(resultClassName);
        this.setDoc(methodDoc);

        if(interfaceClass){
            this.setAnnotations("@RequestMapping(value = \""+methodName+"\", method = RequestMethod.POST)");
            if(commonGeneratingParam.isAddSwaggerAPIAnnotation()){
                topClassProperty.setImportClasss("io.swagger.annotations.ApiOperation");
                this.setAnnotations("@ApiOperation(value = \""+methodDoc+entityVariable.getDoc()+"\")");
            }
        }else{
            this.setAnnotations("@Override");
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
        table(2).append(serviceVariable.getVariableName()+"."+methodName+"(request.getId());");
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
        String packagePath = commonGeneratingParam.getControllerPackagePath()+"."+entityVariable.getVariableName().toLowerCase()+".vo";

        classProperty.setPackagePath(packagePath);
        classProperty.setImportClasss("com.dev999.framework.common.AbstractServiceRequest");

        classProperty.setExtensClass("AbstractServiceRequest");

        VariableProperty variableProperty = new VariableProperty("Long","id");
        classProperty.setGlobalVariables(variableProperty);

        if(commonGeneratingParam.isAddSwaggerAPIAnnotation()){
            classProperty.setImportClasss("io.swagger.annotations.ApiModel");
            classProperty.setImportClasss("io.swagger.annotations.ApiModelProperty");
            variableProperty.setAnnotations("@ApiModelProperty(value = \"id\")");
            classProperty.setAnnotations("@ApiModel(value = \""+methodName+"请求参数\")");
        }
        commonGeneratingParam.getSourceList().add(classProperty);

        topClassProperty.setImportClasss(packagePath+"."+resultClass);
        return resultClass;
    }

    private String createResultClass() {
        String resultClass = NameUtils.responseClassName(methodName);

        ClassProperty classProperty = new ClassProperty();
        classProperty.setClassName(resultClass);
        String packagePath = commonGeneratingParam.getControllerPackagePath()+"."+entityVariable.getVariableName().toLowerCase()+".vo";

        classProperty.setPackagePath(packagePath);
        classProperty.setImportClasss("com.dev999.framework.common.AbstractServiceResponse");

        classProperty.setExtensClass("AbstractServiceResponse");

        if(commonGeneratingParam.isAddSwaggerAPIAnnotation()){
            classProperty.setImportClasss("io.swagger.annotations.ApiModel");
            classProperty.setAnnotations("@ApiModel(value = \""+methodName+"返回结果\")");
        }
        commonGeneratingParam.getSourceList().add(classProperty);

        topClassProperty.setImportClasss(packagePath+"."+resultClass);
        return resultClass;
    }




}
