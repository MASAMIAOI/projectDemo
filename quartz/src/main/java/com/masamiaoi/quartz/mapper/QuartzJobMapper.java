package com.masamiaoi.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.masamiaoi.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 定时任务在线管理
 * @Author: masamiaoi
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {


}
