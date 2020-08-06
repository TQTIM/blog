package com.tq.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录状态检查，设置登录拦截器，防止未登录可以直接访问后台页面
 * 自定义拦截器还要在MyMvcConfig中注册
 * 从返回的session中看是否能取到用户登录信息
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override//目标方法前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser==null){
/*      //未登录，返回登录页并提示
            request.setAttribute("msg","没有权限，请登录");
            request.getRequestDispatcher("admin/login.html").forward(request,response);//转发到登录页面,我这转发失败就用重定向*/
            response.sendRedirect("/admin");
            return false;
        }else {
            //登录状态，放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
