package com.awse.book.web;


import com.awse.book.web.dto.HelloResponseDto;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int  amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);
         assertThat(dto.getName()).isEqualTo(name);
         assertThat(dto.getAmount()).isEqualTo(amount);

    }

}
