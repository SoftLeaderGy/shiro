package com.yang.shirospringboot.service;

import com.yang.shirospringboot.entity.User;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/10/10:15
 */
public interface UserService {
    User getUserByUserName(String userName);
}
