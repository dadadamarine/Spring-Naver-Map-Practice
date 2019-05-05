package com.mappractice.demo.service;

import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.ImageDTO;
import com.mappractice.demo.domain.ImageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageService imageService;

    @Test
    public void create_성공() {
        //given
        ImageDTO imageDTO = new ImageDTO("테스트 이미지", "12kl312nlk3", "12.1234567", "12.1234566");
        Image image = new Image(imageDTO);
        Image savedImage = new Image(imageDTO);
        when(imageRepository.save(any(Image.class))).thenReturn(savedImage);
        //when
        Image createdImage = imageService.create(imageDTO);
        //then
        assertThat(createdImage.getName()).isEqualTo("테스트 이미지");
    }
}