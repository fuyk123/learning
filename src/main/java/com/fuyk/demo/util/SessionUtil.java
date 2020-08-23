package com.fuyk.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SessionUtil {
    @Autowired
    private static StringRedisTemplate redisTemplate;
    public static Boolean checkSessionIsValid(String session){
        try {
            redisTemplate.keys("*" + session + "*");
        }
        catch (NullPointerException e){
            return false;
        }
        return true;
    }

    public static void updateSession(String session){
        try {
            redisTemplate.keys("*" + session + "*");
        }
        catch (NullPointerException e){
            return ;
        }
        redisTemplate.expire(session,300, TimeUnit.SECONDS);
    }
}
