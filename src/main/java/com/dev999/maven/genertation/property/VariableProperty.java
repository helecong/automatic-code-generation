package com.dev999.maven.genertation.property;

import com.dev999.maven.genertation.constant.ApplicationConstant;
import com.dev999.maven.genertation.utils.StringUtils;

/**
 * 变量属性
 * @author helecong
 * @date 2018/12/14
 */
public class VariableProperty extends BaseProperty{
    /**
     * 访问修饰符
     */
    private String accessModifier = ApplicationConstant.METHOD_SCOPE_PRIVATE;

    /**
     * 静态变量
     */
    private boolean staticVariable = false;

    /**
     * final变量
     */
    private boolean finalVariable = false;

    /**
     * 变量类型
     */
    private String variableType;

    /**
     * 变量名称
     */
    private String variableName;

    /**
     * 默认值
     */
    private String defalutVal;

    /**
     * 变量的全类名
     */
    private String fullyQualifiedName;

    public VariableProperty(){
        super();
    }

    public VariableProperty(String variableType, String variableName){
        super();
        this.variableType = variableType;
        this.variableName = variableName;
    }

    @Override
    public String getSource() {
        sb = new StringBuilder();

        addDoc(variableName);
        addAnnotations();

        newLine();
        table().append(accessModifier);
        if(staticVariable){
            blank().append("static");
        }
        if(finalVariable){
            blank().append("final");
        }
        blank().append(variableType);
        blank().append(variableName);

        if("".equals(defalutVal)){
            blank().append(defalutVal);
        }
        sb.append(";");
        String s = sb.toString();
        sb = null;
        return s;
    }

    public String getGetter(){
        sb = new StringBuilder();

        addDoc("getter 方法");
        newLine();
        table().append("public");
        blank().append(variableType);
        blank().append("get").append(StringUtils.firstNameUpper(variableName)).append("(){");
        newLine();
        table();
        table().append("return ").append(variableName).append(";");
        newLine();
        table().append("}");

        String s = sb.toString();
        sb = null;
        return s;
    }

    public String getSetter(){
        sb = new StringBuilder();

        addDoc("setter 方法");

        newLine();
        table().append("public");
        blank().append("void");
        blank().append("set").append(StringUtils.firstNameUpper(variableName)).append("(").append(variableType);
        blank().append(variableName).append("){");
        newLine();
        table();
        table().append("this. ").append(variableName).append(" = ").append(variableName).append(";");
        newLine();
        table().append("}");

        String s = sb.toString();
        sb = null;
        return s;
    }

    /**
     * 当变量为参数时输出
     * @return
     */
    public String getParamSource(){
        sb = new StringBuilder();

        addParamAnnotations().append(variableType);
        blank().append(variableName);

        return sb.toString();
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public boolean isStaticVariable() {
        return staticVariable;
    }

    public void setStaticVariable(boolean staticVariable) {
        this.staticVariable = staticVariable;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public void setFinalVariable(boolean finalVariable) {
        this.finalVariable = finalVariable;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getDefalutVal() {
        return defalutVal;
    }

    public void setDefalutVal(String defalutVal) {
        this.defalutVal = defalutVal;
    }

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public void setFullyQualifiedName(String fullyQualifiedName) {
        this.fullyQualifiedName = fullyQualifiedName;
    }
}
