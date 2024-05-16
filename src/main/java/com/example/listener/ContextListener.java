package com.example.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class ContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    LocalDateTime localDateTime = LocalDateTime.now();
    event.getServletContext().setAttribute("servletTimeInit", localDateTime);
  }
}
