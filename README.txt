Pre-requisitos:
- Java eclipse (neon)
- Maven
- Tomcat v 9
- en la carpeta lib de tomcat agregar el jar JDBC de Postgresql

1. agregar a pom.xml las dependencias de REST

<dependency>
	<groupId>com.sun.jersey</groupId>
	<artifactId>jersey-core</artifactId>
	<version>1.19</version>
</dependency>
<dependency>
	<groupId>com.sun.jersey</groupId>
	<artifactId>jersey-json</artifactId>
	<version>1.19</version>
</dependency>
<dependency>
	<groupId>com.sun.jersey</groupId>
	<artifactId>jersey-servlet</artifactId>
	<version>1.19</version>
</dependency>

2. Agregar a web-inf, el cual esta dentro de src/web-app agregar servlet para
los servicios Rest.
-------
<servlet>
	<servlet-name>Jersey Web Application</servlet-name>
	<servlet-class>com.sun.jersey.spi.container.servlet.ServletContainer</servlet-class>
	<init-param>
		<param-name>com.sun.jersey.config.property.packages</param-name>
		<param-value>true</param-value>
	</init-param>
	<init-param>
		<param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
		<param-value>true</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>Jersey Web Application</servlet-name>
	<url-pattern>/rest/*</url-pattern>
</servlet-mapping>

3. Realizar la peticion en un browser o en una herramienta como SOAPUI o Postman
----
http://localhost:8080/TestRest/services/JavaArtsoft/validaUsuario
Request Invalido:
{
	"usuario" : "walter",
	"password" : "123456"	
}
Request valido:
{
	"usuario" : "java",
	"password" : "artsoft"	
}

4. crear la tabla en postgresql:
CREATE TABLE public."Version"
(
  "Id" integer,
  "Version" text
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."Version"
  OWNER TO postgres;

  
5. insertar en bd.
http://localhost:8080/TestRest/services/JavaArtsoft/Insertar

6. mostrar la data:
http://localhost:8080/TestRest/services/JavaArtsoft/Mostrar


