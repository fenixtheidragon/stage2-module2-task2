package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

  private final static String helloPage = "/user/hello.jsp";
  private final static String loginPage = "/login.jsp";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
      forwardToLoginPage(request,response);
    } else {
      sendRedirectToHelloPage(response);
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    String user = request.getParameter("login");
    String password = request.getParameter("password");
    if (!user.isEmpty() && Users.getInstance().getUsers().contains(user) && !password.isEmpty()) {
      HttpSession session = request.getSession();
      session.setAttribute("user", user);
      sendRedirectToHelloPage(response);
    } else {
      forwardToLoginPage(request,response);
    }
  }

  private void sendRedirectToHelloPage(HttpServletResponse response) {
    try {
      response.sendRedirect(helloPage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getRequestDispatcher(loginPage).forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }
}
