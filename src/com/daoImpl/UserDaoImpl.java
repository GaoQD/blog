package com.daoImpl;

import com.dao.UserDao;
import com.db.C3P0Connection;
import com.model.User;
import com.utils.DBUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserDaoImpl
 * @Author THINK
 * @Date 2019/9/19 11:09
 */

public class UserDaoImpl implements UserDao {

    private Connection conn;

    private static UserDao instance;

    public static final UserDao getInstance() {
        if (instance == null) {
            try {
                instance = new UserDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public boolean register(String username, String password) {
        conn = C3P0Connection.getInstance().getConnection();
        String sql = "insert into t_user(user_name,user_password,POWER) values(?,?,2)";
        PreparedStatement preparedStatement = null;
        boolean rs = false;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            rs = preparedStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public User login(String username, String password) {
        conn = C3P0Connection.getInstance().getConnection();

        User user = null;
        String sql = "select * from t_user where user_name=? and user_password=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            // bean导入
            if (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                user = new User();
                map.put("user_name", rs.getString("user_name"));
                map.put("user_password", rs.getString("user_name"));
                map.put("user_id", rs.getString("user_id"));
                map.put("user_power", rs.getString("power"));
                try {
                    BeanUtils.populate(user, map);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
            DBUtils.Close(conn,preparedStatement, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
