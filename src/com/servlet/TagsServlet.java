package com.servlet;

import com.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName TagsServlet
 * @Author THINK
 * @Date 2019/9/19 16:52
 */
@WebServlet("/TagsServlet")
public class TagsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getparameter? no dont exist here.
        String get =  request.getParameter("get");
        //初始化标签
        TagService ts = TagService.getInstance();
        request.setAttribute("id_tag_map", ts.getTagAndArticle(get));

        request.getRequestDispatcher("/page/tags.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
