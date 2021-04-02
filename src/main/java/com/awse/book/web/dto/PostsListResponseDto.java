package com.awse.book.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.awse.book.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsListResponseDto {
    

    private Long id;
    private String title, author;

    private LocalDateTime  modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }

}
