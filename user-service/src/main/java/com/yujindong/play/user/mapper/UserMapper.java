package com.yujindong.play.user.mapper;

import com.yujindong.play.user.api.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author yujindong
 */
@Mapper
public interface UserMapper {
    @Select("select id, username, password, real_name as realName, mobile, email from t_user where id=#{id}")
    UserInfo getUserById(@Param("id")int id);

    @Select("select id, username, password, real_name as realName, mobile, email from t_user where username=#{username}")
    UserInfo getUserByName(@Param("username")String username);

    @Select("select id, username, password, real_name as realName, mobile, email from t_user where mobile=#{mobile}")
    UserInfo getUserByMobile(@Param("mobile")String mobile);

    @Insert("insert into t_user (username, password, real_name, mobile, email) values (#{user.username}, #{user.password}, #{user.realName}, #{user.mobile}, #{user.email})")
    void registerUser(@Param("user") UserInfo userInfo);
}
