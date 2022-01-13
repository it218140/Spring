# SpringBoot
## Prerequisites
### Database

#### Using Docker
[Install Docker](https://docs.docker.com/get-docker/)

```bash
docker run --name mysqldb -v mysqldbvol:/var/lib/mysql -p 3306:3306 -e MYSQL_USER=<MYSQL-USERNAME> -e MYSQL_PASSWORD=<MYSQL-PASSWORD> -e MYSQL_DATABASE=<DATABASE> -e MYSQL_ROOT_PASSWORD=<ROOT-PASSWORD> --rm -d mysql/mysql-server:latest
```
connect to mysql
```bash
mysql -h 127.0.0.1 -u <MYSQL-USERNAME> -p
```
import db schema (e.g schema.sql file)
```bash
mysql -h 127.0.0.1 -u <MYSQL-USERNAME> -p <DATABASE>  < schema.sql
```
### Application properties
Create a file named application.properties
```bash

jdbc.url=jdbc:mysql://SERVER:PORT/DATABASE?useSSL=false&allowPublicKeyRetrieval=true
jdbc.user=USERNAME
jdbc.password=PASSWORD
jdbc.driver=com.mysql.cj.jdbc.Driver

hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.show_sql=true
```

Execute the highlighted file to database

![EXECUTE THIS FILE TO DATABASE BEFORE RUNNING THE APPLICATION](screenshots/mysqlfile.png "Execute this file to database")


(We used mysql localhost instead of docker!!!!)

## Clone Project into Eclipse


File -> Import -> Git/Projects from Git -> Clone URI

the project url is 
[https://github.com/tsadimas/spring-mvc-demo-2020.git](https://github.com/tsadimas/spring-mvc-demo-2020.git)

when the prompt to select the kind of project appears, select *Import as general project*


![import_project](screenshots/import.png "Import into Eclipse")


Next, you should convert the current project to Dynamic Web project.
To accomplish this, you should right-click the project and in properties go to *Project Facets*
Enable Faceted Project

![enable_facets](screenshots/convert-to-facet.png "Convert to faceted form")

and select _Dynamic Web Module_ from the facets list.

![facets](screenshots/dynamic-facet.png "Convert to Dynamic Web Project")

Set the default JRE runtime to be Java 11. To accomplish this, right click the project and in _Java Build Path_, in Libraries Tab edit the _JRE System Library_ to point to your Java 11 runtime (setting the appropriate value to Alternate JRE).

![facets](screenshots/java-1.png "JRE config 1")

![facets](screenshots/java-2.png "JRE config 2")


Also, don't forget to convert the project to maven project (Configure->Convert to Maven Project)



## Links
* [Maven]: [Setting the -source and -target of the Java Compiler](https://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html)
* [IntelliJ]: [Error:java: release version 5 not supported](https://dev.to/techgirl1908/intellij-error-java-release-version-5-not-supported-376)
* [IntelliJ]: [IntelliJ IDEA cannot resolve import javax.servlet.*;](https://stackoverflow.com/questions/25589152/intellij-idea-cannot-resolve-import-javax-servlet)
* [IntelliJ]: [How to add Resources Folder, Properties at Runtime into IntelliJ classpath? Adding Property files to Classpath](https://crunchify.com/how-to-add-resources-folder-properties-at-runtime-into-intellijs-classpath-adding-property-files-to-classpath/)
* [git]: [What is git tag, How to create tags & How to checkout git remote tag(s)](https://stackoverflow.com/questions/35979642/what-is-git-tag-how-to-create-tags-how-to-checkout-git-remote-tags)
