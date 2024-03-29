package com.servlet;

import com.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AxisServlet
 * @Author THINK
 * @Date 2019/9/19 16:44
 */
@WebServlet("/AxisServlet")
public class AxisServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取时间轴文章类型的数据
        ArticleService as=  ArticleService.getInstance();
        request.setAttribute("axis_list",as.getAxisList());

        //转发
        request.getRequestDispatcher("/page/axis.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
