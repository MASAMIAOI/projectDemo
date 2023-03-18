package com.masamiaoi.elasticsearch.po;

import lombok.Data;

/**
 * @author: MASAMIAOI
 * @description: 用户交互对象
 * @date: 2023/3/18 12:27
 * @version: 1.0.0
 */
@Data
public class UserPO {
    private String id;
    private String name;
    private String sex;
    private Integer age;
    private String city;
}
