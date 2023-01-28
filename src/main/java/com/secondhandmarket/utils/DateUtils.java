package com.secondhandmarket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 获取系统时刻
     * @return
     */
    public static String nowTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
