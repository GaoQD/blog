package com.dao;

import java.util.List;

public interface TagDao {

    int DEFAULT_ID = 0;

    String DEFAULT_TAG = "LEMONREDS2017";

    /**
     * 新的标签
     * @param id
     * @param tag
     * @return
     */
    boolean addTag(int id,String tag);

    /**
     * 删除标签
     * @param id
     * @param tag
     * @return
     */
    boolean deleteTag(int id,String tag);

    /**
     * 获取所有的标签 不含重复
     * @return
     */
    List getAllTag();

    /**
     * 更新标签
     * @param old_tag
     * @param new_tag
     * @return
     */
    boolean updateTag(String old_tag,String new_tag);

    /**
     * 通过列属性获取标签
     * @param column
     * @param value
     * @return
     */
    List getTagByColumn(String column,String value);
}
