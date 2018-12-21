package com.dev999.maven.genertation;

import com.dev999.maven.genertation.mybatis.api.MyBatisGenerator;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.ClassloaderUtility;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Goal which generates MyBatis/iBATIS artifacts.
 * 
 * @goal generate
 * @phase automatic-code-generation
 */
public class MyBatisGeneratorMojo extends AbstractMojo {

    /**
     * @parameter expression="${project}"
     * @required
     * @readonly
     * 
     */
    private MavenProject project;

    /**
     * @parameter expression="${mybatis.generator.outputDirectory}"
     *            default-value=
     *            "${project.build.directory}/generated-sources/mybatis-generator"
     * @required
     */
    private File outputDirectory;

    /**
     * Location of the configuration file.
     * 
     * @parameter expression="${mybatis.generator.configurationFile}"
     *            default-value
     *            ="${basedir}/src/main/resources/generator/generatorConfig.xml"
     * @required
     */
    private File configurationFile;

    /**
     * Specifies whether the mojo writes progress messages to the log
     * 
     * @parameter expression="${mybatis.generator.verbose}" default-value=false
     */
    private boolean verbose;

    /**
     * Specifies whether the mojo overwrites existing files. Default is false.
     * 
     * @parameter expression="${mybatis.generator.overwrite}"
     *            default-value=false
     */
    private boolean overwrite;

    /**
     * Location of a SQL script file to run before generating code. If null,
     * then no script will be run. If not null, then jdbcDriver, jdbcURL must be
     * supplied also, and jdbcUserId and jdbcPassword may be supplied.
     * 
     * @parameter expression="${mybatis.generator.sqlScript}"
     */
    private String sqlScript;

    /**
     * JDBC Driver to use if a sql.script.file is specified
     * 
     * @parameter expression="${mybatis.generator.jdbcDriver}"
     */
    private String jdbcDriver;

    /**
     * JDBC URL to use if a sql.script.file is specified
     * 
     * @parameter expression="${mybatis.generator.jdbcURL}"
     */
    private String jdbcURL;

    /**
     * JDBC user ID to use if a sql.script.file is specified
     * 
     * @parameter expression="${mybatis.generator.jdbcUserId}"
     */
    private String jdbcUserId;

    /**
     * JDBC password to use if a sql.script.file is specified
     * 
     * @parameter expression="${mybatis.generator.jdbcPassword}"
     */
    private String jdbcPassword;

    /**
     * Comma delimited list of table names to generate
     * 
     * @parameter expression="${mybatis.generator.tableNames}"
     */
    private String tableNames;

    /**
     * Comma delimited list of contexts to generate
     * 
     * @parameter expression="${mybatis.generator.contexts}"
     */
    private String contexts;

    @Override
    public void execute() throws MojoExecutionException {
        // 借助 mybatis-genertation 生成dao层
        Configuration configuration = genertationDao();

//        getLog().info("开始生产serivice");
//        // 生成service 和 controller
//        MavenProgressCallback callback = new MavenProgressCallback(getLog(),true);
//        GeneratorCentext commonGeneratingDispose = new GeneratorCentext(callback);
//        commonGeneratingDispose.initParam(configuration);
//        commonGeneratingDispose.startCreate();



    }

    private Configuration genertationDao() throws MojoExecutionException{
        // add resource directories to the classpath.  This is required to support
        // use of a properties file in the build.  Typically, the properties file
        // is in the project's source tree, but the plugin classpath does not
        // include the project classpath.
        // 借助这mybatis-generator 解析完成的config
        Configuration resultConfig = null;
        @SuppressWarnings("unchecked")
        List<Resource> resources = project.getResources();
        List<String> resourceDirectories = new ArrayList<String>();
        for (Resource resource: resources) {
            resourceDirectories.add(resource.getDirectory());
        }
        // 讲资源目录加入类加载器 为什么呢？
        ClassLoader cl = ClassloaderUtility.getCustomClassloader(resourceDirectories);
        ObjectFactory.addResourceClassLoader(cl);

        // 判断配置文件是否存在
        if (configurationFile == null) {
            throw new MojoExecutionException(
                    Messages.getString("RuntimeError.0")); //$NON-NLS-1$
        }

        List<String> warnings = new ArrayList<String>();

        if (!configurationFile.exists()) {
            throw new MojoExecutionException(Messages.getString(
                    "RuntimeError.1", configurationFile.toString())); //$NON-NLS-1$
        }

        runScriptIfNecessary();

        // 加载表名 默认没有
        Set<String> fullyqualifiedTables = new HashSet<String>();
        if (StringUtility.stringHasValue(tableNames)) {
            StringTokenizer st = new StringTokenizer(tableNames, ","); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String s = st.nextToken().trim();
                if (s.length() > 0) {
                    fullyqualifiedTables.add(s);
                }
            }
        }

