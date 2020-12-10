package com.shirc.redis.delay.queue.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description
 * @Author shirenchuang
 * @Date 2019/8/2 5:01 PM
 **/
public class ExceptionUtil {


    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
