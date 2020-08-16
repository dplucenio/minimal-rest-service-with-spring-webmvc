package io.plucen;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
  private static final AnnotationConfigApplicationContext context =
      new AnnotationConfigApplicationContext(AppConfiguration.class);

  public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8081);
    tomcat.getConnector();
    Context tomcatContext = tomcat.addContext("", null);

    MinimalHttpServlet minimalHttpServlet = context.getBean(MinimalHttpServlet.class);
    Wrapper servlet = Tomcat.addServlet(tomcatContext, "minimalServlet", minimalHttpServlet);
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");
    tomcat.start();
  }
}
