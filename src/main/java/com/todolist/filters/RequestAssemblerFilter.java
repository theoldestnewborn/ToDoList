package com.todolist.filters;

import com.todolist.dto.assembler.DtoAssembler;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/*")
public class RequestAssemblerFilter implements Filter {
    private DtoAssembler dtoAssembler;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        dtoAssembler = new DtoAssembler();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        dtoAssembler.assemble((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        dtoAssembler = null;
    }
}
