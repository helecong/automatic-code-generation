package com.dev999.maven.genertation.utils;

import java.io.File;
import java.io.IOException;

/**
 * @author helecong
 * @date 2018/12/14
 */
public class FileUtils {


    public static File createFile(String path){
        File file = new File(path);

        File parentFile = file.getParentFile();
        if(!parentFile.exists() || !parentFile.isDirectory()){
            parentFile.mkdirs();
        }
        if(!file.exists() || !file.isFile()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return file;
    }
}
