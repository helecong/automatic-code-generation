package com.dev999.maven.genertation.property;

import com.dev999.maven.genertation.constant.ApplicationConstant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类属性
 * @author helecong
 * @date 2018/12/14
 */
public class ClassProperty extends BaseProperty{
    /**
     * 包路径
     */
    private String packagePath = "";
    /**
     * 静态导入类
     */
    private Set<String> staticImportClasss;

    /**
     * 类导入数组
     */
    private Set<String> importClasss ;

    /**
     * 访问修饰符
     */
    private String accessModifier = ApplicationConstant.METHOD_SCOPE_PUBLIC;

    /**
     * 是否是抽象类
     */
    private boolean abstractClass = false;

    /**
     * 类的类型
     */
    private String classTyoe = ApplicationConstant.CLASS_TYPE_CLASS;

    /**
     * 类名
     */
    private String className = "";

    /**
     * 继承类的类名称
     */
    private String extensClass ="";

    /**
     * 接口类名称
     */
    private Set<String> interfaceClasss;

    /**
     * 全局变量
     */
    private Set<VariableProperty> globalVariables;


    /**
     * 方法集合
     */
    private List<MethodProperty> methods;

    /**
     * 生成getter
     */
    private boolean generatedGetter = true;
    /**
     * 生成setter
     */
    private boolean generatedSetter = true;

    public ClassProperty(){
        super();
        staticImportClasss = new HashSet<String>();
        importClasss = new HashSet<String>();
        interfaceClasss = new HashSet<String>();
        globalVariables = new HashSet<VariableProperty>();
        methods = new ArrayList<MethodProperty>();
    }

    @Override
    public String getSource() {
        sb = new StringBuilder();
        if(!"".equals(packagePath)){
            sb.append("package ").append(packagePath).append(";");
        }

        newLine();

        for(String staticImportClass : staticImportClasss){
            newLine().append("import static ").append(staticImportClass).append("\\.*;");
        }
        newLine();
        for(String importClass : importClasss){
            newLine().append("import ").append(importClass).append(";");
        }
        newLine();
        newLine();
        addClassDoc(className);
        addClassAnnotations();
        newLine().append(accessModifier);
        blank();
        if(abstractClass){
            sb.append("abstract class");
        }else{
            sb.append(classTyoe);
        }
        blank().append(className);

        if(!"".equals(extensClass)){
            blank().append("extends ").append(extensClass);
        }

        if(interfaceClasss != null && interfaceClasss.size()>0){
            blank().append("implements ");
            for(String interfaceClass : interfaceClasss){
                sb.append(interfaceClass).append(", ");
            }
            sb.deleteCharAt(sb.length()-2);
        }
        sb.append("{");

        newLine();
        if(globalVariables!=null && globalVariables.size()>0){
            for (VariableProperty globalVariable:globalVariables){
                newLine().append(globalVariable.getSource());
                newLine();
            }
        }

        //加载方法
        newLine();
        if(methods != null && methods.size()>0){
            for(MethodProperty method : methods){
                newLine();
                newLine().append(method.getSource(ApplicationConstant.CLASS_TYPE_INTERFACE.equals(classTyoe) ));
            }
        }

        //加载参数的getset方法
        if(globalVariables!=null && globalVariables.size()>0){
            if(generatedGetter&&generatedSetter){
                for (VariableProperty globalVariable:globalVariables){
                    newLine().append(globalVariable.getGetter());
                    newLine();
                    newLine().append(globalVariable.getSetter());
                    newLine();
                }
            }else if(generatedGetter&&!generatedSetter){
                for (VariableProperty globalVariable:globalVariables){
                    newLine().append(globalVariable.getGetter());
                    newLine();
                }
            }else if(!generatedGetter&&generatedSetter){
                for (VariableProperty globalVariable:globalVariables){
                    newLine().append(globalVariable.getSetter());
                    newLine();
                }
            }
        }

        newLine();
        newLine().append("}");
        String s = sb.toString();
        sb = null;
        return s;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public Set<String> getStaticImportClasss() {
        return staticImportClasss;
    }

    public void setStaticImportClasss(Set<String> staticImportClasss) {
        this.staticImportClasss = staticImportClasss;
    }

    public Set<String> getImportClasss() {
        return importClasss;
    }

    public void setImportClasss(Set<String> importClasss) {
        this.importClasss = importClasss;
    }
    public void setImportClasss(String importClass) {
        if(this.importClasss ==null){
            this.importClasss = new HashSet<String>();
        }
        this.importClasss.add(importClass);
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public boolean isAbstractClass() {
        return abstractClass;
    }

    public void setAbstractClass(boolean abstractClass) {
        this.abstractClass = abstractClass;
    }

    public String getClassTyoe() {
        return classTyoe;
    }

    public void setClassTyoe(String classTyoe) {
        this.classTyoe = classTyoe;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getExtensClass() {
        return extensClass;
    }

    public void setExtensClass(String extensClass) {
        this.extensClass = extensClass;
    }

    public Set<String> getInterfaceClasss() {
        return interfaceClasss;
    }

    public void setInterfaceClasss(Set<String> interfaceClasss) {
        this.interfaceClasss = interfaceClasss;
    }
    public void setInterfaceClasss(String interfaceClass) {
        if(this.interfaceClasss == null){
            this.interfaceClasss = new HashSet<String>();
        }
        this.interfaceClasss.add(interfaceClass);
    }

    public Set<VariableProperty> getGlobalVariables() {
        return globalVariables;
    }

    public void setGlobalVariables(Set<VariableProperty> globalVariables) {
        this.globalVariables = globalVariables;
    }

    public void setGlobalVariables(VariableProperty globalVariable) {
        if(this.globalVariables == null){
            this.globalVariables = new HashSet<VariableProperty>();
        }
        this.globalVariables.add(globalVariable);
    }

    public List<MethodProperty> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodProperty> methods) {
        this.methods = methods;
    }

    public boolean isGeneratedGetter() {
        return generatedGetter;
    }

    public void setGeneratedGetter(boolean generatedGetter) {
        this.generatedGetter = generatedGetter;
    }

    public boolean isGeneratedSetter() {
        return generatedSetter;
    }

    public void setGeneratedSetter(boolean generatedSetter) {
        this.generatedSetter = generatedSetter;
    }


}
