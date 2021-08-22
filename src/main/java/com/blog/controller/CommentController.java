package com.blog.controller;

import com.blog.entity.Comment;
import com.blog.entity.User;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;
	
	/**
	* //yml配置文件中输入  comment.avatar: /images/**.jpg
	* @Value("${comment.avatar}")
	* private String avatar;
	*/

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
//        System.out.println("2222");
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
//        System.out.println("111111111111");
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User)session.getAttribute("user");

        if(user!=null){
            //comment.setAvatar(user.getAvatar());//设置头像
            comment.setAdminComment(true);
        }else {
            //comment.setAvatar(avatar);//设置头像
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }

    //访客管理评论
    @GetMapping("/guestComments/{guestId}")
    public String guestComments(@PathVariable Long guestId,Model model){
        model.addAttribute("guestComments",commentService.listCommentsByGuestId(guestId));
        return "home :: guest-comments";
    }
}
