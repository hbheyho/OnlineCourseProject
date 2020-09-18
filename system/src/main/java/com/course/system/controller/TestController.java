package com.course.system.controller;

import com.course.server.domain.Test;
import com.course.server.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: HB
 * @Description:
 * @CreateDate: 0:44 2020/9/18
 */

@RestController
public class TestController {

    // Autowired - 按照byType 进行注入
    // Resources - 按照byName 进行注入
    @Resource
    private TestService testService;

    @RequestMapping("/test")
    public List<Test> test() {
        return testService.list();
    }
}
