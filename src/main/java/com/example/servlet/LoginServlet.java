package com.example.servlet;

import com.example.Users;

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
    HttpSession session = request.getSession();
    String result;
    if (session.getAttribute("user") == null) {
      result = loginPage;
    } else {
      result = helloPage;
    }
    sendRedirect(response, result);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    String user = request.getParameter("login");
    String password = request.getParameter("password");
    String result;
    if (Users.getInstance().getUsers().contains(user) && !password.isEmpty()) {
      HttpSession session = request.getSession();
      session.setAttribute("user", user);
      result = helloPage;
    } else {
      result = loginPage;
    }
    sendRedirect(response, result);
  }

  private void sendRedirect(HttpServletResponse response, String result) {
    try {
      response.sendRedirect(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
