package com.fuyk.demo.service;

import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;

public interface  UserInfoService {
    public UserInfoRsp queryUserInfo(Integer id);
    public UserInfoRsp queryUserInfoByName(String name);
}
