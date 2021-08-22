package com.blog.controller;

import com.blog.entity.Guest;
import com.blog.service.UserService;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(){
        return "register";
    }



    @PostMapping("/gregister")
    public String post(Guest guest,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       RedirectAttributes attributes){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Date createtime= new Date();

        guest.setUsername(username);
        guest.setPassword(password);
        guest.setEmail(email);
        guest.setCreateTime(createtime);
        Guest guest1=userService.saveGuest(guest);

        if(guest1 == null){
            //未保存成功
            attributes.addFlashAttribute("message","操作失败");

        }else{
            try {
                response.getWriter().println("<script language='javascript'>alert('用户注册成功！');window.location.href='admin/login.html';</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return "register";

    }

    @PostMapping("/checkGuestName")
    public void checkName(HttpServletRequest request,
                          HttpServletResponse response){
        String username = request.getParameter("username");
        //检查数据库是否重名
        boolean flag = userService.checkguestbyName(username);
        if(flag==true){
            try {
                response.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().print("false");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
