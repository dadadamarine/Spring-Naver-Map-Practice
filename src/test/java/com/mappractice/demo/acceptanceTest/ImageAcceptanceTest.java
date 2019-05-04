package com.mappractice.demo.acceptanceTest;

import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.ImageDTO;
import com.mappractice.demo.domain.Location;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageAcceptanceTest extends AcceptanceTest {
    private static final String API_IMAGE_URI = "/api/image";

    @Test
    public void 이미지_생성_API_테스트_성공() {
        Image image = new Image(1L, "테스트 이미지", "12kl312nlk3", new Location("12.1234567", "12.1234566"));
        ImageDTO imageDTO = new ImageDTO("테스트 이미지", "12kl312nlk3", "12.1234567", "12.1234566");
        ResponseEntity<Image> responseEntity = sendPost(API_IMAGE_URI, imageDTO, Image.class);
        assertThat(responseEntity.getBody().getName()).isEqualTo("테스트 이미지");

    }

    @Test
    public void 이미지_리스트_가져오기_API_테스트_성공() {
        //given
        ImageDTO imageDTO = new ImageDTO("테스트 이미지", "12kl312nlk3", "12.1234567", "12.1234566");
        String location = createResoure(API_IMAGE_URI, imageDTO);

        //when
        ResponseEntity<List> responseEntity = sendGet(API_IMAGE_URI, List.class);

        //then
        assertThat(responseEntity.getBody().size()).isEqualTo(1);
    }

    @Test
    public void 이미지_삭제_API_테스트_성공() {
        //given
        ImageDTO imageDTO = new ImageDTO("테스트 이미지", "12kl312nlk3", "12.1234567", "12.1234566");
        String location = createResoure(API_IMAGE_URI, imageDTO);
        //when
        ResponseEntity<String> responseEntity = sendDelete(location, String.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
