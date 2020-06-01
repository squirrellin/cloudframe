package com.yaoshun.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.yaoshun.dao"})
public class MyBatisConfig {
}
