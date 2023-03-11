package com.masamiaoi.mybatisplusdemo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author MASAMIAOI
 */
@Data
@TableName(value = "user")
public class UserPO {
    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;

}
