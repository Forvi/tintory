package com.example.tintory.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "palettes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaletteEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "palette_color",
            joinColumns = @JoinColumn(name = "palette_id")
    )
    private List<Color> colors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
