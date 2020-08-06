package com.tq.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
      //  int i=10/0;
        return "index";
    }

/*    @GetMapping("/{id}")
    public String aspect(@PathVariable("id") int id) {

        System.out.println("---------方法执行了，用于测试切面。。。");
        return "index";
    }*/
}
