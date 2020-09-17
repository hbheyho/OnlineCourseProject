package com.course.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    // spring boot 默认集成slf4j框架
    private static final Logger log = LoggerFactory.getLogger(EurekaApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EurekaApplication.class);
        Environment environment = application.run(args).getEnvironment();
        log.info("启动成功！");
        log.info("Eureka 地址: \thttp://127.0.0.1:{}", environment.getProperty("server.port"));
    }

}
