package com.servlet;

import com.model.Tag;
import com.service.ArticleService;
import com.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AddServlet
 * @Author THINK
 * @Date 2019/9/19 16:31
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        request.setAttribute("time",format.format(date));

        ArticleService articleService = ArticleService.getInstance();
        Map sort_count = articleService.getSortAndCount();
        request.setAttribute("sort_count",sort_count);

        TagService tagService = TagService.getInstance();
        List all_tag = tagService.getAllTag();
        request.setAttribute("all_tag",all_tag);

        request.getRequestDispatcher("/admin/add.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
