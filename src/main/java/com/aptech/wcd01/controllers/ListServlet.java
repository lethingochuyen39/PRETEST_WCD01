package com.aptech.wcd01.controllers;

import com.aptech.wcd01.services.EmployeeJDBCService;
import com.aptech.wcd01.services.EmployeeJDBCServiceImpl;
import com.aptech.wcd01.services.EmployeeJPAService;
import com.aptech.wcd01.services.EmployeeJPAServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/list")
public class ListServlet extends HttpServlet {

    @Inject
    EmployeeJDBCService employeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //lấy cookie ra
        var cookie = req.getCookies();
        String lastSearchStr = "";
        for(Cookie ck : cookie)
        {
            if(ck.getName().equals("lastSearchStr")){
                lastSearchStr = ck.getValue();
                break;
            }
        }

        employeeService = new EmployeeJDBCServiceImpl();
        req.setAttribute("employeeList", employeeService.getAllEmployee());
        req.setAttribute("lastSearchStr",lastSearchStr);
        req.getServletContext().getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);

    }
}
