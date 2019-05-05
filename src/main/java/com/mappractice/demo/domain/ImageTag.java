package com.mappractice.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageTag {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "imageTags")
    private List<Image> imageSet = new ArrayList<>();

}

