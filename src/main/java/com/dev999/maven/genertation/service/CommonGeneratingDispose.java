package com.dev999.maven.genertation.service;

import com.dev999.maven.genertation.MavenProgressCallback;
import com.dev999.maven.genertation.dom.dev999.DefaultControllerInterfaceJavaFile;
import com.dev999.maven.genertation.dom.dev999.DefaultControllerJavaFile;
import com.dev999.maven.genertation.dom.dev999.DefaultServiceInterfaceJavaFile;
import com.dev999.maven.genertation.dom.dev999.DefaultServiceJavaFile;
import com.dev999.maven.genertation.property.ClassProperty;
import com.dev999.maven.genertation.property.VariableProperty;
import com.dev999.maven.genertation.utils.FileUtils;
import com.dev999.maven.genertation.utils.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.exception.ShellException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class CommonGeneratingDispose extends CommonGeneratingParam {



    /**
     * 打印maven信息
     */
    protected MavenProgressCallback callback;



    public CommonGeneratingDispose(MavenProgressCallback callback) {
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
            VariableProperty services = createServices(entityName);

            //TODO 创建controller
            createContert(entityName,services);
        }

        saveFile();

    }

    private void createContert(String entityName, VariableProperty services) {
        if(!generationController){
            return;
        }

        String name = StringUtils.firstNameUpper(entityName);

        DefaultControllerInterfaceJavaFile controllerInterfaceJavaFile = new DefaultControllerInterfaceJavaFile(this);
        controllerInterfaceJavaFile.setEntityName(name);
        controllerInterfaceJavaFile.initSource();
        sourceList.add(controllerInterfaceJavaFile);

        DefaultControllerJavaFile controllerJavaFile = new DefaultControllerJavaFile(this);
        controllerJavaFile.setEntityName(name);
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
                callback.startTask(targetProject);
                callback.startTask(clazz.getPackagePath());
                callback.startTask(clazz.getClassName());

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
     */
    private VariableProperty createServices(String entityName) {
        if(!generationService&&!generationController){
            return null;
        }
        String name = StringUtils.firstNameUpper(entityName);

        DefaultServiceInterfaceJavaFile interfaceJavaFile = new DefaultServiceInterfaceJavaFile();
        interfaceJavaFile.setDaoPackage(targetDaoPackage).setDaoBeanName(name+"Mapper");
        interfaceJavaFile.setEntityPackage(targetEntityPackage).setEntityName(name);
        interfaceJavaFile.setClassName("I"+name+"Service");
        interfaceJavaFile.setPackagePath(servicePackagePath+"."+name.toLowerCase());
        interfaceJavaFile.initSource();
        sourceList.add(interfaceJavaFile);

        DefaultServiceJavaFile serviceFile = new DefaultServiceJavaFile();
        serviceFile.setDaoPackage(targetDaoPackage).setDaoBeanName(name+"Mapper");
        serviceFile.setEntityPackage(targetEntityPackage).setEntityName(name);
        serviceFile.setClassName(name+"ServiceImpl");
        serviceFile.setPackagePath(servicePackagePath+"."+name.toLowerCase()+".impl");
        serviceFile.initSource();
        sourceList.add(serviceFile);

        // 返回变量
        VariableProperty variableProperty = new VariableProperty("I"+name+"Service",StringUtils.firstNameLower(name)+"Service");
        variableProperty.setFullyQualifiedName(servicePackagePath+"."+name.toLowerCase()+"."+"I"+name+"Service");
        variableProperty.setAnnotations("@Autowired");

        return variableProperty;

    }


}
