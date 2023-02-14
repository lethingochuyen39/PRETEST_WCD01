package com.aptech.wcd01.controllers;

import com.aptech.wcd01.models.Employee;
import com.aptech.wcd01.models.EmployeeList;
import com.aptech.wcd01.services.EmployeeJPAService;
import com.aptech.wcd01.services.EmployeeJPAServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/insert")
public class InsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/insert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            EmployeeJPAServiceImpl employeeJPAService = new EmployeeJPAServiceImpl(); /.or
            EmployeeJPAService employeeJPAService = new EmployeeJPAServiceImpl();
            Employee employee = new Employee();
            employee.setId(req.getParameter("id"));
            employee.setName(req.getParameter("name"));
            employee.setAddress(req.getParameter("address"));
            employee.setAge(Integer.parseInt(req.getParameter("age")));


            if (!employeeJPAService.addEmployee(employee)) {

                req.setAttribute("error", "Employee is exist");
                req.getServletContext()
                        .getRequestDispatcher("/WEB-INF/failed.jsp").forward(req, resp);
            } else {
                resp.sendRedirect( req.getContextPath() + "/list");
            }

        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            req.getServletContext()
                    .getRequestDispatcher("/WEB-INF/failed.jsp").forward(req, resp);
            ex.printStackTrace();
        }
    }
}
