package com.example.simpleboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String writer;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
    //페이징시 createdAt이 null이라면 정렬이 이상해질 수 있음 -초기화
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public Post(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}