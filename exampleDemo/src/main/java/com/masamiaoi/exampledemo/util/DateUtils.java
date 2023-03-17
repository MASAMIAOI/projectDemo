package com.masamiaoi.exampledemo.util;


import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author shenruiyan
 * @version 1.0
 * @Description: 时间工具类
 * @date 2022/10/24 - 19:13
 */
@Slf4j
public class DateUtils {

    public static DateTimeFormatter Y_M_D_h_m_s = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter Y_M_D__h_m_s = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");


    /**
     * localDateTime -> string
     */
    public static String locateDateTimeToString(LocalDateTime triggerTime) {
        String timeNow = "";
        try {
            timeNow = triggerTime.format(Y_M_D_h_m_s);
        } catch (Exception ex) {
            log.error("LocateDateTimeToString -> ex", ex);
        }
        return timeNow;
    }

    /**
     * localDateTime -> string
     */
    public static String locateDateTimeToString(LocalDateTime triggerTime, DateTimeFormatter df) {
        String timeNow = "";
        try {
            timeNow = triggerTime.format(df);
        } catch (Exception ex) {
            log.error("LocateDateTimeToString -> ex", ex);
        }
        return timeNow;
    }
}
