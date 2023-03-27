package com.masamiaoi.quartz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;


/**
 * @author: MASAMIAOI
 * @description: quartz 定时任务
 * @date: 2023/3/27 21:21
 * @version: 1.0.0
 */
@Configuration
public class QuartzConfiguration {

    // Quartz配置文件路径
    private static final String QUARTZ_CONFIG = "config/quartz.properties";

    @Value("${task.enabled:true}")
    private boolean enabled;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        // 设置加载的配置文件
        schedulerFactoryBean.setConfigLocation(new ClassPathResource(QUARTZ_CONFIG));

        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 系统启动后，延迟5s后启动定时任务，默认为0
        schedulerFactoryBean.setStartupDelay(5);

        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除 qrtz_job_details 表对应记录了
        schedulerFactoryBean.setOverwriteExistingJobs(true);

        // SchedulerFactoryBean在初始化后是否马上启动Scheduler，默认为true。如果设置为false，需要手工启动Scheduler
        schedulerFactoryBean.setAutoStartup(enabled);
        return schedulerFactoryBean;
    }
}
