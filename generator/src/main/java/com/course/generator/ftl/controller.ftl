package com.course.${module}.controller.admin;


import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.${Domain}Service;
import com.course.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: HB
 * @Description: ${name} Controller
 * @CreateDate: ${date}
 */

@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    // 日志所用标识
    private static final String BUSINESS_NAME = "${name}";

    @Resource
    private ${Domain}Service ${domain}Service;

    /**
     * @Author: HB
     * @Description:
     * @Date: ${date}
     * @Params: pageDto
     * @Returns: pageDto
     */
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto<>();
        responseDto.setContent(${domain}Service.list(pageDto));
        return responseDto;
    }


    /**
     * @Author: HB
     * @Description: 新增/编辑${name}
     * @Date: ${date}
     * @Params: null
     * @Returns:
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto) {

        // 进行数据校验
        <#list fieldList as field>
            <#if field.name != "id" && field.nameHump != "createdAt" && field.nameHump != "updatedAt" && field.nameHump != "sort ">
                <#if !field.nullAble>
                    ValidatorUtil.require(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}");
                </#if>
                <#if (field.length > 0)>
                    ValidatorUtil.length(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}", 1 , ${field.length});
                </#if>
            </#if>
        </#list>

        ResponseDto responseDto = new ResponseDto<>();
        ${domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    /**
     * @Author: HB
     * @Description: 删除${name}数据 - Restful风格
     * @Date: ${date}
     * @Params: id
     * @Returns:
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto<>();
        try {
            int result = ${domain}Service.delete(id);
            if (result < 1){
                responseDto.setSuccess(false);
                responseDto.setMessage("删除失败,请重新再试");
            }else {
                responseDto.setMessage("删除成功");
            }
        }catch (Exception e){
            responseDto.setSuccess(false);
            responseDto.setMessage("删除失败,请重新再试");
        }
        return responseDto;
    }


}
