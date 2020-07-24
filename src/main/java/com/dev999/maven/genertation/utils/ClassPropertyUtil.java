package com.dev999.maven.genertation.utils;

import java.util.Date;
import java.util.Map;

/**
 * 类属性工具
 *
 * @author helecong
 * @version V1.0.0 Created in 2020/7/24
 */
public class ClassPropertyUtil {

    public static String  getCalssDoc(String mes){
        StringBuilder sb = new StringBuilder();
        sb.append("/**");
        newLine(sb);
        blank(sb).append("* ").append(mes);

        // 添加作者和时间
        addAuthAndTime(sb);
        newLine(sb);
        blank(sb).append("*/");
        return sb.toString();
    }

    /**
     * 添加作者和时间
     */
    public static StringBuilder addAuthAndTime(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        newLine(sb);
        blank(sb).append("* @author " + getUser());
        newLine(sb);
        blank(sb).append("* @version V1.0.0 Created in " + DateUtils.getFormatDate(new Date(), "yyyy/MM/dd HH:mm:ss"));

        return sb;
    }

    protected static String getUser() {
        String author = "";
        Map<String, String> getenv = System.getenv();
        author = getenv.get("USER");
        if ("".equals(author) || author == null) {
            author = getenv.get("LOGNAME");
        }
        return author;
    }

    public static StringBuilder newLine(StringBuilder sb) {
        sb.append("\r\n");
        return sb;
    }

    /**
     * 空格
     */
    public static StringBuilder blank(StringBuilder sb) {
        sb.append(" ");
        return sb;
    }
}
