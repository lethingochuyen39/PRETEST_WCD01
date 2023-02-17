package com.aptech.wcd01.controllers;

import com.aptech.wcd01.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         String userName = req.getParameter("username");
         String password = req.getParameter("password");

        User user = new User();
        //cd: @Accessors(chain = true)
        user.setUserName(userName).setPassword(password);

         if(userName.equals("user") && password.equals("huyen")){
             var session = req.getSession(true);
             session.setAttribute("user", user);
             resp.sendRedirect(req.getContextPath() + "/list");
         }else {
             req.getServletContext().getRequestDispatcher("/login.jsp").include(req,resp);
             resp.getWriter().write("Invalid username or password !!!");
         }
    }
}
