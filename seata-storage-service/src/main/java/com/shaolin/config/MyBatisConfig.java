package com.shaolin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.shaolin.dao"})
public class MyBatisConfig {
}
