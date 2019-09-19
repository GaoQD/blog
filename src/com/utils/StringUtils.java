package com.utils;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName StringUtils
 * @Author THINK
 * @Date 2019/9/18 10:25
 */

public class StringUtils {

    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){

        if(str==null || str.trim().equals("") ) return true;
        return false;
    }


    /**
     * 截取
     * @param str
     * @param begin
     * @param end
     * @return
     */
    public static String cutString(String str,int begin ,int  end){

        if(str.length() < end  || str.length()< begin) return str;

        return str.substring(begin,end);
    }


    /**
     * 解码 解决在URL传中文值出现的乱码问题
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String pareCode(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("ISO-8859-1"),"UTF-8");
    }
}
