package com.blog.service;

import com.blog.entity.Comment;
import com.blog.entity.Guest;
import com.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User checkUser(String username,String password);

    //访客
    Guest checkguester(String username,String password);
    //确认注册访客重名
    boolean checkguestbyName(String username);
    //访客经验
    void updateguestLevel(Guest guest);
    //新增访客
    Guest saveGuest(Guest guest);
    //管理访客
    Page<Guest> guestList(Pageable pageable);
    //删除访客
    void deleteGuest(Long id);
    //获得访客
    Guest getGuest(Long id);


    /*管理评论*/


    /*访客管理评论*/
    //得到该访客所有评论
//    List<Comment> guestComments(Long id);
}
