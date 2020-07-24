package com.dev999.maven.genertation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 *
 * @author helecong
 * @version V1.0.0 Created in 2020/7/24
 */
public class DateUtils {
    public static String getFormatDate(Date date,String format) {
        String str = "";
        if (date == null) {
            return str;
        } else {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.format(date);
        }
    }
}
