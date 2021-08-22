package com.blog.dao;

import com.blog.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> , JpaSpecificationExecutor<Comment> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    @Query("select c from Comment c where c.gid = ?1 ")
    List<Comment> findCommentsByGidNull(Long gId);

//    @Transactional
//    @Modifying
//    @Query("select c from Comment c where c.guest.id = ?1 ")
//    List<Comment> findCommentsByGuestId(Long guestId);

    @Query("select c from Comment c where c.guest.id =?1")
    List<Comment> findByGuestIdNull(Long guestId,Sort sort);
//    List<Comment> findByGuestId(Long guestId,Sort sort);
}
