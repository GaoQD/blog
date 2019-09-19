package com.utils;

import com.db.VisitorDB;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ClassName VisitorThreadUtil
 * @Author THINK
 * @Date 2019/9/18 9:55
 */

public class VisitorThreadUtil extends Thread {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public VisitorThreadUtil(HttpServletRequest request,HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    public void run(){
        VisitorDB.visit(request);
        Cookie cookie = new Cookie("myblog_visitor",DateUtils.getFormatDate(new Date()));

        cookie.setMaxAge(60 * 60);
        cookie.setPath("/Blog");
        response.addCookie(cookie);
    }
}
