package com.course.server.service.impl;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.service.ChapterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageDto<Chapter> list(PageDto pageDto) {
        // 初始化PageHelper
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        // 进行数据查询
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        // 进行分页信息组装
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
        // 重新封装查询结果
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (Chapter chapter : chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            // BeanUtils 用于实体之间的复制
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        pageDto.setList(chapterDtoList);

        return pageDto;
    }
}
