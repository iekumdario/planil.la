# MIS - Calidad de software #



### Descripción ###

* WIP (Arles  / Carlos M.)

### Prerequisito ###
WIP


### Configuración Incial ###

WIP
- Crear credencias de git en TFS
- Clonar repositorio
- Mysql
- Verificar que JAVA esta instalado
- Abrir proyecto con IntellJ
- Correr


### Herramientas de desarrollo ###

* SVC: Git - 2.7.4
* IDE: IntelliJ IDEA 2017.1.5
* Lenguaje de Programación: Java v1.8_44
* Build Tool: Gradle v3.3
* Web Framework: spring-boot-starter-thymeleaf v1.5.7.RELEASE
* Template Engine: Thymeleaf v3.0.7
* ORM: Hibernate v5.1.0 Final
* DBCP: Tomcat-dbcp v8.0.32
* Frontend Framework: Bootstrap v4 | [Material Design for Bootstrap v4.4.1](https://mdbootstrap.com/material-design-for-bootstrap/) Pedir a Carlos M. que Revise

### Base de datos ###
* [MySql](https://dev.mysql.com/doc/refman/5.7/en/installing.html)
	* (Opcional) Instalacion con docker (seguir esta [guia](https://dev.mysql.com/doc/mysql-installation-excerpt/5.5/en/docker-mysql-getting-started.html))
		* Nota-1: exponer puertos al ejecutar docker run: 
		 `sudo docker run --name=mysql1 -p 3306:3306 -d mysql/mysql-server`
		* Nota-2: luego de configurar el password de root (dentro del container), configurar accesso remoto con el wildcard % 
		`GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;`
	* (Opcional) Mysql [workbench](https://dev.mysql.com/doc/workbench/en/wb-installing.html) (GUI de administracion)

### Equipo ###

WIP

### git [gitflow](https://danielkummer.github.io/git-flow-cheatsheet/) ###
* master : branch en produción
* develop: branch en desarrollo
* release*: branch para versiones de prueba
* feature: branch para desarrollo de funciones
