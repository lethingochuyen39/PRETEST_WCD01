package com.aptech.wcd01.controllers;

import com.aptech.wcd01.services.EmployeeJDBCService;
import com.aptech.wcd01.services.EmployeeJPAService;
import com.aptech.wcd01.services.EmployeeJPAServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

    @Inject
    EmployeeJDBCService employeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        req.setAttribute("emp", employeeService.getEmployeeById(id));
        req.getServletContext().getRequestDispatcher("/WEB-INF/delete.jsp").forward(req,resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if (employeeService.deleteEmployee(id)) {
            resp.sendRedirect(req.getContextPath() + "/list");
        } else {
            req.setAttribute("error", "Delete error !!!");
            req.getServletContext().getRequestDispatcher("/WEB-INF/failed.jsp").forward(req,resp);
        }
    }
}
