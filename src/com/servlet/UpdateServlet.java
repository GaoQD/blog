package com.servlet;

import com.model.Article;
import com.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName UpdateServlet
 * @Author THINK
 * @Date 2019/9/19 16:52
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminService as = AdminService.getInstance();
        Article result = as.updateArticle(request);
        request.setAttribute("article", result);

        request.getRequestDispatcher("/admin/result.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
