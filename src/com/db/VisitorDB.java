package com.db;

import com.utils.DBUtils;
import com.utils.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @ClassName VisitorDB
 * @Author THINK
 * @Date 2019/9/18 10:10
 */

public class VisitorDB {
    private static Connection conn = C3P0Connection.getInstance().getConnection();

    /**
     * 浏览者信息
     * @param request
     */
    public static void visit(HttpServletRequest request) {
        Connection conn = C3P0Connection.getInstance().getConnection();
        String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
        String localAddr = request.getLocalAddr();		//获取WEB服务器的IP地址
        String remoteHost = request.getRemoteHost();
        String time = DateUtils.getFormatDate(new Date());

        String sql ="insert into t_visitor values(null,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, remoteAddr);
            ps.setString(2, time);
            ps.setString(3, localAddr);
            ps.setString(4, remoteHost);
            ps.executeUpdate();
            DBUtils.Close(conn,ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 全部浏览者
     * @return
     */
    public static int totalVisit() {
        Connection conn = C3P0Connection.getInstance().getConnection();
        int result = 0;
        String sql = "select count(id) from t_visitor";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            DBUtils.Close(conn,preparedStatement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 第几个浏览者
     * @return
     */
    public static int totalMember() {
        Connection conn = C3P0Connection.getInstance().getConnection();
        int result = 0;
        String sql = "select count(distinct(ip)) from t_visitor";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            DBUtils.Close(conn,preparedStatement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
