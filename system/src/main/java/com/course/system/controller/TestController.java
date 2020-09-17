package com.course.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: HB
 * @Description:
 * @CreateDate: 0:44 2020/9/18
 */

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
