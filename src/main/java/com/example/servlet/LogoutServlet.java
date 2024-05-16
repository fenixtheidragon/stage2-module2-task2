package com.example.servlet;

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
      session.setAttribute("user", null);
      session.invalidate();
      try {
        response.sendRedirect(loginPage);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
}
