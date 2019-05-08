package com.mappractice.demo.domain.dto;

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
public class PositionedImageDTO {

    @NotEmpty
    private String fileName;

    @NotEmpty
    private MultipartFile file;

    @NotNull
    private String xIndex;

    @NotNull
    private String yIndex;

    public PositionedImageDTO(String fileName, MultipartFile file, String xIndex, String yIndex) {
        this.fileName = fileName;
        this.file = file;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

}
