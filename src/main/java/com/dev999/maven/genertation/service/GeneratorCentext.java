package com.dev999.maven.genertation.service;

import com.dev999.maven.genertation.MavenProgressCallback;
import com.dev999.maven.genertation.dom.dev999.DefaultControllerInterfaceJavaFile;
import com.dev999.maven.genertation.dom.dev999.DefaultControllerJavaFile;
import com.dev999.maven.genertation.dom.dev999.DefaultServiceInterfaceJavaFile;
import com.dev999.maven.genertation.dom.dev999.DefaultServiceJavaFile;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.utils.FileUtils;
import com.dev999.maven.genertation.utils.NameUtils;
import com.dev999.maven.genertation.utils.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.exception.ShellException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mybatis.generator.internal.util.messages.Messages.getString;


/**
 * 公共生成代码处理
 *
 * 统一处理
 *
 * @author helecong
 * @date 2018/12/12
 */
public class GeneratorCentext extends GeneratorCentextParam {



    /**
     * 打印maven信息
     */
    protected MavenProgressCallback callback;


    public GeneratorCentext(MavenProgressCallback callback) {
        super();
        this.callback = callback;

    }



    /**
     * 开始创建
     * @param generatedJavaFiles
     */
    public void startCreate(List<GeneratedJavaFile> generatedJavaFiles){

        this.generatedJavaFiles = generatedJavaFiles;

        for (String entityName : entityNames){
            //TODO 创建service
            createServices(entityName);

            //TODO 创建controller
            createContert(entityName);
        }

        saveFile();

    }

    private void createContert(String entityName) {
        if(!generationController){
            return;
        }


        DefaultControllerInterfaceJavaFile controllerInterfaceJavaFile = new DefaultControllerInterfaceJavaFile(this);
        controllerInterfaceJavaFile.setEntityName(entityName);
        controllerInterfaceJavaFile.initSource();
        sourceList.add(controllerInterfaceJavaFile);

        DefaultControllerJavaFile controllerJavaFile = new DefaultControllerJavaFile(this);
        controllerJavaFile.setEntityName(entityName);
        controllerJavaFile.initSource();
        sourceList.add(controllerJavaFile);

    }

    /**
     * 保存文件
     */
    private void saveFile() {
        if(sourceList == null || sourceList.size()<=0){
            return;
        }

        for(ClassProperty clazz : sourceList){

            File targetFile;
            String source;

            try {

                File directory = FileUtils.getDirectory(targetProject,clazz.getPackagePath());
                targetFile = new File(directory, clazz.getClassName()+".java");
                source = clazz.getSource();

                callback.checkCancel();
                callback.startTask(getString(
                        "Progress.15", targetFile.getName()));
                FileUtils.writeFile(targetFile,source);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ShellException e) {
                e.printStackTrace();
            }

        }


    }

    /**
     * 创建service层代码
     * @param entityName
     */
    private void createServices(String entityName) {
        if(!generationService&&!generationController){
            return;
        }

        DefaultServiceInterfaceJavaFile interfaceJavaFile = new DefaultServiceInterfaceJavaFile(this);
        interfaceJavaFile.setEntityName(entityName);
        interfaceJavaFile.initSource();
        sourceList.add(interfaceJavaFile);

        DefaultServiceJavaFile serviceFile = new DefaultServiceJavaFile(this);
        serviceFile.setEntityName(entityName);
        serviceFile.initSource();
        sourceList.add(serviceFile);

    }


}
