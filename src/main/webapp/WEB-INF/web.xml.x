<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	id="WebApp_ID" version="4.0">
	<display-name>Web Application from Archetype for Servlet 4.0</display-name>
	<welcome-file-list>
		<welcome-file>index.jsp</welcome-file>
	</welcome-file-list>

	<request-character-encoding>UTF-8</request-character-encoding>
	<response-character-encoding>UTF-8</response-character-encoding>

	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<!-- Java-based config -->
	<context-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
	</context-param>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>core.config.SpringConfig</param-value>
	</context-param>

	<!-- hibernate filter -->
	<filter>
		<filter-name>HibernateFilter</filter-name>
		<filter-class>org.springframework.orm.hibernate5.support.OpenSessionInViewFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>HibernateFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<!-- Spring MVC config start -->
	<servlet>
		<servlet-name>DispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

		<init-param>
			<param-name>contextClass</param-name>
			<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
		</init-param>

		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>core.config.SpringMvcConfig</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
		<multipart-config />
	</servlet>
	<servlet-mapping>
		<servlet-name>DispatcherServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

</web-app>
