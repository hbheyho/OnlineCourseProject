package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HB
 * @Description: 章节操作Service接口
 * @CreateDate: 9:33 2020/9/21
 */
@Service
public interface ChapterService {

    /**
     * @Author: HB
     * @Description: 大章列表查询
     * @Date: 10:00 2020/9/21
     * @Params:
     * @Returns:
    */
    public PageDto<Chapter> list(PageDto pageDto);

}
