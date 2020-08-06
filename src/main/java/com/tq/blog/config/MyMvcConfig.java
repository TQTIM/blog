package com.tq.blog.config;

import com.tq.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自己创建配置类来扩展springmvc自动配置功能，实现WebMvcConfigurer接口，重写相关方法
 *
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override //注册视图映射，让请求来到其他访问路径页面
    public void addViewControllers(ViewControllerRegistry registry) {//视图映射
       registry.addViewController("/admin").setViewName("admin/login");
       registry.addViewController("/admin/main.html").setViewName("admin/index");//访问/admin/main.html请求来到/admin/index.html
    }

    @Override//注册自定义的拦截器
    public void addInterceptors(InterceptorRegistry registry) {//不用考虑静态资源，spring boot默认配置好了
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")//拦截/admin/下所有请求
                .excludePathPatterns("/admin/login.html")//排除的请求
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
             ;
    }
}