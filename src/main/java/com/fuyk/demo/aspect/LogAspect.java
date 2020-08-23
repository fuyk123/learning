package com.fuyk.demo.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuyk.demo.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {
    //*全匹配，和linux差不多
    @Pointcut("execution(public * com.fuyk.demo.controller.*Controller.*(..)))")
    public void userProcess(){
    }

    @Before("userProcess()")
    public void logAspectBefore(JoinPoint joinPoint) throws JsonProcessingException {
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("args",joinPoint.getArgs());
        LogUtil.logUtil(joinPoint.getSignature().getName(),reqMap,0);
    }

    //注解里的returning表示返回的对象
    @AfterReturning(pointcut = "userProcess()",returning = "rsp")
    public void logAspectAfter(JoinPoint joinPoint,Object rsp) throws JsonProcessingException{
        LogUtil.logUtil(joinPoint.getSignature().getName(),rsp,1);
    }
}
