package com.masamiaoi.email.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author: MASAMIAOI
 * @description: 时间工具类
 * @date: 2023/3/17 15:45
 * @version: 1.0.0
 */
public class TimeUtils {


    /**
     * LocalDateTime 转 date
     */
    public static Date localDateTimeToDate(LocalDateTime dateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = dateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }
}
