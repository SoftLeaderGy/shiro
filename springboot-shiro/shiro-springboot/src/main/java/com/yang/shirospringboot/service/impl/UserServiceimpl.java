package com.yang.shirospringboot.service.impl;

import com.yang.shirospringboot.dao.UserDao;
import com.yang.shirospringboot.entity.User;
import com.yang.shirospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/10/10:15
 */
@Service("UserServiceimpl")
public class UserServiceimpl implements UserService {
    @Autowired(required = false)
    UserDao userDao;
    @Override
    public User getUserByUserName(String userName) {
        return userDao.selectById(userName);
    }
}
