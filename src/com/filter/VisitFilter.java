package com.filter;

import com.utils.VisitorThreadUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 仅统计首页jsp的访问量
 * @ClassName VisitFilter
 * @Author THINK
 * @Date 2019/9/19 15:46
 */
@WebFilter(filterName = "VisitFilter",urlPatterns = {"*.jsp"})
public class VisitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        HttpServletResponse rp=(HttpServletResponse) servletResponse;


        if(rq.getRequestURL().indexOf("index.jsp") != -1){


            synchronized (this) {
                //System.out.println(rq.getRequestURI());
                Cookie[] cookies = rq.getCookies();
                boolean visited = false;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("myblog_visitor")) {
                            visited = true;
                            break;
                        }
                    }
                }
                if (! visited) {
                    Thread t = new VisitorThreadUtil(rq, rp);
                    t.start();
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
