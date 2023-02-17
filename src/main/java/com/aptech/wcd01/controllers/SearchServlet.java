package com.aptech.wcd01.controllers;

import com.aptech.wcd01.models.Employee;
import com.aptech.wcd01.services.EmployeeJDBCService;
import com.aptech.wcd01.services.EmployeeJDBCServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/search")
public class SearchServlet  extends HttpServlet {

    private final EmployeeJDBCService employeeJDBCService = new EmployeeJDBCServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("searchString");
        var cookie = new Cookie("lastSearchStr",search);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
        req.setAttribute("employeeList", employeeJDBCService.searchEmployeeByName(search));
        req.getServletContext().getRequestDispatcher("/WEB-INF/list.jsp").forward(req,resp);
    }
}