        // 加载上下文 默认没有
        Set<String> contextsToRun = new HashSet<String>();
        if (StringUtility.stringHasValue(contexts)) {
            StringTokenizer st = new StringTokenizer(contexts, ","); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String s = st.nextToken().trim();
                if (s.length() > 0) {
                    contextsToRun.add(s);
                }
            }
        }

        try {
            ConfigurationParser cp = new ConfigurationParser(
                    project.getProperties(), warnings);
            // 获取配置文件
            Configuration config = cp.parseConfiguration(configurationFile);
            resultConfig = config;

            ShellCallback callback = new MavenShellCallback(this, overwrite);

            MavenProgressCallback callback1 = new MavenProgressCallback(getLog(),
                    verbose);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                    callback, warnings,callback1);


            myBatisGenerator.generate(callback1, contextsToRun, fullyqualifiedTables);

        } catch (XMLParserException e) {
            for (String error : e.getErrors()) {
                getLog().error(error);
            }

            throw new MojoExecutionException(e.getMessage());
        } catch (SQLException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (InvalidConfigurationException e) {
            for (String error : e.getErrors()) {
                getLog().error(error);
            }

            throw new MojoExecutionException(e.getMessage());
        } catch (InterruptedException e) {
            // ignore (will never happen with the DefaultShellCallback)
            ;
        }catch (Exception e) {
            // ignore (will never happen with the DefaultShellCallback)
            ;
            getLog().error(e);
        }

        for (String error : warnings) {
            getLog().warn(error);
        }

        if (project != null && outputDirectory != null
                && outputDirectory.exists()) {
            project.addCompileSourceRoot(outputDirectory.getAbsolutePath());

            Resource resource = new Resource();
            resource.setDirectory(outputDirectory.getAbsolutePath());
            resource.addInclude("**/*.xml");
            project.addResource(resource);
        }
        return resultConfig;
    }

    /**
     * 如果需要就运行sql脚本
     * @throws MojoExecutionException
     */
    private void runScriptIfNecessary() throws MojoExecutionException {
        if (sqlScript == null) {
            return;
        }

        SqlScriptRunner scriptRunner = new SqlScriptRunner(sqlScript,
                jdbcDriver, jdbcURL, jdbcUserId, jdbcPassword);
        scriptRunner.setLog(getLog());
        scriptRunner.executeScript();
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    @Override
    public String toString() {
        return "MyBatisGeneratorMojo{" +
                "project=" + project +
                ", outputDirectory=" + outputDirectory +
                ", configurationFile=" + configurationFile +
                ", verbose=" + verbose +
                ", overwrite=" + overwrite +
                ", sqlScript='" + sqlScript + '\'' +
                ", jdbcDriver='" + jdbcDriver + '\'' +
                ", jdbcURL='" + jdbcURL + '\'' +
                ", jdbcUserId='" + jdbcUserId + '\'' +
                ", jdbcPassword='" + jdbcPassword + '\'' +
                ", tableNames='" + tableNames + '\'' +
                ", contexts='" + contexts + '\'' +
                '}';
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public File getConfigurationFile() {
        return configurationFile;
    }

    public void setConfigurationFile(File configurationFile) {
        this.configurationFile = configurationFile;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public String getSqlScript() {
        return sqlScript;
    }

    public void setSqlScript(String sqlScript) {
        this.sqlScript = sqlScript;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    public String getJdbcUserId() {
        return jdbcUserId;
    }

    public void setJdbcUserId(String jdbcUserId) {
        this.jdbcUserId = jdbcUserId;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    public String getContexts() {
        return contexts;
    }

    public void setContexts(String contexts) {
        this.contexts = contexts;
    }
}
