package com.mappractice.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.dto.PositionedImageDTO;
import com.mappractice.demo.domain.Location;
import com.mappractice.demo.exception.ImageNotFoundException;
import com.mappractice.demo.exception.RestReponseEntityExceptionHandler;
import com.mappractice.demo.service.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiImageControllerTest {
    private static final String API_IMAGE_URI = "/api/image";

    private Logger log = LoggerFactory.getLogger(ApiImageControllerTest.class);

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ApiImageController apiImageController;

    private MockMvc mockMvc;

    private JacksonTester<PositionedImageDTO> jsonImageDTO;

    private static final MockMultipartFile imageFile = new MockMultipartFile("name", "ded.png", "image/png", "data".getBytes());
    private static final Image image = new Image(1L, "테스트 이미지", "12kl312nlk3".getBytes(), new Location("12.1234567", "12.1234566"));
    private static final PositionedImageDTO positionedImageDTO = new PositionedImageDTO("테스트 이미지", imageFile, "12.1234567", "12.1234566");


    @Before
    public void setup() {
        JacksonTester.initFields(this, ObjectMapper::new);

        mockMvc = MockMvcBuilders.standaloneSetup(apiImageController)
                .setControllerAdvice(new RestReponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void create_성공() throws Exception {
        //given
        when(imageService.create(any(PositionedImageDTO.class))).thenReturn(image);
        //when
        MockHttpServletResponse response =
                mockMvc.perform(multipart(API_IMAGE_URI)
                        .file(imageFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("fileName", "이름입니다.")
                        .param("xIndex", "111")
                        .param("yIndex","113"))
                        .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void delete_성공() throws Exception {
        //given
        when(imageService.delete(isA(Long.class))).thenReturn(image);

        //when
        MockHttpServletResponse response =
                mockMvc.perform(delete(API_IMAGE_URI + "/{id}", 1))
                        .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void delete_not_found_실패() throws Exception {
        //given
        doThrow(new ImageNotFoundException()).when(imageService).delete(isA(Long.class));

        //when
        MockHttpServletResponse response =
                mockMvc.perform(delete(API_IMAGE_URI + "/{id}", 1))
                        .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}