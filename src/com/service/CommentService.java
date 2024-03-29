package com.service;

import com.dao.CommentDao;
import com.daoImpl.CommentDaoImpl;
import com.model.Comment;

import java.util.List;

/**
 * @ClassName CommentService
 * @Author THINK
 * @Date 2019/9/19 15:43
 */

public class CommentService {

    private CommentDao dao;

    private static CommentService instance;
    private CommentService(){
        dao =  CommentDaoImpl.getInstance();
    }

    public static final CommentService getInstance(){
        if (instance == null) {
            try {
                instance = new CommentService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }



    public List loadComment(int article_id){
        return dao.getComment(article_id);
    }

    public boolean addComment(Comment comment){
        return dao.addComment(comment);
    }

    public int star_diss(int id,int  star_or_diss){
        return dao.star_diss(id, star_or_diss);
    }

    public boolean deleteComment(int id){
        return dao.deleteComment(id);
    }
}
