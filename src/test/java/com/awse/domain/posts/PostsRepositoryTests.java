package com.awse.domain.posts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import com.awse.book.domain.posts.Posts;
import com.awse.book.domain.posts.PostsRepository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@RunWith(SpringRunner.class)
@Log4j2
public class PostsRepositoryTests {

    @Autowired
    private PostsRepository postsRepository;

    // 단위 테스트가 끝날때마다 실행하는 메서드를 정해준다.
    // 보통 배포전 전체 테스트를 수행시 테스트간 데이터 침범을 막기위해서 사용한다.

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트게시물";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("wesaq@gmail.com").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        log.info(posts.getTitle().equals(title));
        log.info(posts.getContent().equals(content));
        // assertThat(posts.getTitle()).isEqualsTo(title);
        // assertThat(posts.getCOntent()).isEqualsTo(content);
    }    

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        System.out.println(">>>>>>>>>>>> createDate ="+posts.getCreateDate()+", modifiedDate ="+posts.getModifiedDate());
    
        /*
            assertThat(posts.getCreateDate()).isAfter(now);
            assertThat(posts.getModifiedDate()).isAfter(now);
        */
    }
}
