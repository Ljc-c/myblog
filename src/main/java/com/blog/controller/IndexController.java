package com.blog.controller;

import com.blog.config.BlogQuery;
import com.blog.config.NotFoundException;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 6,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlogs(pageable));
        model.addAttribute("types",typeService.listTypeTop(4));
        model.addAttribute("tags",tagService.listTagTop(5));
        model.addAttribute("recommendBlogs",blogService.listBlogTop(3));

        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));

        return "blog";
    }
	
	@PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"updateTime"},
            direction = Sort.Direction.DESC)Pageable pageable,
                         @RequestParam String query,
                         Model model){
        model.addAttribute("page",blogService.listBlogs("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";

    }

    @GetMapping("/footer/newblog")
    public String newblog(Model model){
//        System.out.println("1111111111111111");
        model.addAttribute("newblogs",blogService.listBlogTop(3));
        return "_fragments :: newblogList";
    }

}
