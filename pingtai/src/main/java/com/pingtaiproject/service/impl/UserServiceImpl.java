package com.pingtaiproject.service.impl;

import com.pingtaiproject.dao.UserDOMapper;
import com.pingtaiproject.dataobject.UserDO;
import com.pingtaiproject.service.UserService;
import com.pingtaiproject.service.model.UserModel;
import com.pingtaiproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        //调用userdomapper获取对应的用户dataobject
        UserDO userDO=userDOMapper.selectByPrimaryKey(id);
        if(userDO==null){
            return null;
        }
        return convertFromDataObject(userDO);
    }


    private UserModel convertFromDataObject(UserDO userDO){
        if(userDO==null){
            return null;
        }
        UserModel userModel=new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        /*if(userPasswordDO!=null) {
            userModel.setEncrptPassword(userpasswordDO.getEncrptPassword());
        }*/
            return userModel;
    }
}
