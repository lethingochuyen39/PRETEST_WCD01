package com.aptech.wcd01;

import com.aptech.wcd01.models.Employee;
import com.aptech.wcd01.models.EmployeeList;
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
            EmployeeList employeeList = new EmployeeList();

            Employee employee = new Employee();
            employee.setId((req.getParameter("id")));
            employee.setName((req.getParameter("name")));
            employee.setAddress((req.getParameter("address")));
            employee.setAge((Integer.valueOf(req.getParameter("age"))));

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

            if (constraintViolations.isEmpty()) {
                //luu dl cua ds emp
                req.setAttribute("employeeList", employeeList.getEmployeeList());
                if (!employeeList.insertEmp(employee)) {
                    req.setAttribute("errors", "Employee is exist!");
                    req.getServletContext().getRequestDispatcher("/WEB-INF/failed.jsp").forward(req, resp);
                }
                //chuyen trang neu dung
                req.getServletContext().getRequestDispatcher("/WEB-INF/success.jsp").forward(req, resp);
            } else {

                String errors = "<ul>";
                for (ConstraintViolation<Employee> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage() + "</li>";
                }
                errors += "</ul>";

                //luu dl cua ds emp
                req.setAttribute("employeeList", employeeList.getEmployeeList());
                req.setAttribute("errors", errors);
                //validation
                req.getServletContext().getRequestDispatcher("/WEB-INF/insert.jsp").forward(req, resp);
            }

        } catch (Exception ex) {
            req.setAttribute("errors", ex.getMessage());
            req.getServletContext().getRequestDispatcher("/WEB-INF/insert.jsp").forward(req, resp);
            ex.printStackTrace();
        }
    }
}
