package com.tq.blog.controller.admin;

import com.tq.blog.entity.User;
import com.tq.blog.repository.UserRepository;
import com.tq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 后台管理员登陆
 */
@Controller
@RequestMapping("/admin")//下面的访问路径前都要补上/admin
public class LoginController {
    @Autowired
    UserService userService;

/*    @GetMapping
    public String loginPage() {//MyMvcConfig配置了
        return "admin/login";
    }*/
    @PostMapping("/login") //完整访问是/admin/login
    public String login(@RequestParam String username, @RequestParam String password, Map<String,Object>map, HttpSession session) {
        User user = userService.checkUser(username, password);//通过获取参数查询数据库获取有此用户
        if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(password)&&user!=null){//user对象不为空时登录成功
            session.setAttribute("loginUser",username);//登录成功将用户名放入session中，用于LoginInterceptor拦截器检查是否登录
            return "redirect:main.html";//防止表单重复提交，可重定向到主页main.html，再通过MyMvcConfig配置让main.html显示index.html页面

        }else {//登入失败,并返回错误提示消息
            map.put("msg","账号或密码错误");

            return "admin/login";

        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");//退出移除session的用户
        return "redirect:/admin";

    }
}
