package web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import init.HibernateUtils_init;




@WebListener
public class CreateHibernateSessionFactoryListner implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("系統關閉中");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("------------------------------------------------");
    //	HibernateUtils_init.getSessionFactory();
    	System.out.println("------------------------------------------------");
    }
	
}
