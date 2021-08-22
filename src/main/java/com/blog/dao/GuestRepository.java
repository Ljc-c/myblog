package com.blog.dao;

import com.blog.entity.Comment;
import com.blog.entity.Guest;
import com.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest,Long>, JpaSpecificationExecutor<Comment> {
    Guest findByUsernameAndPassword(String username,String password);
    Guest findByUsername(String username);

    @Query("select c from Comment c where c.guest.id like ?1 ")
    List<Comment> findCommentByGuestId(Long id);
}
