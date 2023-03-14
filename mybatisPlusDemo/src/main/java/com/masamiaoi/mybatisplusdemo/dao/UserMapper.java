package com.masamiaoi.mybatisplusdemo.dao;

import com.masamiaoi.mybatisplusdemo.config.BaseMapperX;
import com.masamiaoi.mybatisplusdemo.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: 用户 mapper
 * @date: 2023/3/11 14:57
 * @version: 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapperX<UserPO> {


    /*
     * 查询全部
     * */
    public List<UserPO> seAll();
}
