package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.entity.Guest;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        Guest guest= (Guest) session.getAttribute("guest");
       /* Long guestId = guest.getId();
        System.out.println("guestId: " + guestId);
//        List<Comment> cs=commentService.listCommentsByGuestId(guestId);
        List<Comment> cs= commentService.listComments();
        System.out.println("111111111");
        if(cs==null){
            System.out.println("null========");
        }
        model.addAttribute("guestComments",cs);*/

        return "home";
    }

    /*修改*/
    @GetMapping("/home-input/{id}")
    public String editInput(@PathVariable Long id,
                            Model model){

        Guest guest = userService.getGuest(id);
        model.addAttribute("guest",guest);
        return "home-input";
    }


}
