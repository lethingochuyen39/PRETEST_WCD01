package com.aptech.wcd01.controllers;

import com.aptech.wcd01.models.Employee;
import com.aptech.wcd01.services.EmployeeJDBCService;
import com.aptech.wcd01.services.EmployeeJDBCServiceImpl;
import com.aptech.wcd01.services.EmployeeJPAService;
import com.aptech.wcd01.services.EmployeeJPAServiceImpl;
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

     EmployeeJPAService employeeService = new EmployeeJPAServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("searchString");
        //set cookie
        var cookie = new Cookie("lastSearchStr",search);
        //bảo về cookie khỏi việc truy cập trái phép từ browser
        cookie.setHttpOnly(true);
        //trả về client
        resp.addCookie(cookie);
        // lưu danh sách emp
        req.setAttribute("employeeList", employeeService.searchEmployeeByName(search));
        //chuyển thẳng qua jsp không qua controller nữa
        req.getServletContext().getRequestDispatcher("/WEB-INF/list.jsp").forward(req,resp);
    }
}
