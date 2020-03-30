package com.xxx.web.advice;

import com.alibaba.fastjson.JSON;
import com.mickey.core.exception.NoveSystemException;
import com.mickey.core.utils.common.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: web层日志切面
 * @author J·K
 * @date 2018/7/27 18:06

 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect
{
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    
    @Pointcut("execution(public * com.xxx.web.controller..*.*(..))")
    public void webLog()
    {
    };
    
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable
    {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        printReuestMsg(request, joinPoint);
    }

    private void printReuestMsg(HttpServletRequest request, JoinPoint joinPoint)
    {
        String method = request.getMethod();
        log.info("【full requestURL】:" + request.getRequestURL().toString());
        log.info("【ip】:" + IpUtils.getIpAddress(request));
        log.info("【remoteAddr】:" + request.getRemoteAddr());
        log.info("【remoteHost】:" + request.getRemoteHost());
        log.info("【localAddr】:" + request.getLocalAddr());
        log.info("【method】:" + method);
        log.info("【headers】:" + getHeadersInfo(request));
        log.info("【parameters】:" + this.getParam(request.getParameterMap()));
        if("POST".equals(method)){
            printPostParams(joinPoint);
        }
        log.info("【classMethod】:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    private void printPostParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String params = "";
        if(args.length>0){
            Object object = args[0];
            Map map = getKeyAndValue(object);
            params = JSON.toJSONString(map);
            log.info("【postParams】:" + params);
        }
    }

    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class clazz = (Class) obj.getClass();
        if(clazz.getName().equals("java.lang.String")) {
            map.put("value",obj);
            return map;
        }
        Field[] fs = clazz.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);
            try {
                Object val = f.get(obj);
                map.put(f.getName(), val);
            } catch (IllegalArgumentException e) {
                log.error("[WebLogAspect-getKeyAndValue]:{}",e);
                throw new NoveSystemException("500",e.getMessage());
            } catch (IllegalAccessException e) {
                log.error("[WebLogAspect-getKeyAndValue]:{}",e);
                throw new NoveSystemException("500",e.getMessage());
            }
        }
        return map;
    }
    
    private String getParam(Map<String, String[]> map)
    {
        StringBuilder str = new StringBuilder();
        for(String key : map.keySet())
        {
            if(!str.toString().equals(""))
                str.append("&");
            str.append(key + "= " + String.join(",", map.get(key)));
        }
        return str.toString();
    }
    
    private Map<String, String> getHeadersInfo(HttpServletRequest request)
    {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements())
        {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
    
    @AfterReturning(returning = "response", pointcut = "webLog()")
    public void doAfterReturning(Object response) throws Throwable
    {
        // 处理完请求，返回内容
        log.info("【RESPONSE】: " + response);
        log.info("【SPEND TIME】: " + (System.currentTimeMillis() - startTime.get()));
    }
}
