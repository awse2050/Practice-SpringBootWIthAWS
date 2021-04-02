package com.awse.book.web;

import com.awse.book.service.posts.PostsService;
import com.awse.book.web.dto.PostsResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
    
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());        

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id")Long id, Model model) {
        
        PostsResponseDto dto  =postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable("id")Long id) {
        postsService.delete(id);
        return id;
    }

}
