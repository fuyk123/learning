package com.fuyk.demo.service.impl;

import com.fuyk.demo.pojo.web.req.AddUserInfoReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;
import com.fuyk.demo.service.UserInfoService;
import com.fuyk.demo.sqlservice.dao.UserMapper;
import com.fuyk.demo.sqlservice.domain.User;
import com.fuyk.demo.sqlservice.domain.UserQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserInfoRsp queryUserInfo(Integer id){
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            UserInfoRsp userInfoRsp = UserInfoRsp.builder()
                    .id(user.getId())
                    .name(user.getUserName())
                    .sex(user.getSex())
                    .isLocal((user.getLocal() == 1 ? true : false))
                    .errmsg("success")
                    .build();
            return userInfoRsp;
        }
        else{
            UserInfoRsp userInfoRsp = UserInfoRsp.builder()
                    .id(null)
                    .name(null)
                    .sex(null)
                    .isLocal(null)
                    .errmsg("无此id")
                    .build();
            return userInfoRsp;
        }
    }

    @Override
    public UserInfoRsp queryUserInfoByName(String name){
        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andUserNameEqualTo(name);
        List<User> userList = userMapper.selectByExample(userQuery);
        UserInfoRsp userInfoRsp = UserInfoRsp.builder()
                .id(userList.get(0).getId())
                .name(userList.get(0).getUserName())
                .sex(userList.get(0).getSex())
                .isLocal((userList.get(0).getLocal() ==1?true:false))
                .build();
        return userInfoRsp;
    }

    @Override
    public BaseResultRsp addUserInfo(AddUserInfoReq addUserInfoReq){
        BaseResultRsp baseResultRsp = new BaseResultRsp();
        User user = new User();
        user.setUserName(addUserInfoReq.getUserName());
        user.setSex(addUserInfoReq.getSex());
        user.setLocal(addUserInfoReq.getLocal());
        baseResultRsp.setErrmsg(userMapper.insert(user)>0?"success":"failed");
        return baseResultRsp;
    }
}
