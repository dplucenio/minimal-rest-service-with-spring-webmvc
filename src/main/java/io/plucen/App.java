package io.plucen;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.flywaydb.core.Flyway;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class App {

  public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8081);
    tomcat.getConnector();
    Context tomcatContext = tomcat.addContext("", null);

    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfiguration.class);
    context.setServletContext(tomcatContext.getServletContext());
    context.refresh();
    final Flyway flyway = context.getBean(Flyway.class);
    flyway.migrate();

    DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    Wrapper servlet = Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");
    tomcat.start();
  }
}
