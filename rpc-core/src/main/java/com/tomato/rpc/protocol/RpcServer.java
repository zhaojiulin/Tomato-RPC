package com.tomato.rpc.protocol;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: rpc服务
 * @date 2025/10/22 17:38
 */
public class RpcServer {
    public void start(String hostName,int port) {
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");
        Connector connector = new Connector();
        connector.setPort(port);

        StandardEngine standardEngine = new StandardEngine();
        standardEngine.setDefaultHost(hostName);

        StandardHost standardHost = new StandardHost();
        standardHost.setName(hostName);

        String contextPath = "";
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(contextPath);
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        standardHost.addChild(standardContext);
        standardEngine.addChild(standardHost);

        service.setContainer(standardEngine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath, "dispatcher", new RpeDispatcherServlet());

        standardContext.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }

    }
}
