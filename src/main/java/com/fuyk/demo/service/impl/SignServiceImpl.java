package com.fuyk.demo.service.impl;

import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public BaseResultRsp login(Integer userId,String uuid){
        String userKey = "LOGIN_TEST_"+userId.toString();
        String sessionKeyNew = userKey + "_" + uuid;
        Set<String> set = redisTemplate.keys("*" + userKey + "*");
        if (!set.isEmpty()){
            for(String sessionKeyOld : set){
                redisTemplate.delete(sessionKeyOld);
            }
        }
        redisTemplate.opsForValue().set(sessionKeyNew,"1");
        redisTemplate.expire(sessionKeyNew,300, TimeUnit.SECONDS);
        return BaseResultRsp.builder().errmsg("success").build();
    }

    @Override
    public BaseResultRsp logout(String session){
        Set<String> set = redisTemplate.keys("*"+session);
        for(String sessionKey : set){
            redisTemplate.delete(sessionKey);
        }
        return BaseResultRsp.builder().errmsg("success").build();
    }
}
