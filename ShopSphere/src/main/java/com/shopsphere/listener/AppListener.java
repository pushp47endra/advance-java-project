package com.shopsphere.listener;
import jakarta.servlet.annotation.WebListener;import jakarta.servlet.ServletContextEvent;import jakarta.servlet.ServletContextListener;import jakarta.servlet.http.HttpSessionEvent;import jakarta.servlet.http.HttpSessionListener;import java.util.concurrent.atomic.AtomicInteger;
@WebListener public class AppListener implements ServletContextListener,HttpSessionListener {
 private static final AtomicInteger active=new AtomicInteger();public void contextInitialized(ServletContextEvent e){e.getServletContext().setAttribute("appName","ShopSphere");e.getServletContext().setAttribute("activeSessions",active);}
 public void sessionCreated(HttpSessionEvent e){active.incrementAndGet();}public void sessionDestroyed(HttpSessionEvent e){active.decrementAndGet();}
}
