package com.course.server.service.impl;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
import com.course.server.service.${Domain}Service;
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
 * @CreateDate: ${date}
 */
@Service("${domain}Service")
public class ${Domain}ServiceImpl implements ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /**
     * @Author: HB
     * @Description: 列表
     * @Date: ${date}
     * @Params:
     * @Returns:
     */
    @Override
    public PageDto<${Domain}> list(PageDto pageDto) {
        // 初始化PageHelper
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        // 进行数据查询
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        // 排序条件
        <#list fieldList as field >
            <#if field.nameHump == "sort">
                ${domain}Example.setOrderByClause("sort asc");
            </#if>
        </#list>
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        // 进行分页信息组装
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());
        // 重新封装查询结果
        List<${Domain}Dto> ${domain}DtoList = new ArrayList<>();

        ${domain}DtoList = CopyUtil.copyList(${domain}List, ${Domain}Dto.class);

        pageDto.setList(${domain}DtoList);

        return pageDto;
    }


    /**
     * @Author: HB
     * @Description: 新增/修改
     * @Date: ${date}
     * @Params:
     * @Returns: int
    */
    public void save(${Domain}Dto ${domain}Dto) {
       if (StringUtils.isEmpty(${domain}Dto.getId())){
          insert(${domain}Dto);
       }else {
           edit(${domain}Dto);
       }
    }

    /**
     * @Author: HB
     * @Description: 删除
     * @Date: ${date}
     * @Params: null
     * @Returns:
    */
    @Override
    public int delete(String id) {
        return ${domain}Mapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author: HB
     * @Description: 新增
     * @Date: ${date}
     * @Params: null
     * @Returns:
    */
    private void insert(${Domain}Dto ${domain}Dto) {

        // 设置UUID
        ${domain}Dto.setId(UUIDUtil.getShortUuid());
        ${Domain} ${domain} = new ${Domain}();
        BeanUtils.copyProperties(${domain}Dto, ${domain});

        Date now = new Date();
        <#list fieldList as field >
            <#if field.nameHump == "createdAt">
                ${domain}.setCreatedAt(now);
            </#if>
            <#if field.nameHump == "updatedAt">
                ${domain}.setUpdatedAt(now);
            </#if>
        </#list>

        ${domain}Mapper.insert(${domain});
    }

    /**
     * @Author: HB
     * @Description: 修改
     * @Date: ${date}
     * @Params: null
     * @Returns:
    */
    private void edit(${Domain}Dto ${domain}Dto) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);

        Date now = new Date();
        <#list fieldList as field >
            <#if field.nameHump == "updatedAt">
                ${domain}.setUpdatedAt(now);
            </#if>
        </#list>

        ${domain}Mapper.updateByPrimaryKey(${domain});
    }
}