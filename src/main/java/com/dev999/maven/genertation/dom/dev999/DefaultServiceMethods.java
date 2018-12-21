package com.dev999.maven.genertation.dom.dev999;

import com.dev999.maven.genertation.dom.dev999.method.service.*;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.service.GeneratorCentext;

/**
 * 默认的service层方法
 * @author helecong
 * @date 2018/12/17
 */
public class DefaultServiceMethods {
    private VariableProperty entityVariable;
    private GeneratorCentext generatorCentext;
    private ClassProperty topClassProperty;


    public DefaultServiceMethods( GeneratorCentext generatorCentext,ClassProperty topClassProperty, VariableProperty entityVariable){
        this.entityVariable = entityVariable;
        this.generatorCentext = generatorCentext;
        this.topClassProperty = topClassProperty;
    }

    public void getDefaultMethods(){

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

        new UpdateMethodServiceModel(generatorCentext,topClassProperty,entityVariable);

    }

    private void queryMethod() {

        new QueryListByMapMethodServiceModel(generatorCentext,topClassProperty,entityVariable);
        new QueryByIdMethodServiceModel(generatorCentext,topClassProperty,entityVariable);

        
    }

    private void delectMethod() {

        new DeleteByIdMethodServiceModel(generatorCentext,topClassProperty,entityVariable);

    }

    private void insertMethod() {

        new InsertMethodServiceModel(generatorCentext,topClassProperty,entityVariable);
        new InsertListMethodServiceModel(generatorCentext,topClassProperty,entityVariable);

    }

}
