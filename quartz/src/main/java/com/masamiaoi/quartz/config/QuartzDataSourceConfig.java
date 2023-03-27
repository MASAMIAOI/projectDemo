package com.masamiaoi.quartz.config;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author: MASAMIAOI
 * @description: dataSource
 * @date: 2023/3/27 21:25
 * @version: 1.0.0
 */
@Configuration
public class QuartzDataSourceConfig {
    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("masamiaoi");
        dataSource.setUrl("jdbc:mysql://110.40.135.78:3306/quartz?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        return dataSource;
    }
}
