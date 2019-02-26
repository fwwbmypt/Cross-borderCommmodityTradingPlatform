package com.pingtaiproject.service;

import com.pingtaiproject.service.model.UserModel;

public interface UserService {

    //通过id获取用户对象
    UserModel getUserById(Integer id);
}
