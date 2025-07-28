package com.example.simpleboard.service;

import com.example.simpleboard.controller.CommentRestController;
import com.example.simpleboard.domain.Comment;
import com.example.simpleboard.domain.Post;
import com.example.simpleboard.dto.CommentRequest;
import com.example.simpleboard.repository.CommentRepository;
import com.example.simpleboard.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> findByPost(Long id) {
        return commentRepository.findByPostId(id);
    }

    @Transactional
    public void update(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + id));

        comment.setAuthor(request.author());
        comment.setContent(request.content());
    }
}
