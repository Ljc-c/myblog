package com.blog.controller.admin;

import com.blog.entity.Type;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                        Pageable pageable,
                        Model model){   //Pageable根据前端参数自动分装
        //显示十条，根据id排序，倒序
        //Model 前端展示
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /*修改*/
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,
                            Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }
    /*删除*/
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

    @PostMapping("/types")
    public String post(@Valid Type type,
                       BindingResult result,
                       RedirectAttributes attributes){

        Type te = typeService.getTypeByName(type.getName());
        if(te != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if(t == null){
            //未保存成功
            attributes.addFlashAttribute("message","操作失败");

        }else{
            //保存成功
            attributes.addFlashAttribute("message","操作成功");

        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editpost(@Valid Type type,
                       BindingResult result,
                       @PathVariable Long id,
                           RedirectAttributes attributes){

        Type te = typeService.getTypeByName(type.getName());
        if(te != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if(t == null){
            //未保存成功
            attributes.addFlashAttribute("message","更新失败");

        }else{
            //保存成功
            attributes.addFlashAttribute("message","更新成功");

        }
        return "redirect:/admin/types";
    }
}
