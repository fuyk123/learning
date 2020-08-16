package com.fuyk.demo.controller;

import com.fuyk.demo.pojo.web.req.AddUserInfoReq;
import com.fuyk.demo.pojo.web.req.UserInfoReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;
import com.fuyk.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/test")
@Api("User信息")
public class MainController {

    @Resource
    UserInfoService userInfoService;
    /**
     * @RestController           相当于 @Controller + @ResponseBody
     * @GetMapping("/hello") 相当于 @RequestMapping(value = "/hello",method = RequestMethod.GET)
     * @return
     */
    @GetMapping("/queryUserInfo")
    @ApiOperation(value = "查询User信息", notes = "返回请求里的message", response = UserInfoRsp.class)
    public UserInfoRsp queryUserInfo(@RequestParam(value = "id") Integer id) {
        return userInfoService.queryUserInfo(id);
    }

    @PostMapping("/queryUserInfoByName")
    public UserInfoRsp queryUserInfoByName(@RequestBody UserInfoReq userInfoReq){
        return userInfoService.queryUserInfoByName(userInfoReq.getName());
    }

    @PostMapping("/addUserInfo")
    @ApiOperation(value = "增加User信息", notes = "返回请求里的message", response = UserInfoRsp.class)
    public BaseResultRsp addUserInfo(@RequestBody @ApiParam(value = "请求体", required = true) AddUserInfoReq addUserInfo){
        return userInfoService.addUserInfo(addUserInfo);
    }
}
