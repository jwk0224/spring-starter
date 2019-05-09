# spring-starter

> Spring Framework is a Java based open source application framework.

The standard spring web mvc sample project with essential settings

## Features

- Spring 5.1.3
- Template codes including Controller, Service, DAO, JSP and etc.
- Dependencies used :
    - Spring & Web
        - spring-context
        - spring-webmvc
        - commons-fileupload
        - jackson-databind
    - DB
        - mysql-connector-java
        - spring-jdbc
        - mybatis
        - mybatis-spring
    - Test
        - junit
        - spring-test
    - AOP
        - spring-aop
        - aspectjweaver
    - Logging
        - slf4j-api
        - jcl-over-slf4j
        - logback-classic

## Installation

1. [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
  
2. [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/)

3. Spring Tools 3 Add-On (Eclipse Market Place)

4. [Apache Tomcat 8](https://tomcat.apache.org/download-80.cgi)

5. [MySQL](https://www.mysql.com/downloads/) or [MariaDB](https://mariadb.com/downloads/)

## Usage

Create database, user and table as follows.

    CREATE DATABASE spring;

    CREATE USER 'spring_user'@'localhost' IDENTIFIED BY 'spring_password';

    GRANT ALL PRIVILEGES ON spring.* TO 'spring_user'@'localhost';

    CREATE TABLE `todo` (
      `todo_id` int(11) NOT NULL,
      `content` varchar(30) DEFAULT NULL,
      `done` tinyint(4) DEFAULT NULL,
      PRIMARY KEY (`todo_id`)
    );

Update the code below using your actual file upload directory in `FileUploadController.java`

    File f = new File("/Users/USER_NAME/Downloads", file.getOriginalFilename());

## Details

Follow step by step guide below to make a standard spring web mvc project manually.<br/>

- [1. Maven Project](#1-maven-project)
- [2. Spring](#2-spring)
- [3. Spring Web](#3-spring-web)
- [4. File Upload](#4-file-upload)
- [5. JSON](#5-json)
- [6. JDBC](#6-jdbc)
- [7. MyBatis](#7-mybatis)
- [8. Test](#8-test)
- [9. AOP](#9-aop)
- [10. Transaction](#10-transaction)
- [11. Interceptor](#11-interceptor)
- [12. Logging](#12-logging)

<a id="maven-project"></a>
## 1. Maven Project

1. *File > New > Dynamic Web Project*
    - Target runtime : Apache tomcat v8.0
    - Dynamic web module version : 3.1
    - Source folders on build path (Maven standard)
     
            src/main/java
            src/main/resources
            src/test/java
            src/test/resources

    - Content directory (Maven standard)

            src/main/webapp
    - Generate web.xml deployment descriptor : checked

2. *Configure > Convert to Maven Project*
    - GroupId : spring-starter (com.company)
    - ArtifactId : spring-starter
    - Version : 0.0.1-SNAPSHOT (X.X.X-RELEASE)

3. Create following packages

        src/main/java/starter.aspect
        src/main/java/starter.controller
        src/main/java/starter.dao
        src/main/java/starter.exception
        src/main/java/starter.interceptor
        src/main/java/starter.service
        src/main/java/starter.vo

        src/test/java/starter.aspect
        src/test/java/starter.controller
        src/test/java/starter.dao
        src/test/java/starter.exception
        src/test/java/starter.interceptor
        src/test/java/starter.service
        src/test/java/starter.vo

<a id="spring"></a>
## 2. Spring

> [spring-context](https://mvnrepository.com/artifact/org.springframework/spring-context)

- Add spring-context dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.1.3.RELEASE</version>
    </dependency>
    ```

<a id="spring-web"></a>
## 3. Spring Web

> [spring-webmvc](https://mvnrepository.com/artifact/org.springframework/spring-webmvc)

1. Add spring-webmvc dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.3.RELEASE</version>
    </dependency>
    ```

2. Create following directories

        src/main/webapp/WEB-INF/spring (.xml files)
        src/main/webapp/WEB-INF/views (.jsp files)
        src/main/webapp/WEB-INF/resources/images (.png files)
        src/main/webapp/WEB-INF/resources/styles (.css files)
   
3. Create following *Spring Bean Configuration File*s

        src/main/webapp/WEB-INF/spring/root-context.xml
        src/main/webapp/WEB-INF/spring/servlet-context.xml

4. Add *context* in  *Namespaces* tab of `root-context.xml`

5. Add following code inside `<beans>` tag in `root-context.xml`

    ```html
    <!--  Basic Web Configuration -->
    <context:component-scan base-package="starter">
            <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
    ```
  
6. Add *context, mvc* in  *Namespaces* tab of `servlet-context.xml`

7. Add following code inside `<beans>` tag in `servlet-context.xml`

    ```html
    <!--  Basic Web Configuration -->
    <context:component-scan base-package="starter" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <mvc:annotation-driven/>

    <mvc:resources location="/WEB-INF/resources/" mapping="/resources/**"/>

    <!--  ViewResolver Configuration -->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    ```

8. Delete default code inside `<web-app>` tag in src/main/webapp/WEB-INF/`web.xml`
   
9.  Add following code inside `<web-app>` tag in `web.xml`

    ```html
    <!-- Root & Other Context Configuration -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			/WEB-INF/spring/root-context.xml
		</param-value>
    </context-param>
    
    <!-- Dispatcher Servlet Configuration -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/spring/servlet-context.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- Encoding Configuration -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ```

<a id="file-upload"></a>
## 4. File Upload

> [commons-fileupload](https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload)

1. Add commons-fileupload dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.3.3</version>
    </dependency>
    ```
2. Add following code inside `<beans>` tag in `servlet-context.xml`

    ```html
    <!--  MultipartResolver Configuration -->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="maxUploadSize" value="104857600"/>
        <property name="defaultEncoding" value="UTF-8"/>
    </bean>
    ```

<a id="json"></a>
## 5. JSON

>[jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)

- Add jackson-databind dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.8</version>
    </dependency>
    ```

<a id="jdbc"></a>
## 6. JDBC

> [mysql-connector-java ](https://mvnrepository.com/artifact/mysql/mysql-connector-java) (in case of MySQL, MariaDB)

1. Add mysql-connector-java dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.13</version>
    </dependency>
    ```

2. Create the following directory

        src/main/resources/config


3. Create the following file
   
        src/main/resources/config/database.properties


4. Add following code in `database.properties`

        # My SQL Driver
        db.driverClass=com.mysql.cj.jdbc.Driver
        db.url=jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul
        db.username=spring_user
        db.password=spring_password

> [spring-jdbc](https://mvnrepository.com/artifact/org.springframework/spring-jdbc)

5. Add spring-jdbc dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.1.3.RELEASE</version>
    </dependency>
    ```

6. Create the following *Spring Bean Configuration File*

        src/main/webapp/WEB-INF/spring/datasource.xml

7. Add *context* in  *Namespaces* tab of `datasource.xml`

8. Add following code inside `<beans>` tag in `datasource.xml`

    ```html
    <!-- DataSource Configuration -->
    <context:property-placeholder location="classpath:config/database.properties"/>
    
    <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
        <property name="driverClass" value="${db.driverClass}"/>
        <property name="url" value="${db.url}"/>
        <property name="username" value="${db.username}"/>
        <property name="password" value="${db.password}"/>
    </bean>
    ```
9. Add datasource.xml path inside `<param-value>` tag in `web.xml`

    ```html
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            /WEB-INF/spring/root-context.xml
            /WEB-INF/spring/datasource.xml
        </param-value>
    </context-param>
    ```

<a id="mybatis"></a>
## 7. MyBatis

> [mybatis](https://mvnrepository.com/artifact/org.mybatis/mybatis)

1. Add mybatis dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.4.6</version>
    </dependency>
    ```

> [mybatis-spring](https://mvnrepository.com/artifact/org.mybatis/mybatis-spring)

2. Add mybatis-spring dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.3.2</version>
    </dependency>
    ```

3. Create the following file

        src/main/resources/mybatis-config.xml

4. Add following code in `mybatis-config.xml`

    ```html
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
    </configuration>
    ```

5. Create the following directory

        src/main/resources/mappers

6. Create the following file
   
        src/main/resources/mappers/todo-mapper.xml

7. Add following code in `todo-mapper.xml`

    ```html
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="mappers.todo-mapper">

    </mapper>
    ```


8. Add following code inside `<beans>` tag in `datasource.xml`

    ```html
    <!-- Mybatis Configuration -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations">
            <list>
                <value>classpath:mappers/todo-mapper.xml</value>
            </list>
        </property>
    </bean>

    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg ref="sqlSessionFactory"/>
    </bean>
    ```

<a id="test"></a>
## 8. Test

> [junit](https://mvnrepository.com/artifact/junit/junit/4.12)

1. Add junit dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    ```

> [spring-test](https://mvnrepository.com/artifact/org.springframework/spring-test)

2. Add spring-test dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>5.1.3.RELEASE</version>
        <scope>test</scope>
    </dependency>
    ```

<a id="aop"></a>
## 9. AOP

> [spring-aop](https://mvnrepository.com/artifact/org.springframework/spring-aop)

1. Add spring-aop dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
        <version>5.1.3.RELEASE</version>
    </dependency>
    ```

> [aspectjweaver](https://mvnrepository.com/artifact/org.aspectj/aspectjweaver
)

2. Add aspectjweaver dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.2</version>
    </dependency>
    ```

3. Add *aop* in  *Namespaces* tab of `root-context.xml`

4. Add following code inside `<beans>` tag in `root-context.xml`

    ```html
    <!--  AOP Configuration -->
    <aop:aspectj-autoproxy/>
    ```

<a id="transaction"></a>
## 10. Transaction

1. Add *aop, tx* in  *Namespaces* tab of `datasource.xml`

2. Add following code inside `<beans>` tag in `datasource.xml`

    ```html
    <!-- Transaction Configuration -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>

    <aop:config>
        <aop:pointcut expression="execution(* starter.service.*Service.*(..))" id="transactionPointcut"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="transactionPointcut"/>
    </aop:config>
    ```

<a id="interceptor"></a>
## 11. Interceptor

- Add following code inside `<beans>` tag in `servlet-context.xml`

    ```html
    <!-- Interceptor Configuration -->
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/index"/>
            <mvc:exclude-mapping path="/login"/>
            <mvc:exclude-mapping path="/signup"/>
            <bean id="loginCheckInterceptor" class="starter.interceptor.LoginCheckInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
    ```

<a id="logging"></a>
## 12. Logging

> [slf4j-api](https://mvnrepository.com/artifact/org.slf4j/slf4j-api)

1. Add slf4j-api dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.25</version>
    </dependency>
    ```

> [jcl-over-slf4j](https://mvnrepository.com/artifact/org.slf4j/jcl-over-slf4j)

2. Add jcl-over-slf4j dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>jcl-over-slf4j</artifactId>
        <version>1.7.25</version>
    </dependency>
    ```

> [logback-classic](https://mvnrepository.com/artifact/ch.qos.logback/logback-classic)

3. Add logback-classic dependency to `pom.xml`

    ```html
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version>
    </dependency>
    ```

4. Create the following file

        src/main/resources/logback.xml

5. Add following code in `logback.xml`

    ```html
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE configuration>
    <configuration>
        <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
            <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
                <pattern>
                    ▶ %-5level %d{HH:mm:ss.SSS} [%thread] %logger[%method:%line] - %msg%n
                </pattern>
            </encoder>
        </appender>
        
        <!-- <appender name="FILE" class="ch.qos.logback.core.FileAppender">
            <file>c:\testFile.log</file>
            <append>true</append>
            <encoder>
                <pattern>
                    ▶ %-5level %d{HH:mm:ss.SSS} [%thread] %logger[%method:%line] - %msg%n
                </pattern>
            </encoder>
        </appender> -->
        
        <!-- <appender name="dailyRolling" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>c:\logFile.log</file>
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <fileNamePattern>c:\logFile.%d{yyyy-MM-dd-HH}.log</fileNamePattern>
                <maxHistory>30</maxHistory>
            </rollingPolicy>
            <encoder>
                <pattern>
                    ▶ %-5level %d{HH:mm:ss.SSS} [%thread] %logger[%method:%line] - %msg%n
                </pattern>
            </encoder>
        </appender> -->
        
        <logger name="org.springframework" level="INFO" />
        <root level="info">
            <appender-ref ref="console" />
            <!-- <appender-ref ref="FILE" /> -->
            <!-- <appender-ref ref="dailyRolling" /> -->
        </root>
    </configuration>
    ```

6. Add `<exclusions>` tag inside spring-context dependency in `pom.xml`

    ```html
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.1.3.RELEASE</version>
        <exclusions>
            <exclusion>
                <groupId>commons-logging</groupId>
                <artifactId>commons-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    ```

7. Add `<exclusions>` tag inside spring-webmvc dependency in `pom.xml`

    ```html
    <dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-webmvc</artifactId>
	    <version>5.1.3.RELEASE</version>
	    <exclusions>
	    	<exclusion>
	    		<groupId>commons-logging</groupId>
	    		<artifactId>commons-logging</artifactId>
	    	</exclusion>
	    </exclusions>
	</dependency>
    ```
