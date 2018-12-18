package com.dev999.maven.genertation.utils;

/**
 * 字符串处理类
 * @author helecong
 * @date 2018/12/14
 */
public class StringUtils {

    /**
     * 首字母大写
     * @param name
     * @return
     */
    public static String firstNameUpper(String name) {
        char[] cs=name.toCharArray();
        String s = String.valueOf(cs[0]);
        s = s.toUpperCase();
        cs[0]=s.charAt(0);
        return String.valueOf(cs);

    }

    /**
     * 首字母小写
     * @param name
     * @return
     */
    public static String firstNameLower(String name) {
        char[] cs=name.toCharArray();
        String s = String.valueOf(cs[0]);
        s = s.toLowerCase();
        cs[0]=s.charAt(0);
        return String.valueOf(cs);

    }


}
