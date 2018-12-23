package com.yujindong.play.course.api;

import com.yujindong.play.course.dto.CourseDto;

import java.util.List;

public interface ICourseService {
    List<CourseDto> courseList();
}
