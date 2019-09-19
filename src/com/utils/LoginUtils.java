package com.utils;

import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginUtils
 * @Author THINK
 * @Date 2019/9/19 16:55
 */

public class LoginUtils {

    public static void login(HttpServletRequest request, HttpServletResponse response){

        String username= request.getParameter("username");
        String password= request.getParameter("password");

        if( StringUtils.isEmpty(username)&& StringUtils.isEmpty(password)){
            return;
        }

        UserDao dao = UserDaoImpl.getInstance();
        User user = dao.login(username, password);
        if(user==null)
            return;

        //写入session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

    }
}
