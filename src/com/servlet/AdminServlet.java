package com.servlet;

import com.db.VisitorDB;
import com.service.ArticleService;
import com.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AdminServlet
 * @Author THINK
 * @Date 2019/9/19 16:30
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //传所有的文章
        ArticleService as =  ArticleService.getInstance();
        request.setAttribute("articles",as.getArticle());
        //传所有的分类
        request.setAttribute("sort", as.getAllSort());
        //传所有的标签
        TagService ts = TagService.getInstance();
        request.setAttribute("tags", ts.getAllTag());
        //传网站的统计数据
        request.setAttribute("visited", VisitorDB.totalVisit());
        request.setAttribute("member", VisitorDB.totalMember());

        //转发
        request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
