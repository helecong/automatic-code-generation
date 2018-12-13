package com.dev999.maven.genertation;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

/**
 * @author helecong
 * @date 2018/12/12
 */
public class FistMojo extends AbstractMojo {

    /**
     * @parameter expression="${project}"
     * @required
     * @readonly
     *
     */
    private MavenProject project;

    /**
     * 是否生成controller层代码.默认为true
     */
    private boolean generationController = true;

    /**
     * 是否生成service层代码.默认为true
     */
    private boolean generationService = true;

    /**
     * 是否添加默认的swagger注解
     */
    private boolean addSwaggerAPIAnnotation = true;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hellow world");
    }
}
