package com.dev999.maven.genertation.utils;

import org.mybatis.generator.api.dom.java.Field;

import java.util.List;

/**
 * @author helecong
 * @date 2018/12/18
 */
public class FieldUtils {
    public static String getFielDoc(Field field) {
        String result = "";

        List<String> javaDocLines = field.getJavaDocLines();
        if(javaDocLines.size()>2){
            result = javaDocLines.get(1).substring(2).trim();
        }

        return result;
    }
}
