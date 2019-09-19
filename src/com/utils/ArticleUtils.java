package com.utils;

import com.model.Article;
import com.model.AxisArticle;

import java.util.List;

/**
 * 文章处理工具
 * @ClassName ArticleUtils
 * @Author THINK
 * @Date 2019/9/18 9:47
 */

public class ArticleUtils {

    /**
     * 处理时间
     * @param list
     * @return
     */
    public static List cutTime(List<Article> list) {
        for (Article a : list) {
            a.setTime(a.getTime().substring(0,11));
        }
        return list;
    }

    /**
     * 处理时间
     * @param a
     * @return
     */
    public static Article cutTime(Article a) {
        a.setTime(a.getTime().substring(0,11));
        return a;
    }

    /**
     * 处理文章内容
     * @param list
     * @return
     */
    public static List cutContent(List<Article> list) {
        for (Article a : list) {
            if (a.getContent() != null && a.getContent().length() > 100) {
                a.setContent(a.getContent().substring(0,99) + "...");
            }
        }
        return list;
    }


    public static AxisArticle getAxisArticle(Article article) {

        AxisArticle axisArticle = new AxisArticle();

        axisArticle.setTitle(article.getTitle());
        axisArticle.setId(article.getId());

        //2017-09-20 21:27:14
        String year=StringUtils.cutString(article.getTime(),0,4);
        String month=StringUtils.cutString(article.getTime(),5,7);
        String day=StringUtils.cutString(article.getTime(),8,10);



        axisArticle.setYear(Integer.valueOf(year)) ;
        axisArticle.setMonth(Integer.valueOf(month)) ;
        axisArticle.setDay(Integer.valueOf(day)) ;


        return axisArticle;
    }
}
