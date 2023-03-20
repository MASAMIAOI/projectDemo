package com.masamiaoi.redis.po;

import lombok.Builder;
import lombok.Data;

/**
 * @author: MASAMIAOI
 * @description: 用户
 * @date: 2023/3/20 17:56
 * @version: 1.0.0
 */
@Data
@Builder
public class UserPO {

    private Long id;

    private String name;

    private String password;
}
