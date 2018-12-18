package com.dev999.maven.genertation.utils;

import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.util.messages.Messages;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author helecong
 * @date 2018/12/14
 */
public class FileUtils {
    private static String encoding = "UTF-8";


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

    /**
     * Writes, or overwrites, the contents of the specified file
     *
     * @param file
     * @param content
     */
    public static void writeFile(File file, String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;

        osw = new OutputStreamWriter(fos, encoding);

        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }

    public static File getDirectory(String targetProject, String targetPackage)
            throws ShellException {


        String path = targetProject ;
        File project = new File(path);
        if (!project.exists()) {
            project.mkdirs();
        }

        if (!project.isDirectory()) {
            throw new ShellException(Messages.getString("Warning.9", //$NON-NLS-1$
                    project.getAbsolutePath()));
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, "."); //$NON-NLS-1$
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                throw new ShellException(Messages.getString("Warning.10", //$NON-NLS-1$
                        directory.getAbsolutePath()));
            }
        }

        return directory;
    }
}
