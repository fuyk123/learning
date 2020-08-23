package com.fuyk.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuyk.demo.pojo.web.req.LoginReq;
import com.fuyk.demo.pojo.web.req.UserInfoReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.service.SignService;
import com.fuyk.demo.util.LogUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/sign")
@Api("登录、登出")
public class LoginController {
    @Autowired
    private SignService signService;

    @PostMapping("/login")
    public ResponseEntity<BaseResultRsp> login(@Valid @RequestBody LoginReq loginReq) throws JsonProcessingException {
        String uuid = String.valueOf(UUID.randomUUID()).replaceAll("-","");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Cookie", "JSESSIONID=" + uuid);
        return new ResponseEntity<>(signService.login(loginReq.getUserId(),uuid),responseHeaders, HttpStatus.OK);
    }
    @PostMapping("/logout")
    public BaseResultRsp logout(@RequestHeader Map<String,String> cookieMap) throws JsonProcessingException {
        //LogUtil.logUtil("{}",0);
        String session = cookieMap.get("cookie").replaceAll("JSESSIONID=","");
        return signService.logout(session);
    }
}
