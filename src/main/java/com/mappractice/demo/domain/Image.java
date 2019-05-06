package com.mappractice.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Lob
    private byte[] content;

    @Embedded
    private Location location;

    @Column
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(name = "image_tags",
            joinColumns = {@JoinColumn(name = "image_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<ImageTag> imageTags = new HashSet<>();

    public Image(String name, byte[] content, Location location) {
        this.name = name;
        this.content = content;
        this.location = location;
    }

    public Image(Long id, String name, byte[] content, Location location) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.location = location;
    }

    public Image(ImageDTO imageDTO) {
        this.name=imageDTO.getName();
        try {
            this.content = imageDTO.getContent().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Location inputedlocation = new Location(imageDTO.getXIndex(), imageDTO.getYIndex());
        this.location = inputedlocation;
    }
}
