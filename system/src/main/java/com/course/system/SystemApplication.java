package com.course.system;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
/* 组件扫描 */
@ComponentScan("com.course")
/* Mapper扫描 */
@MapperScan("com.course.server.mapper")
public class SystemApplication {

    // spring boot 默认集成slf4j框架
    private static final Logger log = LoggerFactory.getLogger(SystemApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SystemApplication.class);
        Environment environment = application.run(args).getEnvironment();
        log.info("启动成功！");
        log.info("System 地址: \thttp://127.0.0.1:{}", environment.getProperty("server.port"));
    }

}
