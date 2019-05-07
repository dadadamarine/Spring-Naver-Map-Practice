package com.mappractice.demo.web;

import com.mappractice.demo.domain.Image;
import com.mappractice.demo.domain.dto.ImageDTO;
import com.mappractice.demo.domain.dto.PositionedImageDTO;
import com.mappractice.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ApiImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("")
    public List<ImageDTO> getList(){
        //TODO : 리스트 가져오기 구현
        return null;
    }

    @PostMapping("")
    public ResponseEntity<Image> create(@ModelAttribute PositionedImageDTO positionedImageDTO){
        Image image = imageService.create(positionedImageDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setLocation(URI.create("/api/image/"+image.getId()));
        return new ResponseEntity<>(image, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Image delete(@PathVariable Long id){
        return imageService.delete(id);
    }



}
