package com.mappractice.demo.service;

import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.dto.PositionedImageDTO;
import com.mappractice.demo.domain.ImageRepository;
import com.mappractice.demo.domain.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

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

    private static MockMultipartFile imageFile = new MockMultipartFile("name","ded.png","image/png", "data".getBytes());
    private static Image image = new Image(1L, "테스트 이미지", "12kl312nlk3".getBytes(), new Location("12.1234567", "12.1234566"));
    private static PositionedImageDTO positionedImageDTO = new PositionedImageDTO("테스트 이미지", imageFile, "12.1234567", "12.1234566");

    @Test
    public void create_성공() {
        //given
        Image image = new Image(positionedImageDTO);
        Image savedImage = new Image(positionedImageDTO);
        when(imageRepository.save(any(Image.class))).thenReturn(savedImage);
        //when
        Image createdImage = imageService.create(positionedImageDTO);
        //then
        assertThat(createdImage.getName()).isEqualTo("테스트 이미지");
    }

    @Test
    public void delete_성공() {
        //given
        when(imageRepository.findById(1l)).thenReturn(Optional.of(image));
        doNothing().when(imageRepository).delete(isA(Image.class));
        //when
        Image createdImage = imageService.delete(1l);
        //then
        assertThat(createdImage.getName()).isEqualTo("테스트 이미지");
    }
}