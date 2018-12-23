package com.yujindong.play.course.mapper;

import com.yujindong.play.course.dto.CourseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select * from t_course")
    List<CourseDto> listCourse();

    @Select("select user_id from t_user_course where course_id=#{courseId}")
    Integer getTeacherIdByCourseId(@Param("courseId")int courseId);
}
