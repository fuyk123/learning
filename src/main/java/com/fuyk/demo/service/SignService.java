package com.fuyk.demo.service;

import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;

public interface SignService {
    public BaseResultRsp login(Integer userId,String uuid);
    public BaseResultRsp logout(String session);
}
