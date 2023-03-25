package com.masamiaoi.mq.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: MASAMIAOI
 * @description: 测试vo
 * @date: 2023/3/25 0:02
 * @version: 1.0.0
 */
@Data
public class ScProductVO implements Serializable {

    private String name;
    private Integer number;
    private Integer id;
    private String productImg;
}
