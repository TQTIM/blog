package com.tq.blog.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * 捕获错误异常并交予error/error.html显示，
 * @ControllerAdvice表示拦截掉所有带有@Controller注解的控制器
 * @ExceptionHandler表明是异常处理方法
 * ModelAndView：返回一个页面信息
 * 通过拦截异常信息，在日志中记录，并返回给error页面
 * 标识了状态码的时候就不拦截，如资源找不到异常
 */
@ControllerAdvice //处理异常方法：1.@ControllerAdvice+@ExceptionHandler来处理异常 2.@Controller+@ExceptionHandler  3.实现HandlerExceptionResolver接口
public class ExceptionHandlerInterceptor {

    private Logger logger=LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //        记录异常信息：请求的URL，异常信息
        logger.error("Requst URL : {}，Exception : {}", request.getRequestURL(),e);

//        当标识了状态码的时候就不拦截
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");

        return modelAndView;//将记录的异常信息返回到error页面
    }
}
