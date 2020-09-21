package com.course.server.service.impl;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HB
 * @Description:
 * @CreateDate: 9:59 2020/9/21
 */
@Service("chapterService")
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    /**
     * @Author: HB
     * @Description: 大章列表查询
     * @Date: 10:00 2020/9/21
     * @Params:
     * @Returns:
     */
    @Override
    public List<ChapterDto> list() {

        ChapterExample chapterExample = new ChapterExample();
        chapterExample.setOrderByClause("id desc");

        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<>();

        for (Chapter chapter : chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            // BeanUtils 用于实体之间的复制
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
