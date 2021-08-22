package com.blog.service;

import com.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);

    //获得某个访客评论
    List<Comment> listCommentsByGuestId(Long guestId);

    List<Comment> listComments();
}
