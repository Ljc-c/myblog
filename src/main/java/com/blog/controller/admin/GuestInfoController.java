package com.blog.controller.admin;

import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class GuestInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/guest-info")
    public String guestInfo(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                        Pageable pageable, Model model){
       model.addAttribute("page", userService.guestList(pageable));
        return "admin/guest-info";
    }

    /*删除*/
    @GetMapping("/guest-info/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        userService.deleteGuest(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/guest-info";
    }
}
