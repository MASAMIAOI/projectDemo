package com.masamiaoi.redis.rest;

import com.masamiaoi.redis.config.RedisUtil;
import com.masamiaoi.redis.po.UserPO;
import com.masamiaoi.redis.service.UserService;
import com.masamiaoi.redis.type.RestResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: MASAMIAOI
 * @description: 用户rest
 * @date: 2023/3/20 18:00
 * @version: 1.0.0
 */
@RestController
public class UserRest {

    @Autowired
    private UserService userService;

    @Autowired
    RedisUtil cacheService;


    /**
     * 测试-redis
     * 存储 String
     */
    @GetMapping("/test1")
    public RestResultVo<UserPO> test1() {
        cacheService.add("test", "zxczcxvzxcv");
        return RestResultVo.ok();
    }

    /**
     * 测试-redis
     * 存储对象
     */
    @GetMapping("/test2")
    public RestResultVo<UserPO> test2() {
        UserPO user = UserPO.builder()
                .id(123L)
                .name("小萌")
                .password("1231")
                .build();
        cacheService.add(user.getId(), user);
        return RestResultVo.ok();
    }


    /**
     * 测试-redis
     * 存入对象， 设置有效期
     */
    @GetMapping("/test3")
    public RestResultVo<UserPO> test3() {
        UserPO user = UserPO.builder()
                .id(11111L)
                .name("小萌")
                .password("1231")
                .build();
        cacheService.add("11111L", user, 1, TimeUnit.MINUTES);
        return RestResultVo.ok();
    }

    /**
     * 测试-redis
     * 存入对象集合， 设置有效期
     */
    @GetMapping("/test")
    public RestResultVo<UserPO> test() {
        UserPO user = UserPO.builder()
                .id(11111L)
                .name("小萌")
                .password("1231")
                .build();
        cacheService.add("key", Collections.singleton(user), 1, TimeUnit.MINUTES);
        return RestResultVo.ok();
    }

    /**
     * 测试-redis
     * 查询 id
     */
    @GetMapping("/test4")
    public RestResultVo<UserPO> test4() {
        UserPO object = cacheService.getObject("11111L", UserPO.class);
        return RestResultVo.success(object);
    }

    /**
     * 测试-redis
     * 查询集合
     */
    @GetMapping("/test5")
    public RestResultVo<List<UserPO>> test5() {
        List<UserPO> users = cacheService.getList("key", UserPO.class);
        return RestResultVo.success(users);
    }


    /**
     * 测试-redis
     * 添加 hash-set
     */
    @GetMapping("/test6")
    public RestResultVo<Void> test6() {
        cacheService.addHashCache("hashKey", "1", "value123");
        cacheService.addHashCache("hashKey", "99", "value123");
        cacheService.addHashCache("hashKey", "2", "value123");
        cacheService.addHashCache("hashKey", "-1", "value123");
        return RestResultVo.ok();
    }


    /**
     * 测试-redis
     * 获取 hash-set
     */
    @GetMapping("/test7")
    public RestResultVo<String> test7() {
        String value = (String) cacheService.getHashCache("hashKey", "key");
        return RestResultVo.success(value);
    }


    /**
     * 测试-redis
     * 获取 hash-set , 根据 key 排序
     */
    @GetMapping("/test8")
    public RestResultVo<Object> test8() {
        return RestResultVo.success(cacheService.getHashCacheSort("hashKey"));
    }
}
