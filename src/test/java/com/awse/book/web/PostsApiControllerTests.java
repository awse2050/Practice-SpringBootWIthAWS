package com.awse.book.web;

import com.awse.book.domain.posts.Posts;
import com.awse.book.domain.posts.PostsRepository;
import com.awse.book.web.dto.PostsSaveRequestDto;
import com.awse.book.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

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

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mvc;

    @org.junit.Before
    public void setup() {
        // springSecurity()가 에러난다 
        // mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()).build();
    }


    @After
    public void testDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Posts_등록() throws Exception {
         //given
         String title = "title";
         String content = "content";
         PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                 .title(title)
                 .content(content)
                 .author("author")
                 .build();
 
         String url = "http://localhost:" + port + "/api/v1/posts";
 
         //when
         mvc.perform(post(url)
                 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .content(new ObjectMapper().writeValueAsString(requestDto)))
                 .andExpect(status().isOk());
 
         //then
         List<Posts> all = postsRepository.findAll();
         assertThat(all.get(0).getTitle()).isEqualTo(title);
         assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Posts_수정() throws Exception {
        Posts savedPosts = postsRepository.save(Posts.builder().title("titel").content("content").author("author").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "contetn2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(expectedTitle).content(expectedContent).build();

        String url = "http://localhost:"+port+"/api/v1/posts"+updateId;

            //when
            mvc.perform(put(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isOk());

            List<Posts> all = postsRepository.findAll();
            assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
            assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        

        
    }

}
