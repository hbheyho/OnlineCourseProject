package com.course.server.service.impl;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.service.ChapterService;
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

        // version 1.0
        /*for (Chapter chapter : chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            // BeanUtils 用于实体之间的复制
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }*/
        // version 2.0
        chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);

        pageDto.setList(chapterDtoList);

        return pageDto;
    }


    /**
     * @Author: HB
     * @Description: 新增/修改大章
     * @Date: 9:57 2020/9/22
     * @Params: chapterDto
     * @Returns: int
    */
    public int save(ChapterDto chapterDto) {
       if (StringUtils.isEmpty(chapterDto.getId())){
           return insert(chapterDto);
       }else {
           return edit(chapterDto);
       }
    }

    /**
     * @Author: HB
     * @Description: 删除大章数据
     * @Date: 11:25 2020/9/22
     * @Params: null
     * @Returns:
    */
    @Override
    public int delete(String id) {
        return chapterMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author: HB
     * @Description: 新增大章
     * @Date: 11:02 2020/9/22
     * @Params: null
     * @Returns:
    */
    private int insert(ChapterDto chapterDto) {
        // 设置UUID
        chapterDto.setId(UUIDUtil.getShortUuid());
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterDto, chapter);
        return chapterMapper.insert(chapter);
    }

    /**
     * @Author: HB
     * @Description: 修改大章
     * @Date: 11:02 2020/9/22
     * @Params: null
     * @Returns:
    */
    private int edit(ChapterDto chapterDto) {
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        return chapterMapper.updateByPrimaryKey(chapter);
    }
}
