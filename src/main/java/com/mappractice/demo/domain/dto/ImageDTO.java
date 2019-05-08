package com.mappractice.demo.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImageDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private MultipartFile content;

    public ImageDTO(String name, MultipartFile content) {
        this.name = name;
        this.content = content;
    }
}
