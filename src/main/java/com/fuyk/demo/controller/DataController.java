package com.fuyk.demo.controller;


import com.fuyk.demo.pojo.web.req.DataReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/upload")
public class DataController {
    @Autowired
    private DataService dataService;
    @PostMapping("/mysql")
    public BaseResultRsp upload(@RequestBody DataReq dataReq)  {
        return  dataService.upload(dataReq);
    }
}
