package com.fuyk.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuyk.demo.pojo.web.req.AddUserInfoReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoListRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;
import com.github.pagehelper.PageInfo;

public interface  UserInfoService {
    public UserInfoRsp queryUserInfo(Integer id);
    public UserInfoRsp queryUserInfoByPath(Integer id);
    public PageInfo queryUserInfoByName(String name, Integer pageNo, Integer pageSize) throws JsonProcessingException;
    public UserInfoListRsp queryUserInfoByNameLimit(String name , Integer limit);
    public UserInfoListRsp queryUserInfoByNamePage(String name,Integer pageNo,Integer pageSize);
    public BaseResultRsp addUserInfo(AddUserInfoReq addUserInfoReq);
}
