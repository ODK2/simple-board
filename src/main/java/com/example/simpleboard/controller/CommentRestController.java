package com.example.simpleboard.controller;

import com.example.simpleboard.domain.Comment;
import com.example.simpleboard.domain.Post;
import com.example.simpleboard.dto.CommentRequest;
import com.example.simpleboard.service.CommentService;
import com.example.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;
    private final PostService postService;

    @GetMapping("/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.findByPostId(postId);
    }

    @PostMapping
    public Comment addComment(@RequestBody CommentRequest request) {
        Post post = postService.findById(request.postId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        Comment comment = Comment.builder()
                .author(request.author())
                .content(request.content())
                .post(post)
                .build();

        return commentService.save(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable Long id, @RequestBody CommentRequest request) {
        commentService.update(id, request);
        return ResponseEntity.ok().build();
    }
}

