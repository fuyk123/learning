package com.fuyk.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "weblog")
public class LogUtil {
    public static void logUtil(Object param,Integer paramType) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String paramString = objectMapper.writeValueAsString(param);
        //slf4j log.info()里{}表示占位符
        if (paramType == ParamType.REQUEST_TYPE.ordinal()){
            log.info("请求接口：queryUserInfoByName,Request Body->{}",paramString);
        }
        else if (paramType == ParamType.RESPONSE_TYPE.ordinal()){
            log.info("请求接口：queryUserInfoByName,Response Body->{}",paramString);
        }
        else{
            log.info("method:logUtil,paramType传参只能是0，1");
        }
    }
    enum ParamType{
       REQUEST_TYPE,
        RESPONSE_TYPE
    }
}
