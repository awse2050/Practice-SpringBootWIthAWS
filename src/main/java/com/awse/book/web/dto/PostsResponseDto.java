package com.awse.book.web.dto;

import com.awse.book.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title, content, author;

    // Entity의 일부필드만 사용하므로 생성자를 통해서 받아 필드에 값을 넣어 사용한다.
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
