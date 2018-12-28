# spring-starter

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

5. [MariaDB](https://mariadb.com/downloads/)



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
