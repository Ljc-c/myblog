package com.blog.service;

import com.blog.entity.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.*;
import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);//新增

    Tag getTag(Long id);//查询

    Tag getTagByName(String name);//根据名称查询

    Page<Tag> listTag(Pageable pageable);//分页查询

    Tag updateTag(Long id, Tag tag);//更新

    void deleteTag(Long id);//删除

    List<Tag> listTag();
    List<Tag> listTag(String ids);
    List<Tag> listTagTop(Integer size);//前端

}
