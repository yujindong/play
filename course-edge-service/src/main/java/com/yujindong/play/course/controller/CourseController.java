package com.yujindong.play.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yujindong.play.course.api.ICourseService;
import com.yujindong.play.course.dto.CourseDto;
import com.yujindong.play.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author yujindong
 */
@Controller
@RequestMapping("course")
public class CourseController {

    @Reference
    private ICourseService courseService;


    @RequestMapping(value = "courseList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDto> courseList(HttpServletRequest request) {
        UserDto user = (UserDto)request.getAttribute("user");
        System.out.println(1111111);
        return courseService.courseList();
    }
}
