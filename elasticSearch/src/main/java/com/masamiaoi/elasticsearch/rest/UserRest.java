package com.masamiaoi.elasticsearch.rest;

import com.masamiaoi.elasticsearch.po.UserPO;
import com.masamiaoi.elasticsearch.service.UserService;
import com.masamiaoi.elasticsearch.type.RestResultCommonCode;
import com.masamiaoi.elasticsearch.type.RestResultVo;
import com.masamiaoi.elasticsearch.vo.UserCityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: es rest
 * @date: 2023/3/18 13:26
 * @version: 1.0.0
 */
@RestController
public class UserRest {

    @Autowired
    UserService userService;

    /**
     * 创建索引
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/createUserIndex")
    public RestResultVo<Boolean> createUserIndex(@RequestParam(value = "index") String index) throws Exception {
        return RestResultVo.success(userService.createUserIndex(index));
    }


    /**
     * 删除索引
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteUserIndex")
    public RestResultVo<Boolean> deleteUserIndex(@RequestParam(value = "index") String index) throws Exception {
        return RestResultVo.success(userService.deleteUserIndex(index));
    }

    /**
     * 新增文档
     *
     * @param document
     * @return
     * @throws Exception
     */
    @PostMapping("/createUserDocument")
    public RestResultVo<Boolean> createUserDocument(@RequestBody UserPO document) throws Exception {
        return RestResultVo.success(userService.createUserDocument(document));
    }

    /**
     * 批量新增文档
     *
     * @param document
     * @return
     * @throws Exception
     */
    @PostMapping("/bulkCreateUserDocument")
    public RestResultVo<Boolean> bulkCreateUserDocument(@RequestBody List<UserPO> document) throws Exception {
        return RestResultVo.success(userService.bulkCreateUserDocument(document));
    }

    /**
     * 删除文档
     *
     * @param id 要删除的主键id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteUserDocument")
    public RestResultVo<String> deleteUserDocument(@RequestParam String id) throws Exception {
        return RestResultVo.success(userService.deleteUserDocument(id));
    }

    /**
     * 更新文档
     *
     * @param document
     * @return
     * @throws Exception
     */
    @PostMapping("/updateUserDocument")
    public RestResultVo<Boolean> updateUserDocument(@RequestBody UserPO document) throws Exception {
        Boolean aBoolean = userService.updateUserDocument(document);
        if (!aBoolean) {
            return RestResultVo.error(RestResultCommonCode.FAIL);
        }
        return RestResultVo.success(userService.updateUserDocument(document));
    }

    /**
     * 获取文档
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/getUserDocument")
    public RestResultVo<UserPO> getUserDocument(@RequestParam String id) throws Exception {
        UserPO userPO = userService.getUserDocument(id);
        return RestResultVo.success(userPO);
    }

    /**
     * 用户列表
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getUserList")
    public RestResultVo<List<UserPO>> getUserList() throws Exception {
        List<UserPO> list = userService.getUserList();
        return RestResultVo.success(list, list.size());
    }

    /**
     * 城市聚合
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/aggregationsSearchUser")
    public RestResultVo<List<UserCityVO>> aggregationsSearchUser() throws Exception {
        return RestResultVo.success(userService.aggregationsSearchUser());
    }

    /**
     * 根据姓名搜索用户
     *
     * @param city 城市
     * @return
     * @throws Exception
     */
    @GetMapping("/searchUserByCity")
    public RestResultVo<List<UserPO>> searchUserByCity(@RequestParam(value = "city") String city) throws Exception {
        return RestResultVo.success(userService.searchUserByCity(city));
    }

}
