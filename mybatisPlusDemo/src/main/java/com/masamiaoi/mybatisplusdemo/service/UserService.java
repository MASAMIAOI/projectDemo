package com.masamiaoi.mybatisplusdemo.service;

import com.alibaba.fastjson.JSONArray;
import com.masamiaoi.mybatisplusdemo.po.UserPO;
import com.masamiaoi.mybatisplusdemo.type.RestResultVo;

import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: 用户实现
 * @date: 2023/3/11 14:48
 * @version: 1.0.0
 */
public interface UserService {

    /**
     * @param :
     * @return void
     * @author MASAMIAOI
     * @description 查询用户信息
     * @date 2023/3/11 14:34
     */
    RestResultVo<List<UserPO>> queryUser();

    /**
     * @param :
     * @return void
     * @author MASAMIAOI
     * @description 查询用户信息
     * @date 2023/3/11 14:34
     */
    RestResultVo<List<UserPO>> seAll();

    /**
     * @param poList 用户集合
     * @return void
     * @author MASAMIAOI
     * @description 批量添加用户信息
     * @date 2023/3/11 14:34
     */
    RestResultVo<Void> batchAddUser(List<UserPO> poList);


    /**
     * @param poList 用户集合
     * @return void
     * @author MASAMIAOI
     * @description 批量添加用户信息
     * @date 2023/3/11 14:34
     */
    RestResultVo<Void> batchAddUser(JSONArray userPoArray);

    // 重载注意 参数个数 、 参数顺序 、 参数类型 不同。 返回值 和 访问修饰符可以不同
//    RestResultVo<Boolean> batchAddUser(JSONArray userPoArray);
}
