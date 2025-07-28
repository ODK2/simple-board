package com.example.simpleboard.controller;

import com.example.simpleboard.domain.Post;
import com.example.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostViewController {
    private  final PostService postService;

    @GetMapping("/posts/list")
    public String showPostList(Model model) {
        List<Post> postList = postService.findAll();
        model.addAttribute("posts",postList);
        return "postList"; // -> templates/postList.html
    }

    @GetMapping("/posts/list/{id}")
    public String showPostDetail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음: " + id));
        model.addAttribute("post", post);
        return "postDetail";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음: " + id));
        model.addAttribute("post", post);
        return "postEdit";
    }
}
