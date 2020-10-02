package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HB
 * @Description: Service接口
 * @CreateDate: 22:09 2020/10/02
 */
@Service
public interface SectionService {

     PageDto<Section> list(PageDto pageDto);
     void save(SectionDto sectionDto);
     int delete(String id);
}