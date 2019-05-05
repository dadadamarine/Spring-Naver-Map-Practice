package com.mappractice.demo.web;

import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.ImageDTO;
import com.mappractice.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
public class ApiImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("")
    public ResponseEntity<Image> create(@RequestBody ImageDTO imageDTO){
        Image image = imageService.create(imageDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(image, headers, HttpStatus.CREATED);
    }



}
