package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.dom.dev999.method.controller.*;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.GeneratorCentext;

/**
 * 默认的Controller层方法
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultControllerMethods {
    private VariableProperty entityVariable;
    private GeneratorCentext generatorCentext;
    private ClassProperty topClassProperty;


    public DefaultControllerMethods( GeneratorCentext generatorCentext,ClassProperty topClassProperty, VariableProperty entityVariable){
        this.entityVariable = entityVariable;
        this.generatorCentext = generatorCentext;
        this.topClassProperty = topClassProperty;
    }

    public void initDefaultMethods(){

        // 增加
        insertMethod();

        // 删除
        delectMethod();

        // 修改
        updateMethod();

        // 查询
        queryMethod();

    }

    private void updateMethod() {

        new UpdateMethodControllerModel(generatorCentext,topClassProperty,entityVariable);

    }

    private void queryMethod() {

        new QueryByIdMethodControllerModel(generatorCentext,topClassProperty,entityVariable);
        new QueryListByMapMethodControllerModel(generatorCentext,topClassProperty,entityVariable);

    }

    private void delectMethod() {

        new DeleteByIdMethodControllerModel(generatorCentext,topClassProperty,entityVariable);

    }

    private void insertMethod() {

        new InsertMethodControllerModel(generatorCentext,topClassProperty,entityVariable);
        new InstallListMethodControllerModel(generatorCentext,topClassProperty,entityVariable);

    }

}
