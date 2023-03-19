package com.masamiaoi.interfacedoc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: MASAMIAOI
 * @description: 用户
 * @date: 2023/3/19 12:50
 * @version: 1.0.0
 */
@Data
@ApiModel(value = "用户", description = "用户实体类")
public class UserVO {


    @ApiModelProperty(value = "用户 id")
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;
}
