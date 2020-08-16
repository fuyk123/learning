package com.fuyk.demo.controller;

import com.fuyk.demo.pojo.web.req.UserInfoReq;
import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;
import com.fuyk.demo.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/test")
public class MainController {

    @Resource
    UserInfoService userInfoService;
    /**
     * @RestController           相当于 @Controller + @ResponseBody
     * @GetMapping("/hello") 相当于 @RequestMapping(value = "/hello",method = RequestMethod.GET)
     * @return
     */
    @GetMapping("/queryUserInfo")
    public UserInfoRsp queryUserInfo(@RequestParam(value = "id") Integer id) {
        return userInfoService.queryUserInfo(id);
    }

    @PostMapping("/queryUserInfoByName")
    public UserInfoRsp queryUserInfoByName(@RequestBody UserInfoReq userInfoReq){
        return userInfoService.queryUserInfoByName(userInfoReq.getName());
    }
}
