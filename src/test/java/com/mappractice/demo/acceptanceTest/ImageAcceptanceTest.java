package com.mappractice.demo.acceptanceTest;

import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.Location;
import com.mappractice.demo.domain.dto.PositionedImageDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageAcceptanceTest extends AcceptanceTest {
    private static final String API_IMAGE_URI = "/api/image";

    private static MockMultipartFile content;
    private static Image image;
    private static PositionedImageDTO positionedImageDTO;

    @BeforeClass
    public static void init(){
        content = new MockMultipartFile("name",
                "ded.png",
                "image/png",
                "datdedadsdwdssdwa".getBytes()
        );
        image = new Image(1L, "테스트 이미지", "12kl312nlk3".getBytes(), new Location("12.1234567", "12.1234566"));
        positionedImageDTO = new PositionedImageDTO("테스트 이미지", content, "12.1234567", "12.1234566");
    }

    @Test
    public void 이미지_생성_API_성공() {
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
        parameters.add("fileName", "테스트 이미지");
        parameters.add("file", content);
        parameters.add("xIndex", "123");
        parameters.add("yIndex", "454");

        ResponseEntity<Image> responseEntity = sendFile(API_IMAGE_URI, parameters, Image.class);
        assertThat(responseEntity.getBody().getName()).isEqualTo("테스트 이미지");
    }

    @Test
    public void 이미지_리스트_가져오기_API_성공() {
        //given
        String location = createResoure(API_IMAGE_URI, positionedImageDTO);
        //when
        ResponseEntity<List> responseEntity = sendGet(API_IMAGE_URI, List.class);
        //then
        assertThat(responseEntity.getBody().size()).isEqualTo(1);
    }

    @Test
    public void 이미지_삭제_API_성공() {
        //given
        String location = createResoure(API_IMAGE_URI, positionedImageDTO);
        //when
        ResponseEntity<String> responseEntity = sendDelete(location, String.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
