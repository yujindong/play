package com.yujindong.play.course;

import com.yujindong.play.course.api.ICourseService;
import com.yujindong.play.course.dto.CourseDto;
import com.yujindong.play.course.mapper.CourseMapper;

import java.util.List;


public class CourseServiceImpl implements ICourseService {
    private CourseMapper courseMapper;
    @Override
    public List<CourseDto> courseList() {
        List<CourseDto> courseList =  courseMapper.listCourse();
        if(courseList != null) {
            for(CourseDto course : courseList) {
                Integer teacherId = courseMapper.getTeacherIdByCourseId(course.getId());
                if(teacherId != null) {

                }
            }
        }
        return null;
    }
}
