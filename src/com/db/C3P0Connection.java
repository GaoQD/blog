package com.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * C3P0数据库连接池的使用
 * @ClassName C3P0Connection
 * @Author THINK
 * @Date 2019/9/18 10:13
 */

public class C3P0Connection {

    private static C3P0Connection instance;
    private static ComboPooledDataSource dataSource;

    public static final C3P0Connection getInstance() {
        if (instance == null) {
            try {
                instance = new C3P0Connection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public synchronized final Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
