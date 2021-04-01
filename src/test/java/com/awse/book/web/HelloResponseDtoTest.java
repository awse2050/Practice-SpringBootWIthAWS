package com.awse.book.web;


import static org.junit.Assert.assertThat;
import com.awse.book.web.dto.HelloResponseDto;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int  amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // assertThat(dto.getName()).isEqualsTo(name);
        // assertThat(dto.getAmount()).isEqualsTo(amount);

    }

}
