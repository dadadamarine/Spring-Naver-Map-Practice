package com.mappractice.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Location {

    @NotNull
    @Column(scale = 7)
    private BigDecimal xIndex;

    @NotNull
    @Column(scale = 7)
    private BigDecimal yIndex;

    public Location(String xIndex, String yIndex){
        this.xIndex = new BigDecimal(xIndex);
        this.yIndex = new BigDecimal(yIndex);
    }

    public Location(float xIndex , float yIndex){
        this.yIndex = BigDecimal.valueOf(xIndex);
        this.yIndex = BigDecimal.valueOf(yIndex);
    }
}
