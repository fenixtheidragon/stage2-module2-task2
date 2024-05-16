package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
  private final static String loginPage = "/login.jsp";
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
      HttpSession session = request.getSession();
      session.removeAttribute("user");
      session.invalidate();
      try {
        request.getRequestDispatcher(loginPage).forward(request,response);
      } catch (ServletException|IOException e) {
        e.printStackTrace();
      }
    }
}
