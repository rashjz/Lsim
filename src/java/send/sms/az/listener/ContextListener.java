/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.listener;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.LsimDataSource;

public class ContextListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    public void contextInitialized(ServletContextEvent e) {
        ServletContext ctx = e.getServletContext(); 
        //configure log4j
        configureLog4j(ctx.getRealPath("/"));
        //configure datasource
        DataBaseHelper.setLsimDataSource(new LsimDataSource("LsimJND"));
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    public void configureLog4j(String prefix) {
        String file = "WEB-INF" + System.getProperty("file.separator") + "log4j.properties";  
        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
            System.out.println("Log4J Logging started for application: " + prefix + file);
        } else {
            System.out.println("Log4J Is not configured for application GPPWEB: " + prefix + file);
        }
    }
}
