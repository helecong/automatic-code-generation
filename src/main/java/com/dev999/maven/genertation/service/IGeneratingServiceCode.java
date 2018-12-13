package com.dev999.maven.genertation.service;

/**
 * 生产service层代码
 * @author helecong
 * @date 2018/12/12
 */
public interface IGeneratingServiceCode {

    String createServiceFile(String packagePath,String interfaceName,String superclassName);

}
