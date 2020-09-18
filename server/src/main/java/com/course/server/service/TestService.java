package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: HB
 * @Description:
 * @CreateDate: 11:45 2020/9/18
 */

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        TestExample example = new TestExample();
        TestExample.Criteria criteria  = example.createCriteria();
        criteria.andIdEqualTo("3");
        return testMapper.selectByExample(example);
    }
}
