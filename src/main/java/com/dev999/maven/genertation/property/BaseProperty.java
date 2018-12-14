package com.dev999.maven.genertation.property;

import java.util.HashSet;
import java.util.Set;

/**
 * 基本操作
 * @author helecong
 * @date 2018/12/14
 */
public abstract class BaseProperty {
    protected StringBuilder sb ;

    /**
     * 解释说明
     */
    protected String doc="";

    protected Set<String> annotations;

    public BaseProperty(){
        annotations = new HashSet<String>();
    }

    /**
     * 添加注释
     */
    protected StringBuilder addDoc(String mes){
        if(sb == null){
            sb = new StringBuilder();
        }

        table().append("/**");
        newLine();
        table().append("* ");
        newLine();
        table().append("* ").append(mes);
        if(!"".equals(doc)){
            newLine();
            table().append("* ").append(doc);
        }
        newLine();
        table().append("* ");
        newLine();
        table().append("*/");
        return sb;
    }

    /**
     * 添加注释
     */
    protected StringBuilder addClassDoc(String mes){
        if(sb == null){
            sb = new StringBuilder();
        }

        sb.append("/**");
        newLine();
        blank().append("* ");
        newLine();
        blank().append("* ").append(mes);
        if(!"".equals(doc)){
            newLine();
            blank().append("* ").append(doc);
        }
        newLine();
        blank().append("* ");
        newLine();
        blank().append("*/");
        return sb;
    }

    /**
     * 添加注解
     */
    protected StringBuilder addAnnotations(){
        if(sb == null){
            sb = new StringBuilder();
        }

        if(annotations != null && annotations.size()>0){
            for(String annotation : annotations){
                newLine();
                table().append(annotation);
            }
        }
        return sb;
    }

    /**
     * 添加注解
     */
    protected StringBuilder addClassAnnotations(){
        if(sb == null){
            sb = new StringBuilder();
        }

        if(annotations != null && annotations.size()>0){
            for(String annotation : annotations){
                newLine().append(annotation);
            }
        }
        return sb;
    }

    protected StringBuilder addParamAnnotations(){
        if(sb == null){
            sb = new StringBuilder();
        }

        if(annotations != null && annotations.size()>0){
            for(String annotation : annotations){
                sb.append(annotation);
                blank();
            }
        }
        return sb;
    }


    public abstract String getSource();

    public StringBuilder newLine(){
        sb.append("\r\n");
        return sb;
    }

    public StringBuilder newLine(int lines){
        for (int i = 0; i < lines; i++) {
            this.newLine();
        }
        return sb;
    }

    /**
     * 空格
     */
    public StringBuilder blank(){
        sb.append(" ");
        return sb;
    }

    /**
     * 制表
     * @return
     */
    public StringBuilder table() {
        sb.append("    ");
        return sb;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
}
