package com.fuyk.demo.service.impl;

import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;
import com.fuyk.demo.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public UserInfoRsp queryUserInfo(Integer id){
        UserInfoRsp userInfoRsp = UserInfoRsp.builder()
                .id(5)
                .name("傅宇康")
                .sex("男")
                .isLocal(true)
                .build();
         return userInfoRsp;
    }
}
