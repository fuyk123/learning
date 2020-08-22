package com.fuyk.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuyk.demo.pojo.web.req.AddUserInfoReq;
import com.fuyk.demo.pojo.web.req.UserInfoReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoListRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;
import com.fuyk.demo.service.UserInfoService;
import com.fuyk.demo.util.LogUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@Api("User信息")
public class MainController {

    @Resource
    private UserInfoService userInfoService;
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
    @GetMapping("/queryUserInfoByPath/{id}")
    @ApiOperation(value = "查询User信息", notes = "返回请求里的message", response = UserInfoRsp.class)
    //@PathVariable注解拿url里的占位符参数，和get请求还是不一样的，get是?
    public UserInfoRsp queryUserInfoByPath(@PathVariable Integer id) {
        return userInfoService.queryUserInfo(id);
    }

    @PostMapping("/queryUserInfoByName")
    public PageInfo queryUserInfoByName(@Valid @RequestBody UserInfoReq userInfoReq) throws JsonProcessingException {
        LogUtil.logUtil(userInfoReq,0);
        return userInfoService.queryUserInfoByName(userInfoReq.getName(),userInfoReq.getPageNo(),userInfoReq.getPageSize());
    }

    @PostMapping("/queryUserInfoByNameLimit")
    public UserInfoListRsp queryUserInfoByNameLimit(@Valid @RequestBody UserInfoReq userInfoReq){
        return userInfoService.queryUserInfoByNameLimit(userInfoReq.getName(),userInfoReq.getLimit());
    }
    @PostMapping("/queryUserInfoByNamePage")
    public UserInfoListRsp queryUserInfoByNamePage(@Valid @RequestBody UserInfoReq userInfoReq){
        return userInfoService.queryUserInfoByNamePage(userInfoReq.getName(),userInfoReq.getPageNo(),userInfoReq.getPageSize());
    }

    @PostMapping("/addUserInfo")
    @ApiOperation(value = "增加User信息", notes = "返回请求里的message", response = UserInfoRsp.class)
    public BaseResultRsp addUserInfo(@Valid @RequestBody @ApiParam(value = "请求体", required = true) AddUserInfoReq addUserInfo){
        return userInfoService.addUserInfo(addUserInfo);
    }
}
