package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CodeFilter
 * @Author THINK
 * @Date 2019/9/19 15:49
 */
@WebFilter(filterName = "codeFilter",urlPatterns = {"/*"})
public class CodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        HttpServletResponse rp=(HttpServletResponse) servletResponse;
        rq.setCharacterEncoding("utf-8");
        rp.setCharacterEncoding("utf-8");
        rp.setContentType("text/html;charset=utf-8");

        rp.setHeader("Cache-Control", "no-cache");
        rp.setHeader("Pragma", "no-cache");
        rp.setDateHeader("expires", -1);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
