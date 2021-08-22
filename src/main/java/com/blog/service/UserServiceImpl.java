package com.blog.service;

import com.blog.dao.GuestRepository;
import com.blog.dao.UserRepository;
import com.blog.entity.Comment;
import com.blog.entity.Guest;
import com.blog.entity.User;
import com.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GuestRepository guestRepository;

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    //登录检查用户
    @Override
    public Guest checkguester(String username, String password) {
        Guest guest = guestRepository.findByUsernameAndPassword(username,password);
        return guest;
    }

    //注册检测用户
    @Override
    public boolean checkguestbyName(String username) {
        Guest guest = guestRepository.findByUsername(username);
        if(guest==null){
            return true;
        } else {
            return false;
        }
    }
    //新增访客
    @Transactional
    @Override
    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    //更新用户等级
    @Override
    public void updateguestLevel(Guest guest) {
        int exp = guest.getLevelExp();
        int l = guest.getLevel();
        if(l==6){
            return;}
        if(exp>=0 && exp%10==0){
            guest.setLevel(l+1);
        }
    }

    //访客列表
    @Override
    public Page<Guest> guestList(Pageable pageable) {
        return guestRepository.findAll(pageable);
    }

    //删除访客
    @Transactional
    @Override
    public void deleteGuest(Long id) { guestRepository.deleteById(id);}

    //获得

    @Override
    public Guest getGuest(Long id) {
        return guestRepository.findById(id).orElse(null);
    }
}
