package com.masamiaoi.email.vo;

import lombok.Data;

/**
 * @author: MASAMIAOI
 * @description: 用户vo
 * @date: 2023/3/17 15:09
 * @version: 1.0.0
 */
@Data
public class UserVO {

    /**
     * 名字
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 电话
     */
    private String phone;


    public UserVO() {
    }

    public UserVO(String name, String gender, String phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }
}
