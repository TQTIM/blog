package com.tq.blog.controller.admin;

import com.tq.blog.entity.Type;
import com.tq.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService typeService;

    /**
     * 分类页 分页显示
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")                 //注解指定一页大小是10，根据id排序，倒序                              //model装查询的返回值，页面可以获取值
    public String types(@PageableDefault(size = 10,sort ="id",direction = Sort.Direction.DESC) Pageable pageable, Model model) {//springboot根据前端页面的值封装好pageable，注解指定默认值
        model.addAttribute("page",typeService.listType(pageable));//
        return "admin/types";
    }

    /**
     * 分类页中 到达新增页
     * @return
     */
    @GetMapping("/types/input")
    public String input() {
        return "admin/types-input";
    }

    /**
     * post形式，新增页添加分类数据
     * @return
     */
    @PostMapping("/types")
    public String addTypes(Type type,RedirectAttributes attributes) {//重定向设置消息
        Type t = typeService.saveType(type);
        if(t==null){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }

       return "redirect:/admin/types";
    }

    /**
     * 修改分类名称，根据id查询到要修改的数据返回到共用的input框，
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")//提交分类修改
    public String editPost(@PathVariable("id") Long id,Type type,RedirectAttributes attributes) {
        Type t = typeService.updateTypeById(id,type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")//删除
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteTypeById(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
