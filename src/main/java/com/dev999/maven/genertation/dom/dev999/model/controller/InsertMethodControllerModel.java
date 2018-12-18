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
public class InsertMethodControllerModel extends BaseControllerDefaultModel {
    public InsertMethodControllerModel(ClassProperty topClassProperty, VariableProperty serviceVariable, VariableProperty entityVariable, CommonGeneratingParam commonGeneratingParam, boolean interfaceClass) {
        super(topClassProperty,serviceVariable,entityVariable,commonGeneratingParam,interfaceClass);
        initMethod();
    }

    private static final String methodName = "insert";
    private static final String methodDoc = "添加数据";
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
            topClassProperty.setImportClasss("com.dev999.framework.util.BeanDisponseUtils");
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
        table(2).append(entityVariable.getVariableType()+" "+ entityVariable.getVariableName() +" = new "+entityVariable.getVariableType()+"();");
        newLine();
        table(2).append("BeanDisponseUtils.copyProperties(request,"+entityVariable.getVariableName()+");");
        newLine();
        table(2).append(serviceVariable.getVariableName()+"."+methodName+"("+entityVariable.getVariableName()+");");
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

        // 通过实体类获取
        parseField(classProperty);

        if(commonGeneratingParam.isAddSwaggerAPIAnnotation()){
            classProperty.setImportClasss("io.swagger.annotations.ApiModel");
            classProperty.setImportClasss("io.swagger.annotations.ApiModelProperty");
            classProperty.setAnnotations("@ApiModel(value = \""+methodName+"请求参数\")");
        }
        commonGeneratingParam.getSourceList().add(classProperty);

        topClassProperty.setImportClasss(packagePath+"."+resultClass);
        return resultClass;
    }

    private void parseField(ClassProperty classProperty) {

        GeneratedJavaFile generatedJavaFile = commonGeneratingParam.getEntityJavaFiles().get(entityVariable.getVariableType());

        CompilationUnit compilationUnit = generatedJavaFile.getCompilationUnit();
        TopLevelClass topLevelClass = null;
        if(compilationUnit instanceof TopLevelClass){
            topLevelClass = (TopLevelClass)compilationUnit;
        }
        List<Field> fields = topLevelClass.getFields();

        String doc = "";
        boolean addSwaggerAPIAnnotation = commonGeneratingParam.isAddSwaggerAPIAnnotation();
        for(Field field : fields){
            VariableProperty variableProperty = new VariableProperty(field.getType().getShortName(),field.getName());
            doc = FieldUtils.getFielDoc(field);
            variableProperty.setDoc(doc);

            if(addSwaggerAPIAnnotation){
                variableProperty.setAnnotations("@ApiModelProperty(value = \""+doc+"\")");
            }
            classProperty.setImportClasss(field.getType().getFullyQualifiedName());

            classProperty.setGlobalVariables(variableProperty);
        }

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
