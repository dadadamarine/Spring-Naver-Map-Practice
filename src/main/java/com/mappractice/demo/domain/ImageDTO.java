package com.mappractice.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ImageDTO {

    @NotEmpty
    private String name;

    @Lob
    @NotEmpty
    private String content;

    @NotNull
    private BigDecimal xIndex;

    @NotNull
    private BigDecimal yIndex;

    public ImageDTO(String name, String content,String xIndex,String yIndex) {
        this.name = name;
        this.content = content;
        this.xIndex = new BigDecimal(xIndex);
        this.yIndex = new BigDecimal(yIndex);
    }
}
