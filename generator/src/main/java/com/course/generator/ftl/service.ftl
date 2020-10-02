package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HB
 * @Description: Service接口
 * @CreateDate: ${date}
 */
@Service
public interface ${Domain}Service {

     PageDto<${Domain}> list(PageDto pageDto);
     void save(${Domain}Dto ${domain}Dto);
     int delete(String id);
}