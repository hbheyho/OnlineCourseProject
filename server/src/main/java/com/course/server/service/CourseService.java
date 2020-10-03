package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HB
 * @Description: Service接口
 * @CreateDate: 14:37 2020/10/03
 */
@Service
public interface CourseService {

     PageDto<Course> list(PageDto pageDto);
     void save(CourseDto courseDto);
     int delete(String id);
}