package com.fuyk.demo.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuyk.demo.util.LogUtil;
import com.fuyk.demo.util.SessionUtil;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class UpdateSessionAspect {
    @Pointcut("execution(public * com.fuyk.demo.controller.MainController.*(..)))")
    public void userProcess(){
    }

    @Before("userProcess()")
    public String checkSession()  {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String session = request.getHeader("Cookie").replaceAll("JSESSIONID=","");
        if(SessionUtil.checkSessionIsValid(session)){
            return "session is valid";
        }
        else{
            return "session is invalid";
        }

    }

    @AfterReturning("userProcess()")
    public void updateSession(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String session = request.getHeader("Cookie").replaceAll("JSESSIONID=","");
        SessionUtil.updateSession(session);
    }
}
