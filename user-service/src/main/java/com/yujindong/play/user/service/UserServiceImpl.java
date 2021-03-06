package com.yujindong.play.user.service;

import com.yujindong.play.user.api.UserInfo;
import com.yujindong.play.user.api.UserService;
import com.yujindong.play.user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yujindong
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getTeacherById(int id) throws TException {
        return userMapper.getTeacherById(id);
    }

    @Override
    public UserInfo getUserByName(String username) throws TException {
        UserInfo user = userMapper.getUserByName(username);
        if(user == null) {
            return new UserInfo();
        }
        return user;
    }

    @Override
    public UserInfo getUserByMobile(String mobile) throws TException {
        UserInfo user = userMapper.getUserByMobile(mobile);
        if(user == null) {
            return new UserInfo();
        }
        return user;
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {
        System.out.println(userInfo);
        userMapper.registerUser(userInfo);
    }
}
