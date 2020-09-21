package com.course.business.controller.admin;

import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: HB
 * @Description: 章节Controller
 * @CreateDate: 9:35 2020/9/21
 */

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

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
    public PageDto<Chapter> chapter(@RequestBody PageDto pageDto) {
        return chapterService.list(pageDto);
    }
}
