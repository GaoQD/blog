package com.filter;

import com.service.ArticleService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ArticleFilter
 * @Author THINK
 * @Date 2019/9/19 11:38
 */

public class ArticleFilter implements Filter {

    /**
     * Default constructor.
     */
    public ArticleFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp=(HttpServletResponse) response;

        //点开文章 自动 增加 浏览次数
        String id = rq.getParameter("id");
        ArticleService as =  ArticleService.getInstance();
        as.addVisit(Integer.valueOf(id));

        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
