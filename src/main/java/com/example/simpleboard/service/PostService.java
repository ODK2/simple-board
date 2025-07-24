package com.example.simpleboard.service;

import com.example.simpleboard.domain.Post;
import com.example.simpleboard.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    //전체조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    //키값으로 조회
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    //저장
    public Post save(Post post) {
        return postRepository.save(post);
    }

    //삭제
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
