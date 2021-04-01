package com.awse.book.web;

import com.awse.book.domain.posts.Posts;
import com.awse.book.domain.posts.PostsRepository;
import com.awse.book.web.dto.PostsSaveRequestDto;
import com.awse.book.web.dto.PostsUpdateRequestDto;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
public class PostsApiControllerTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void testDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록() throws Exception {
        String title= "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).author("author").build();

        //port = local로 사용되는 포트번호로 자동지정
        String url = "http://localhost:"+port+"/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        /*
            assertThat(responseEntity.getStatusCode()).isEqualsTo(HttpStatus.OK);
            assertThat(responseEntity.getBody()).isGreaterThan(0L);

            Lists<Posts> all = postsRepository.findAll();
            assertThat(all.get(0).getTitle()).isEqualsTo(title);
            assertThat(all.get(0).getContent()).isEqualsTo(content);
        */
    }

    @Test
    public void Posts_수정() throws Exception {
        Posts savedPosts = postsRepository.save(Posts.builder().title("titel").content("content").author("author").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "contetn2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(expectedTitle).content(expectedContent).build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        /*
            assertThat(responseEntity.getStatusCode()).isEqualsTo(HttpStatus.OK);
            assertThat(responseentity.getBody()).isGreaterThan(0L);

            Lists<Posts> all = postsRepository.findAll();
            assertThat(all.get(0).getTitle()).isEqualsTo(expectedTitle);
            assertThat(all.get(0).getContent()).isEqualsTo(expectedContent);
        */

        
    }

}
