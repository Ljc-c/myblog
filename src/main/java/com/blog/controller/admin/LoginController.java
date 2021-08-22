package com.blog.controller.admin;

import com.blog.entity.Guest;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/admin","/"})
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
//        Object o = new Guest();

        User user = userService.checkUser(username,password);
//        if(o instanceof Guest)
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/admin/blogs";
        }
        else{

            Guest guest = userService.checkguester(username, password);
            if(guest != null){
//                System.out.println(guest.getLevelExp());
                if( guest.getLevelExp() == null ){
                    guest.setLevelExp(1);
                }else{
                guest.setLevelExp(guest.getLevelExp()+1);}
                userService.updateguestLevel(guest);
//                System.out.println("login"+guest.getUsername());
                session.setAttribute("guest",guest);
                return "redirect:/home";
//                return "home";//无homecontroller
            }else{

                attributes.addFlashAttribute("message","用户名或密码错误！");
                return "redirect:/admin";
            }
        }
    }


    /**
     * 校验验证码
     */
    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST,headers = "Accept=application/json")
    public boolean checkVerify(@RequestParam String verifyInput, HttpSession session) {

        try{
            //从session中获取随机数
            String inputStr = verifyInput;
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return false;
            }
            if (random.equals(inputStr)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
//            logger.error("验证码校验失败", e);
            return false;
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }




}
