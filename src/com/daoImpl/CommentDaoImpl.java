package com.daoImpl;

import com.dao.CommentDao;
import com.db.C3P0Connection;
import com.model.Comment;
import com.utils.DBUtils;
import sun.dc.pr.PRError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommentDaoImpl
 * @Author THINK
 * @Date 2019/9/18 17:15
 */

public class CommentDaoImpl implements CommentDao {

    private Connection conn;

    private static CommentDao instance;

    public static CommentDao getInstance() {
        if (instance == null) {
            try {
                instance = new CommentDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    @Override
    public boolean deleteComment(int comment_id) {
        conn = C3P0Connection.getInstance().getConnection();
        String sql = "delete from t_commont where id=" + comment_id;
        int result = 0;
        try {
            article_sub_comemnt(conn,comment_id);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            result = preparedStatement.executeUpdate();
            DBUtils.Close(conn,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    /**
     * 文章的评论 - 1
     * @param conn
     * @param comment_id
     */
    public void article_sub_comemnt(Connection conn,int comment_id){
        conn = C3P0Connection.getInstance().getConnection();
        String sql = "select article_id from t_comment where id = " + comment_id;
        try {
            PreparedStatement preparedStatement =  conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            int article_id = 0;
            if (rs.next()) {
                article_id = rs.getInt("article_id");
            }
            sql = "update t_article set comment = comment - 1 where id = " + article_id;
            conn.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean addComment(Comment comment) {
        conn = C3P0Connection.getInstance().getConnection();

        String sql = "insert into t_comment values(null,?,?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, comment.getArticle_id());
            preparedStatement.setString(2, comment.getNickname());
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setString(4, comment.getTime());
            preparedStatement.setInt(5, comment.getStar());
            preparedStatement.setInt(6, comment.getDiss());
            result = preparedStatement.executeUpdate();

            sql = "update t_article set comment = comment + 1 where id = " + comment.getArticle_id();
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql);
            preparedStatement1.executeUpdate();

            DBUtils.Close(conn,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public List getComment(int article_id) {
        conn = C3P0Connection.getInstance().getConnection();
        String sql = "select * from t_comment where article_id = ? order by time";
        List list = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,article_id);
            ResultSet rs = preparedStatement.executeQuery();
            Comment comment;
            list = new ArrayList();
            while (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setArticle_id(rs.getInt("article_id"));
                if (rs.getString("nickname") == null) {
                    comment.setNickname("游客");
                } else {
                    comment.setNickname(rs.getString("nickname"));
                }
                comment.setTime(rs.getString("time"));
                comment.setStar(rs.getInt("star"));
                comment.setContent(rs.getString("content"));
                comment.setDiss(rs.getInt("diss"));
                list.add(comment);
            }
            DBUtils.Close(conn,preparedStatement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int star_diss(int id, int star_or_diss) {
        conn = C3P0Connection.getInstance().getConnection();

        String sql;

        int result = -1;

        if (star_or_diss == Comment.STAR) {
            sql = "update t_comment set star=star+1 where id=" + id;
        } else if (star_or_diss == Comment.DISS) {
            sql = "update t_comment set diss=diss+1 where id=" + id;
        } else {
            return -1;
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            // DBUtils.Close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (star_or_diss == Comment.STAR) {
            sql = "SELECT star FROM t_comment WHERE id = " + id;
        } else if (star_or_diss == Comment.DISS) {
            sql = "SELECT diss FROM t_comment WHERE id = " + id;
        }

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            DBUtils.Close(conn, preparedStatement, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
