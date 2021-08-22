package com.blog.service;

import com.blog.config.BlogQuery;
import com.blog.entity.Blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);
    Page<Blog> listBlogs(Pageable pageable,BlogQuery blog); //管理index

    

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
	
	
	Page<Blog> listBlogs(Pageable pageable);  //前端index
    List<Blog> listBlogTop(Integer size);//前端推荐
	Page<Blog> listBlogs(String query,Pageable pageable);  //前端search
    Blog getAndConvert(Long id);   //text  -> markdown
    Page<Blog> listBlogs(Long tagId,Pageable pageable);  //前端标签
    //归档
    Map<String,List<Blog>> archiveBlog();

    Long countBlog();
}
