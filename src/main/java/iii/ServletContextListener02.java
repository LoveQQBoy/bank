package iii;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;


@WebListener
public class ServletContextListener02 implements ServletContextListener {
   		
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    		try {
				InitialContext ic = new InitialContext();
				Context context = (Context)ic.lookup("java:/comp/env"); 
				DataSource ds = (DataSource)context.lookup("jdbc/bank");
				
				ServletContext sc = sce.getServletContext();
				sc.setAttribute("ds-sqlsrv-servlethw", ds);
				
			} catch (NamingException e) {				
				e.printStackTrace();
			}
    	   	
    }
	
}
