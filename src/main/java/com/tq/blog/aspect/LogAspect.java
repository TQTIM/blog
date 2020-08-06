package com.tq.blog.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用切面方式获取访问者的IP等信息,日志和控制台打印出信息
 */
@Aspect  //aop切面
@Component
public class LogAspect {

    private Logger logger= LoggerFactory.getLogger(this.getClass());//定义日志记录器,信息会在控制台/日志文件打印

    //定义一个切点方法，注解的方法都有切点,这里是controller包下所有类下的所有方法都标为切点
    @Pointcut("execution(* com.tq.blog.controller.*.*(..))")
    public void log() {

    }

    @Before("log()") //在切点这使用切面，before表示切点标注的方法前运行
    public void doBefore() {//也可以传入参数JoinPoint joinPoint对象来获取方法名等信息

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        logger.info("访问者访问地址和IP为："+url+"-----"+ip);


        logger.info("-------dobefore切面方法执行-------");
    }

    @After("log()")
    public void doAfter() {
        logger.info("-------doafter执行了-------");
    }
    @AfterReturning(pointcut = "log()" ,returning ="result")
    public void doAfterReturn(Object result) {
        logger.info("Result:{}",result);
    }
}
