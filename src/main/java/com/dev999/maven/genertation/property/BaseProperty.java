package com.dev999.maven.genertation.property;

import com.dev999.maven.genertation.utils.DateUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 基本操作
 *
 * @author helecong
 * @date 2018/12/14
 */
public abstract class BaseProperty {
    protected StringBuilder sb;

    /**
     * 解释说明
     */
    protected String doc = "";

    protected Set<String> annotations;

    public BaseProperty() {
        annotations = new HashSet<String>();
    }

    /**
     * 添加注释
     */
    protected StringBuilder addDoc(String mes) {
        if (sb == null) {
            sb = new StringBuilder();
        }

        table().append("/**");
        newLine();
        table().append("* ").append(mes);
        if (!"".equals(doc)) {
            newLine();
            table().append("* ").append(doc);
        }
        newLine();
        table().append("*/");
        return sb;
    }

    /**
     * 添加作者和时间
     */
    private StringBuilder addAuthAndTime() {
        if (sb == null) {
            sb = new StringBuilder();
        }
        newLine();
        blank().append("* @author " + getUser());
        newLine();
        blank().append("* @version V1.0.0 Created in " + DateUtils.getFormatDate(new Date(), "yyyy/MM/dd HH:mm:ss"));

        return sb;
    }

    protected String getUser() {
        String author = "";
        Map<String, String> getenv = System.getenv();
        author = getenv.get("USER");
        if ("".equals(author) || author == null) {
            author = getenv.get("LOGNAME");
        }
        return author;
    }

    /**
     * 添加注释
     */
    protected StringBuilder addClassDoc(String mes) {
        if (sb == null) {
            sb = new StringBuilder();
        }

        sb.append("/**");
        newLine();
        blank().append("* ").append(mes);
        if (!"".equals(doc)) {
            newLine();
            blank().append("* ").append(doc);
        }
        // 添加作者和时间
        addAuthAndTime();
        newLine();
        blank().append("*/");
        return sb;
    }

    /**
     * 添加注解
     */
    protected StringBuilder addAnnotations() {
        if (sb == null) {
            sb = new StringBuilder();
        }

        if (annotations != null && annotations.size() > 0) {
            for (String annotation : annotations) {
                newLine();
                table().append(annotation);
            }
        }
        return sb;
    }

    /**
     * 添加注解
     */
    protected StringBuilder addClassAnnotations() {
        if (sb == null) {
            sb = new StringBuilder();
        }

        if (annotations != null && annotations.size() > 0) {
            for (String annotation : annotations) {
                newLine().append(annotation);
            }
        }
        return sb;
    }

    protected StringBuilder addParamAnnotations() {
        if (sb == null) {
            sb = new StringBuilder();
        }

        if (annotations != null && annotations.size() > 0) {
            for (String annotation : annotations) {
                sb.append(annotation);
                blank();
            }
        }
        return sb;
    }


    public abstract String getSource();

    public StringBuilder newLine() {
        sb.append("\r\n");
        return sb;
    }

    public StringBuilder newLine(int lines) {
        for (int i = 0; i < lines; i++) {
            this.newLine();
        }
        return sb;
    }

    /**
     * 空格
     */
    public StringBuilder blank() {
        sb.append(" ");
        return sb;
    }

    /**
     * 制表
     *
     * @return
     */
    public StringBuilder table() {
        sb.append("    ");
        return sb;
    }

    /**
     * 制表
     *
     * @return
     */
    public StringBuilder table(int i) {
        for (int j = 0; j < i; j++) {
            table();
        }
        return sb;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public StringBuilder getSb() {
        return sb;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    public Set<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Set<String> annotations) {
        this.annotations = annotations;
    }

    public void setAnnotations(String annotation) {
        if (annotations == null) {
            annotations = new HashSet<String>();
        }
        annotations.add(annotation);
    }
}
