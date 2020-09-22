package com.course.business.controller.admin;


import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: HB
 * @Description: 章节Controller
 * @CreateDate: 9:35 2020/9/21
 */

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final String BUSINESS_NAME = "大章";

    @Resource
    private ChapterService chapterService;

    /**
     * @Author: HB
     * @Description: 章节列表展示 - 要接收前端传递的json数据, 需要设定@RequestBody
     * @Date: 14:59 2020/9/21
     * @Params: pageDto
     * @Returns: pageDto
     */
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto<>();
        responseDto.setContent(chapterService.list(pageDto));
        return responseDto;
    }


    /**
     * @Author: HB
     * @Description: 新增/编辑章节
     * @Date: 9:59 2020/9/22
     * @Params: null
     * @Returns:
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {

        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);

        ResponseDto responseDto = new ResponseDto<>();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    /**
     * @Author: HB
     * @Description: 删除大章数据 - Restful风格
     * @Date: 11:23 2020/9/22
     * @Params: id
     * @Returns:
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto<>();
        try {
            int result = chapterService.delete(id);
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
