package com.course.business.controller.admin;


import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: HB
 * @Description: 课程 Controller
 * @CreateDate: 14:37 2020/10/03
 */

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    // 日志所用标识
    private static final String BUSINESS_NAME = "课程";

    @Resource
    private CourseService courseService;

    /**
     * @Author: HB
     * @Description:
     * @Date: 14:37 2020/10/03
     * @Params: pageDto
     * @Returns: pageDto
     */
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto<>();
        responseDto.setContent(courseService.list(pageDto));
        return responseDto;
    }


    /**
     * @Author: HB
     * @Description: 新增/编辑课程
     * @Date: 14:37 2020/10/03
     * @Params: null
     * @Returns:
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto) {

        // 进行数据校验
                    ValidatorUtil.require(courseDto.getName(), "名称");
                    ValidatorUtil.length(courseDto.getName(), "名称", 1 , 50);
                    ValidatorUtil.length(courseDto.getSummary(), "概述", 1 , 2000);
                    ValidatorUtil.require(courseDto.getPrice(), "价格(元)");
                    ValidatorUtil.length(courseDto.getImage(), "封面", 1 , 100);
                    ValidatorUtil.require(courseDto.getLevel(), "级别");

        ResponseDto responseDto = new ResponseDto<>();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    /**
     * @Author: HB
     * @Description: 删除课程数据 - Restful风格
     * @Date: 14:37 2020/10/03
     * @Params: id
     * @Returns:
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto<>();
        try {
            int result = courseService.delete(id);
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
