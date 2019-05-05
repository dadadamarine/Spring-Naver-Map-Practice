package com.mappractice.demo.service;

import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.ImageDTO;
import com.mappractice.demo.domain.ImageRepository;
import com.mappractice.demo.domain.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
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

    @Test
    public void delete_성공() {
        //given
        Image image = new Image(1L, "테스트 이미지", "12kl312nlk3", new Location("12.1234567", "12.1234566"));
        when(imageRepository.findById(1l)).thenReturn(Optional.of(image));
        doNothing().when(imageRepository).delete(isA(Image.class));
        //when
        Image createdImage = imageService.delete(1l);
        //then
        assertThat(createdImage.getName()).isEqualTo("테스트 이미지");
    }
}