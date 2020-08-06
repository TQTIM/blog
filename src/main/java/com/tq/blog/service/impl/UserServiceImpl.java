package com.tq.blog.service.impl;

import com.tq.blog.entity.User;
import com.tq.blog.repository.UserRepository;
import com.tq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service层接口实现类，方便controller层传参调用service层方法
 */
@Service //
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;//注入dao也就是repository
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}
