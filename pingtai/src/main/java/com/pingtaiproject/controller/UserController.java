package com.pingtaiproject.controller;


import com.pingtaiproject.error.BusinessException;
import com.pingtaiproject.error.EmBusinessError;
import com.pingtaiproject.response.CommonReturnType;
import com.pingtaiproject.service.UserService;
import com.pingtaiproject.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id)throws BusinessException {
        //调用service服务获取对应id的用户对象返回给前端
        UserModel userModel=userService.getUserById(id);
        //若用户信息不存在
        if (userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        return CommonReturnType.create(userModel);
    }

}
