package com.fuyk.demo.service;

import com.fuyk.demo.pojo.web.req.DataReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import org.springframework.stereotype.Service;


public interface DataService {
    BaseResultRsp upload(DataReq dataReq);
}
