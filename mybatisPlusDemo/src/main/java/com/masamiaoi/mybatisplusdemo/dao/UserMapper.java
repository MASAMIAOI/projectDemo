package com.masamiaoi.mybatisplusdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.masamiaoi.mybatisplusdemo.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: MASAMIAOI
 * @description: 用户 mapper
 * @date: 2023/3/11 14:57
 * @version: 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

}
