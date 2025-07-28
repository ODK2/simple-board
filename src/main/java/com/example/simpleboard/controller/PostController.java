package com.example.simpleboard.controller;

import com.example.simpleboard.domain.Post;
import com.example.simpleboard.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //JSON을 반환하는 컨트롤러 (HTML X)
@RequestMapping("/api/posts") //이 클래스의 모든 메서드는 /posts URL 하위에서 동작함
public class PostController {

    //비즈니스 로직을 담당하는 서비스 계층 (DB 연결 등은 이 안에서 처리됨)
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    //모든 게시글을 리스트로 가져와 JSON으로 반환
    @GetMapping
    public List<Post> getAllPosts(){
        return postService.findAll();
    }

    @GetMapping("/{id}") //게시글 단건 조회 (GET /posts/{id})
    public Post getPostById(@PathVariable Long id){ //@PathVariable로 URL 경로에서 id 받아옴
        return postService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글 없음 " + id)); //해당 ID의 게시글 반환, 없으면 예외 발생
    }

    @PostMapping
    //클라이언트가 JSON 형식으로 전송한 게시글 데이터를 @RequestBody로 받아서 저장
    public Post createPost(@RequestBody Post requestDTO) {
        Post post = Post.builder()
                .title(requestDTO.getTitle())
                .writer(requestDTO.getWriter())
                .content(requestDTO.getContent())
                .build();
        return postService.save(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음: " + id));

        post.update(
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getWriter()
        );

        return postService.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
