package com.fuyk.demo.service;

import com.fuyk.demo.pojo.web.req.AddUserInfoReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;

public interface  UserInfoService {
    public UserInfoRsp queryUserInfo(Integer id);
    public UserInfoRsp queryUserInfoByName(String name);
    public BaseResultRsp addUserInfo(AddUserInfoReq addUserInfoReq);
}
