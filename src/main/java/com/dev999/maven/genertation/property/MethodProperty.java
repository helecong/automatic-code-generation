package com.dev999.maven.genertation.property;

import com.dev999.maven.genertation.constant.ApplicationConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法属性
 * @author helecong
 * @date 2018/12/14
 */
public class MethodProperty extends BaseProperty {

    /**
     * 访问修饰符
     */
    private String accessModifier = ApplicationConstant.METHOD_SCOPE_PUBLIC;

    /**
     * 返回结果类型
     */
    private String resultClass = ApplicationConstant.METHOD_RESULT_DEFAULT;

    private String mothodName = "test";

    /**
     * 参数变量
     */
    private List<VariableProperty> params;

    private String body = "";

    /**
     * 是否是抽象方法
     */
    private boolean abstractMethod = false;

    public MethodProperty(){
        super();
        params = new ArrayList<VariableProperty>();
    }

    @Override
    public String getSource() {
        return this.getSource(false);
    }

    /**
     * 扩展getSource方法
     * @param isInterface
     * @return
     */
    public String getSource(boolean isInterface){
        sb = new StringBuilder();

        //添加注释
        addDoc(mothodName);
        //添加注解
        addAnnotations();
        newLine();
        if(!isInterface){
            table().append(accessModifier);
        }
        blank().append(resultClass);
        blank().append(mothodName).append("(");
        for(VariableProperty param : params){
            sb.append(param.getParamSource()).append(", ");
        }
        if(params.size()>0){
            sb.substring(0,sb.length()-2);
        }
        sb.append("){");

        if(!abstractMethod&&!isInterface){
            newLine().append(body);
            newLine();
        }

        table().append("}");

        String s = sb.toString();
        sb = null;
        return s;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public String getResultClass() {
        return resultClass;
    }

    public void setResultClass(String resultClass) {
        this.resultClass = resultClass;
    }

    public String getMothodName() {
        return mothodName;
    }

    public void setMothodName(String mothodName) {
        this.mothodName = mothodName;
    }

    public List<VariableProperty> getParams() {
        return params;
    }

    public void setParams(List<VariableProperty> params) {
        this.params = params;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean abstractMethod) {
        this.abstractMethod = abstractMethod;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
