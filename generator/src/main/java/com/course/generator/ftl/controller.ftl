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
 * @Description: Controller
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
     * @Description: 新增/编辑章节
     * @Date: ${date}
     * @Params: null
     * @Returns:
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto) {

        ValidatorUtil.require(${domain}Dto.getName(), "名称");
        ValidatorUtil.require(${domain}Dto.getCourseId(), "课程ID");
        ValidatorUtil.length(${domain}Dto.getCourseId(), "课程ID", 1, 8);

        ResponseDto responseDto = new ResponseDto<>();
        ${domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    /**
     * @Author: HB
     * @Description: 删除大章数据 - Restful风格
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
