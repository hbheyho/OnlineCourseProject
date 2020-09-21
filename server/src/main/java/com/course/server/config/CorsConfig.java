/*================ 此类废弃 =================*/
// 使用了gateway 来进行路由转发, 遂前端访问的都是gateway的地址, 所以跨域配置应该写到gateway中

/*
package com.course.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

*/
/**
 * @Author: HB
 * @Description: 跨域问题解决
 * @Date: 14:02 2020/9/21
 * @Params: null
 * @Returns:
*//*

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }

}
*/

