package com.masamiaoi.elasticsearch.vo;

import lombok.Data;

/**
 * @author: MASAMIAOI
 * @description: 用户
 * @date: 2023/3/18 15:13
 * @version: 1.0.0
 */
@Data
public class UserCityVO {
    /**
     * 城市
     */
    private String city;
    /**
     * 用户数
     */
    private Long count;
    /**
     * 平均年龄
     */
    private Double avgAge;
}
