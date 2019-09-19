package com.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName DBUtils
 * @Author THINK
 * @Date 2019/9/18 9:40
 */

public class DBUtils {

    /**
     * 关闭数据库连接
     * @param statement
     * @throws SQLException
     */
    public static void Close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    /**
     * 关闭数据库连接
     * @param statement
     * @param rs
     * @throws SQLException
     */
    public static void Close(Statement statement, ResultSet rs) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    /**
     * 关闭数据库连接
     * @param conn
     * @param statement
     * @param rs
     * @throws SQLException
     */
    public static void Close(Connection conn,Statement statement,ResultSet rs) throws SQLException {
        if (conn != null)
            conn.close();
        if (statement != null)
            statement.close();
        if (rs != null)
            rs.close();
    }

    /**
     * 关闭数据库连接
     * @param conn
     * @param statement
     * @throws SQLException
     */
    public static void Close(Connection conn,Statement statement) throws SQLException {
        if (conn != null)
            conn.close();
        if (statement != null)
            statement.close();
    }
}
