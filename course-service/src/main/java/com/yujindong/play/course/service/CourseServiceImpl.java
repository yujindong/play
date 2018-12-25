package com.yujindong.play.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yujindong.play.course.api.ICourseService;
import com.yujindong.play.course.dto.CourseDto;
import com.yujindong.play.course.mapper.CourseMapper;
import com.yujindong.play.course.thrift.ServiceProvider;
import com.yujindong.play.user.api.UserInfo;
import com.yujindong.play.user.dto.TeacherDto;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public List<CourseDto> courseList() {
        List<CourseDto> courseList =  courseMapper.listCourse();
        if(courseList != null) {
            for(CourseDto course : courseList) {
                Integer teacherId = courseMapper.getTeacherIdByCourseId(course.getId());
                if(teacherId != null) {
                    try {
                        UserInfo teacher = serviceProvider.getUserService().getTeacherById(teacherId);

                        course.setTeacher(buildTeacherDto(teacher));
                    } catch (TException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return courseList;
    }

    private TeacherDto buildTeacherDto(UserInfo teacher) {
        TeacherDto teacherDto = new TeacherDto();
        BeanUtils.copyProperties(teacher, teacherDto);
        return teacherDto;
    }
}
