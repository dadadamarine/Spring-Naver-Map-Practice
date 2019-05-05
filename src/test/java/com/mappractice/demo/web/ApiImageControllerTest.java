package com.mappractice.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.ImageDTO;
import com.mappractice.demo.domain.Location;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class ApiImageControllerTest {
    private static final String API_IMAGE_URI = "/api/image";

    private Logger log = LoggerFactory.getLogger(ApiImageControllerTest.class);

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ApiImageController apiImageController;

    private MockMvc mockMvc;

    private JacksonTester<ImageDTO> jsonImageDTO;

    @Before
    public void setup(){
        JacksonTester.initFields(this , ObjectMapper::new);

        mockMvc = MockMvcBuilders.standaloneSetup(apiImageController).build();
    }

    @Test
    public void create_성공() throws Exception {
        //given
        Image image = new Image(1L, "테스트 이미지", "12kl312nlk3", new Location("12.1234567", "12.1234566"));
        ImageDTO imageDTO = new ImageDTO("테스트 이미지", "12kl312nlk3", "12.1234567", "12.1234566");
        when(imageService.create(any(ImageDTO.class))).thenReturn(image);

        //when
        MockHttpServletResponse response =
                mockMvc.perform(post(API_IMAGE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonImageDTO.write(imageDTO).getJson()))
                        .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void delete_성공() throws Exception {
        //given
        Image image = new Image(1L, "테스트 이미지", "12kl312nlk3", new Location("12.1234567", "12.1234566"));
        when(imageService.delete(isA(Long.class))).thenReturn(image);

        //when
        MockHttpServletResponse response =
                mockMvc.perform(delete(API_IMAGE_URI+"/{id}",1))
                        .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}