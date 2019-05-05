package com.mappractice.demo.service;

import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.ImageDTO;
import com.mappractice.demo.domain.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image create(ImageDTO imageDTO) {
        return imageRepository.save(new Image(imageDTO));
    }

    public Image delete(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        imageRepository.delete(image);
        return image;
    }
}
