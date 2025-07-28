package com.example.simpleboard.dto;

public record CommentRequest(
        Long postId,
        String author,
        String content
) {}