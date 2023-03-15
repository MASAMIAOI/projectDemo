package com.masamiaoi.mybatisplusdemo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.masamiaoi.mybatisplusdemo.dao.UserMapper;
import com.masamiaoi.mybatisplusdemo.po.UserPO;
import com.masamiaoi.mybatisplusdemo.service.UserService;
import com.masamiaoi.mybatisplusdemo.type.RestResultVo;
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
    public RestResultVo<List<UserPO>> queryUser() {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", "Jone");
        List<UserPO> pos = userMapper.selectList(queryWrapper);
        return RestResultVo.success(pos);
    }

    /**
     * @param :
     * @return void
     * @author MASAMIAOI
     * @description 查询用户信息
     * @date 2023/3/11 14:34
     */
    @Override
    public RestResultVo<List<UserPO>> seAll() {
        return RestResultVo.success(userMapper.seAll());
    }

    /**
     * @param poList 用户集合
     * @return void
     * @author MASAMIAOI
     * @description 批量添加用户信息
     * @date 2023/3/11 14:34
     */
    @Override
    public RestResultVo<Void> batchAddUser(List<UserPO> poList) {
        userMapper.insertBatch(poList);
        return RestResultVo.ok();
    }

    /**
     * @param userPoArray 用户集合array数组
     * @return void
     * @author MASAMIAOI
     * @description 批量添加用户信息
     * @date 2023/3/11 14:34
     */
    @Override
    public RestResultVo<Void> batchAddUser(JSONArray userPoArray) {
        List<UserPO> userPOS = JSONObject.parseArray(userPoArray.toJSONString(), UserPO.class);
        userMapper.insertBatch(userPOS);
        return RestResultVo.ok();
    }
}
