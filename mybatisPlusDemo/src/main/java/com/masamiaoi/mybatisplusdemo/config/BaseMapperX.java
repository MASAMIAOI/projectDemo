package com.masamiaoi.mybatisplusdemo.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * @author: MASAMIAOI
 * @description: baseMapperX
 * @date: 2023/3/14 13:48
 * @version: 1.0.0
 */
public interface BaseMapperX<T> extends BaseMapper<T> {


    /**
     * 批量插入，适合大量数据插入
     *
     * @param entities 实体们
     */
    default void insertBatch(Collection<T> entities) {
        for (T entity : entities) {
            insert(entity);
        }
    }


    default void updateBatch(T update) {
        update(update, new QueryWrapper<>());
    }
}
