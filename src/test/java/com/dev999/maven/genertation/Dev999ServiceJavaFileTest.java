package com.dev999.maven.genertation;

import com.dev999.maven.genertation.dom.dev999.DefaultServiceInterfaceJavaFile;

/**
 * @author helecong
 * @date 2018/12/14
 */
public class Dev999ServiceJavaFileTest {

    public static void main(String[] args) {

        DefaultServiceInterfaceJavaFile dev999ServiceJavaFile = new DefaultServiceInterfaceJavaFile();

        dev999ServiceJavaFile.setDaoPackage("com.dev999.common.dao").setDaoBeanName("TestMapper").setDaoBeanAlias("测试");
        dev999ServiceJavaFile.setEntityPackage("com.dev999.com.entity").setEntityName("Test").setEntityAlias("测试实体");

        dev999ServiceJavaFile.setClassName("TestServiceImpl");
        dev999ServiceJavaFile.setPackagePath("com.dev999.common.service.impl");
        dev999ServiceJavaFile.initSource();

        System.out.println(dev999ServiceJavaFile.getSource());
    }


}
