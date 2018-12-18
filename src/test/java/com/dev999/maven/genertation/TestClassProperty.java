package com.dev999.maven.genertation;

import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.MethodProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author helecong
 * @date 2018/12/14
 */
public class TestClassProperty {

    public static void main(String[] args) {

        ClassProperty classProperty = new ClassProperty();

        classProperty.setPackagePath("com.dev999.common");
        classProperty.setClassName("TestUtil");

        //添加方法
        addMethods(classProperty);


        System.out.println(classProperty.getSource());
    }

    private static void addMethods(ClassProperty classProperty) {
        List<MethodProperty> methods = new ArrayList<MethodProperty>();

        MethodProperty hello = new MethodProperty();
        hello.setMothodName("hello");
        hello.setBody("        System.out.println(\"hello\");");

        methods.add(hello);
        classProperty.setMethods(methods);
    }

}
