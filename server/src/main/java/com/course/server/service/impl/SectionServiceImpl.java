package com.course.server.service.impl;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
import com.course.server.service.SectionService;
import com.course.server.util.CopyUtil;
import com.course.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: HB
 * @Description:
 * @CreateDate: 22:09 2020/10/02
 */
@Service("sectionService")
public class SectionServiceImpl implements SectionService {

    @Resource
    private SectionMapper sectionMapper;

    /**
     * @Author: HB
     * @Description: 列表
     * @Date: 22:09 2020/10/02
     * @Params:
     * @Returns:
     */
    @Override
    public PageDto<Section> list(PageDto pageDto) {
        // 初始化PageHelper
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        // 进行数据查询
        SectionExample sectionExample = new SectionExample();
        // 排序条件
                sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        // 进行分页信息组装
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());
        // 重新封装查询结果
        List<SectionDto> sectionDtoList = new ArrayList<>();

        sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);

        pageDto.setList(sectionDtoList);

        return pageDto;
    }


    /**
     * @Author: HB
     * @Description: 新增/修改
     * @Date: 22:09 2020/10/02
     * @Params:
     * @Returns: int
    */
    public void save(SectionDto sectionDto) {
       if (StringUtils.isEmpty(sectionDto.getId())){
          insert(sectionDto);
       }else {
           edit(sectionDto);
       }
    }

    /**
     * @Author: HB
     * @Description: 删除
     * @Date: 22:09 2020/10/02
     * @Params: null
     * @Returns:
    */
    @Override
    public int delete(String id) {
        return sectionMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author: HB
     * @Description: 新增
     * @Date: 22:09 2020/10/02
     * @Params: null
     * @Returns:
    */
    private void insert(SectionDto sectionDto) {

        // 设置UUID
        sectionDto.setId(UUIDUtil.getShortUuid());
        Section section = new Section();
        BeanUtils.copyProperties(sectionDto, section);

        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);

        sectionMapper.insert(section);
    }

    /**
     * @Author: HB
     * @Description: 修改
     * @Date: 22:09 2020/10/02
     * @Params: null
     * @Returns:
    */
    private void edit(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);

        Date now = new Date();
        section.setUpdatedAt(now);

        sectionMapper.updateByPrimaryKey(section);
    }
}