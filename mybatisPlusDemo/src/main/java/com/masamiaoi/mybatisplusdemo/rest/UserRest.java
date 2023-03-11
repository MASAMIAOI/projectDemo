package com.masamiaoi.mybatisplusdemo.rest;

import com.masamiaoi.mybatisplusdemo.po.UserPO;
import com.masamiaoi.mybatisplusdemo.service.UserService;
import com.masamiaoi.mybatisplusdemo.type.RestResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: 用户 vo
 * @date: 2023/3/11 14:29
 * @version: 1.0.0
 */
@RequestMapping(value = "/user")
@RestController
public class UserRest {

    /*
     * 用户实现
     * */
    @Autowired
    UserService userService;

    /**
     * @param :
     * @return void
     * @author MASAMIAOI
     * @description 查询用户信息
     * @date 2023/3/11 14:34
     */
    @RequestMapping(method = RequestMethod.POST, value = "queryUser")
    public RestResultVo<List<UserPO>> queryUser() {
        return userService.queryUser();
    }

}
