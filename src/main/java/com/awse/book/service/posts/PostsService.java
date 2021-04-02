package com.awse.book.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.awse.book.domain.posts.Posts;
import com.awse.book.domain.posts.PostsRepository;
import com.awse.book.web.dto.PostsListResponseDto;
import com.awse.book.web.dto.PostsResponseDto;
import com.awse.book.web.dto.PostsSaveRequestDto;
import com.awse.book.web.dto.PostsUpdateRequestDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * PostsService
 */
@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        
        return new PostsResponseDto(entity);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이없습니다."+id));

        postsRepository.delete(posts);

    }


}