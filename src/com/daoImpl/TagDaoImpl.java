package com.daoImpl;

import com.dao.TagDao;
import com.db.C3P0Connection;
import com.model.Tag;
import com.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TagDaoImpl
 * @Author THINK
 * @Date 2019/9/19 11:36
 */

public class TagDaoImpl implements TagDao {

    private Connection conn;

    private static TagDao instance;



    public static final TagDao getInstance() {
        if (instance == null) {
            try {
                instance = new TagDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /* (non-Javadoc)
     * @see blog.daoImpl.TagDao#addTag(int, java.lang.String)
     */
    @Override
    public boolean addTag(int id, String tag) {
        conn = C3P0Connection.getInstance().getConnection();

        String sql = "insert into t_tag values(?,?)";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, tag);
            result = ps.executeUpdate();
            DBUtils.Close(conn,ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result != 0;
    }

    /* (non-Javadoc)
     * @see blog.daoImpl.TagDao#deleteTag(int, java.lang.String)
     */
    @Override
    public boolean deleteTag(int id, String tag) {
        conn = C3P0Connection.getInstance().getConnection();

        String sql = "delete from t_tag where id=? or tag=?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, tag);
            result = ps.executeUpdate();
            DBUtils.Close(conn,ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result != 0;
    }

    /* (non-Javadoc)
     * @see blog.daoImpl.TagDao#getAllTag()
     */
    @Override
    public List getAllTag() {
        conn = C3P0Connection.getInstance().getConnection();

        String sql = "SELECT  distinct(tag)  FROM t_tag";
        List<Tag> list = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList();
            Tag tag;
            while (rs.next()) {
                tag = new Tag();
                tag.setTag(rs.getString(1));
                // 缺省标签对象的id
                tag.setId(0);
                list.add(tag);
            }
            DBUtils.Close(conn,ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* (non-Javadoc)
     * @see blog.daoImpl.TagDao#updateTag(java.lang.String, java.lang.String)
     */
    @Override
    public boolean updateTag(String old_tag, String new_tag) {
        conn = C3P0Connection.getInstance().getConnection();

        String sql = "update t_tag set tag=? where tag=?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, new_tag);
            ps.setString(2, old_tag);
            result = ps.executeUpdate();
            DBUtils.Close(conn,ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result != 0;
    }

    /* (non-Javadoc)
     * @see blog.daoImpl.TagDao#getTagByColumn(java.lang.String, java.lang.String)
     */
    @Override
    public List getTagByColumn(String column, String value) {
        conn = C3P0Connection.getInstance().getConnection();

        String sql = "select * from t_tag where " + column + "=?";
        List list = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList();
            Tag tag;
            while (rs.next()) {
                tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setTag(rs.getString("tag"));
                list.add(tag);
            }
            DBUtils.Close(conn,ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
