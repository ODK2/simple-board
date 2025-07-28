package com.example.simpleboard.controller;

import com.example.simpleboard.domain.Comment;
import com.example.simpleboard.domain.Post;
import com.example.simpleboard.service.CommentService;
import com.example.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostViewController {

    private  final PostService postService;
    private  final CommentService commentService;

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "postList";
    }

    @GetMapping("/new")
    public String newPostForm() {
        return "postForm";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음: " + id));

        // 🔥 댓글 리스트 가져오기
        List<Comment> comments = commentService.findByPost(id);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments); // ✅ 모델에 댓글 추가
        return "postDetail";
    }

    @GetMapping("/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음: " + id));
        model.addAttribute("post", post);
        return "postEdit";
    }
}
