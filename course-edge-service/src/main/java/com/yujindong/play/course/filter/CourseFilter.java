package com.yujindong.play.course.filter;

import com.yujindong.play.user.client.LoginFilter;
import com.yujindong.play.user.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yujindong
 */
public class CourseFilter extends LoginFilter {
    @Override
    protected void loginHandler(HttpServletRequest request, HttpServletResponse response, UserDto userDto) {
        System.out.println(userDto);
        request.setAttribute("user", userDto);
    }
}
