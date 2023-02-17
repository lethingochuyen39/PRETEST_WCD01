package com.aptech.wcd01.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //false: lấy được session , có ss thì khác null , ko có thì null | true : tu tao ra ss
        HttpSession session = request.getSession(false);
        //kiểm tra xem có login hay ko
        boolean isLoginPage = request.getRequestURI().contains("/login.jsp");
        //kiểm tra xem có truy cập servlet của mình hay ko
        boolean isServerletLoginURL = request.getRequestURI().contains(request.getContextPath() + "/login") ||
                request.getRequestURI().endsWith(request.getContextPath() + "/");
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // Đã login và đang login lại
        if (isLoggedIn && (isLoginPage || isServerletLoginURL)) {
            request.getRequestDispatcher("/list").forward(servletRequest, servletResponse);

        }//chưa login, hoặc cố tình vào trang nào đó
        else if (!isLoggedIn && !(isServerletLoginURL || isLoginPage)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
