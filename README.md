#  自动生成代码插件

# 功能说明

- 自动生成从数据库到controller的简单增删改查代码
- 可选择是否生成controller

# 附加说明

- 自动生成数据库和dao借助原有开元插件（org.mybatis.generator  mybatis-generator-maven-plugin）

# 开发笔记

## 如何开发maven插件

参考地址： http://maven.apache.org/plugin-developers/index.html

- 执行命令

mvn groupId ：artifactId ：version ：goal

mvn com.dev999.maven ：automatic-code-generation ：1.0.0 ：generation
