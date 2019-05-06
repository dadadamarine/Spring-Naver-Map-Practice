package com.mappractice.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImageDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private MultipartFile content;

    @NotNull
    private String xIndex;

    @NotNull
    private String yIndex;

    public ImageDTO(String name, MultipartFile content,String xIndex,String yIndex) {
        this.name = name;
        this.content = content;
        this.xIndex = xIndex;
        this.yIndex = yIndex;

    }
}
