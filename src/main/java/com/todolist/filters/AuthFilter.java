package com.todolist.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session.isNew()) {
            Cookie isAuth = new Cookie("isAuth", "false");
            HttpServletResponse response1 = (HttpServletResponse) response;
            response1.addCookie(isAuth);
            request.getRequestDispatcher("/start.jsp").forward(request, response);
        }

        if(!isAuth((HttpServletRequest) request))) {
            request.getRequestDispatcher("/start.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    private boolean isAuth(HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        boolean isAuth = false;
        for (Cookie cookie1 : cookie) {
            if (cookie1.getName().equals("isAuth")) {
                isAuth = Boolean.parseBoolean(cookie1.getValue());
            }
        }
        HttpSession session = request.getSession();
        return isAuth;
    }




//    private boolean isAuth(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        String path = request.getServletPath();
//        Boolean isAuth = (Boolean) session.getAttribute("isAuth");
//        return  Boolean.TRUE.equals(isAuth);
//    }

}
