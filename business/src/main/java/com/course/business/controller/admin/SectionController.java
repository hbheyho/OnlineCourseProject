package com.course.business.controller.admin;


import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.SectionService;
import com.course.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: HB
 * @Description: 小节 Controller
 * @CreateDate: 22:09 2020/10/02
 */

@RestController
@RequestMapping("/admin/section")
public class SectionController {

    // 日志所用标识
    private static final String BUSINESS_NAME = "小节";

    @Resource
    private SectionService sectionService;

    /**
     * @Author: HB
     * @Description:
     * @Date: 22:09 2020/10/02
     * @Params: pageDto
     * @Returns: pageDto
     */
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto<>();
        responseDto.setContent(sectionService.list(pageDto));
        return responseDto;
    }


    /**
     * @Author: HB
     * @Description: 新增/编辑小节
     * @Date: 22:09 2020/10/02
     * @Params: null
     * @Returns:
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto) {

        // 进行数据校验
                    ValidatorUtil.require(sectionDto.getTitle(), "标题");
                    ValidatorUtil.length(sectionDto.getTitle(), "标题", 1 , 50);
                    ValidatorUtil.length(sectionDto.getVideo(), "视频", 1 , 200);

        ResponseDto responseDto = new ResponseDto<>();
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    /**
     * @Author: HB
     * @Description: 删除小节数据 - Restful风格
     * @Date: 22:09 2020/10/02
     * @Params: id
     * @Returns:
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto<>();
        try {
            int result = sectionService.delete(id);
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
