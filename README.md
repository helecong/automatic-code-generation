#  自动生成代码插件

# 功能说明

- 自动生成从数据库到controller的简单增删改查代码
- 可选择是否生成controller

# 附加说明

- 自动生成数据库和dao借助原有开元插件（org.mybatis.generator  mybatis-generator-maven-plugin）

# 使用说明

## 加入maven插件

     <plugin>
         <groupId>com.dev999.maven</groupId>
         <artifactId>automatic-code-generation</artifactId>
         <version>1.0.0</version>
         <configuration>
             <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
             <overwrite>true</overwrite>
             <verbose>true</verbose>
         </configuration>
         <dependencies>
             <dependency>
                 <groupId>mysql</groupId>
                 <artifactId>mysql-connector-java</artifactId>
                 <version>${mysql.version}</version>
             </dependency>
             <dependency>
                 <groupId>tk.mybatis</groupId>
                 <artifactId>mapper</artifactId>
                 <version>3.4.0</version>
             </dependency>
         </dependencies>
     </plugin>

## 添加相关依赖

详细需要的依赖可以查看<依赖插件>一栏

## 执行maven命令

mvn automatic-code-generation:generate


# 依赖插件

- 1 spring boot

      <parent>
      	<groupId>org.springframework.boot</groupId>
      	<artifactId>spring-boot-starter-parent</artifactId>
      	<version>1.5.9.RELEASE</version>
      </parent>

- 2 swagger插件 

swagger 是用于生成在线的接口文档，当需要插件生成swagger文档时候一下插件是必须要的。
    
            <!-- swagger注释依赖 -->
    		<dependency>
    			<groupId>io.springfox</groupId>
    			<artifactId>springfox-swagger2</artifactId>
    			<version>2.4.0</version>
    		</dependency>
    		<dependency>
    			<groupId>io.springfox</groupId>
    			<artifactId>springfox-swagger-ui</artifactId>
    			<version>2.4.0</version>
    		</dependency>
    		

- 3 dev999 系列插件

dev999系列插件，封装了同一格式的返回，已经日志系统，数据库依赖文件等 web需要的东西

            <!-- dev999插件 -->
            <dependency>
                <groupId>com.dev999.framework</groupId>
                <artifactId>database</artifactId>
                <version>0.0.1-SNAPSHOT</version>
            </dependency>
            <dependency>
                <groupId>com.dev999.framework</groupId>
                <artifactId>dev999-framework-common</artifactId>
                <version>0.0.1-SNAPSHOT</version>
            </dependency>
            <dependency>
                <groupId>com.dev999.framework</groupId>
                <artifactId>dev999-framework-queue</artifactId>
                <version>0.0.1-SNAPSHOT</version>
            </dependency>

- 4 pagehelper 分页插件

pagehelper 插件是用于分页查询，此插件封装在dev999框架中，不用手动依赖进去

            <!--pagehelper -->
    		<dependency>
    			<groupId>com.github.pagehelper</groupId>
    			<artifactId>pagehelper-spring-boot-starter</artifactId>
    			<version>RELEASE</version>
    		</dependency>
    		

# 开发笔记

## 如何开发maven插件

参考地址： http://maven.apache.org/plugin-developers/index.html

- 执行命令

mvn groupId ：artifactId ：version ：goal

mvn com.dev999.maven ：automatic-code-generation ：1.0.0 ：generation