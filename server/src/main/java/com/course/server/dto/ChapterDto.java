package com.course.server.dto;

/**
 * @Author: HB
 * @Description: chapter 传输类.
 *               DTO(Data Transfer Object)数据传输类 - 用来接受参数/返回结果, 进行数据的传输.
 *               为什么要DTO呢, 因为domain中的实体类不能被修改, DTO用来数据传输, 可以使用增加或删除属性,
 *               增加一个DTO来提升便利性.
 * @Date: 9:52 2020/9/21
*/
public class ChapterDto {
    private String id;

    private String courseId;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}