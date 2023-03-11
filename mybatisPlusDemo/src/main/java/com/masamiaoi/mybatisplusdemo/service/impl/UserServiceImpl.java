package com.masamiaoi.mybatisplusdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masamiaoi.mybatisplusdemo.dao.UserMapper;
import com.masamiaoi.mybatisplusdemo.po.UserPO;
import com.masamiaoi.mybatisplusdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: 用户实现类
 * @date: 2023/3/11 14:50
 * @version: 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    /*
     * 用户 mapper
     * */
    @Autowired
    UserMapper userMapper;

    /**
     * @param :
     * @return void
     * @author MASAMIAOI
     * @description 查询用户信息
     * @date 2023/3/11 14:34
     */
    @Override
    public void queryUser() {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Jone");
        List<UserPO> pos = userMapper.selectList(queryWrapper);
        System.out.println(JSONObject.toJSON(pos));
    }
}
