package com.tq.blog.service;

import com.tq.blog.entity.User;

/**
 * 定义接口和相关方法
 */
public interface UserService {
    User checkUser(String username,String password);
}
