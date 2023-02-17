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

        HttpSession session = request.getSession(false);
        boolean isLoginPage = request.getRequestURI().contains("/login.jsp");
        boolean isServerletLoginURL = request.getRequestURI().contains(request.getContextPath() + "/login");
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // Đã login và đang login lại

        if (isLoggedIn && (isLoginPage || isServerletLoginURL)) {
            request.getRequestDispatcher("/").forward(request, response);
        } else if (!isLoggedIn && !(isServerletLoginURL || isLoginPage)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
