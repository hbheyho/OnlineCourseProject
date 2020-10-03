package com.course.server.service.impl;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.service.CourseService;
import com.course.server.util.CopyUtil;
import com.course.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @Author: HB
 * @Description:
 * @CreateDate: 14:37 2020/10/03
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * @Author: HB
     * @Description: 列表
     * @Date: 14:37 2020/10/03
     * @Params:
     * @Returns:
     */
    @Override
    public PageDto<Course> list(PageDto pageDto) {
        // 初始化PageHelper
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        // 进行数据查询
        CourseExample courseExample = new CourseExample();
        // 排序条件
                courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        // 进行分页信息组装
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());
        // 重新封装查询结果
        List<CourseDto> courseDtoList = new ArrayList<>();

        courseDtoList = CopyUtil.copyList(courseList, CourseDto.class);

        pageDto.setList(courseDtoList);

        return pageDto;
    }


    /**
     * @Author: HB
     * @Description: 新增/修改
     * @Date: 14:37 2020/10/03
     * @Params:
     * @Returns: int
    */
    public void save(CourseDto courseDto) {
       if (StringUtils.isEmpty(courseDto.getId())){
          insert(courseDto);
       }else {
           edit(courseDto);
       }
    }

    /**
     * @Author: HB
     * @Description: 删除
     * @Date: 14:37 2020/10/03
     * @Params: null
     * @Returns:
    */
    @Override
    public int delete(String id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author: HB
     * @Description: 新增
     * @Date: 14:37 2020/10/03
     * @Params: null
     * @Returns:
    */
    private void insert(CourseDto courseDto) {

        // 设置UUID
        courseDto.setId(UUIDUtil.getShortUuid());
        Course course = new Course();
        BeanUtils.copyProperties(courseDto, course);

        Date now = new Date();
                course.setCreatedAt(now);
                course.setUpdatedAt(now);

        courseMapper.insert(course);
    }

    /**
     * @Author: HB
     * @Description: 修改
     * @Date: 14:37 2020/10/03
     * @Params: null
     * @Returns:
    */
    private void edit(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);

        Date now = new Date();
                course.setUpdatedAt(now);

        courseMapper.updateByPrimaryKey(course);
    }
}