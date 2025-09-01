package com.example.tintory.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import java.util.Arrays;

@Embeddable
@Getter
@ToString
@NoArgsConstructor
public class Color {

    @Column(name = "hex_code", length = 7, nullable = false)
    private  String hexCode;

    public Color(String hexCode) {
        this.hexCode = hexCode;
        validate();
    }

    public void setHexCode(String hexCode) {
        if (hexCode == null || !hexCode.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")) {
            throw new IllegalArgumentException("Invalid hex color code: " + hexCode);
        }

        this.hexCode = hexCode;
    }

    @PrePersist
    @PreUpdate
    private void validate() {
        if (hexCode == null || !hexCode.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")) {
            throw new IllegalArgumentException("Invalid hex color code: " + hexCode);
        }
    }

    public static Color of(String hexCode) {
        return new Color(hexCode);
    }

    /**
     * <p>Конвертирует RGB цвет в HEX</p>
     *
     * @param rgb int[] цвет в RGB (пример: color={255, 255, 255})
     * @return String цвет в HEX
     */
    public static String convertRgbToHex(int[] rgb) {
        if (rgb.length != 3) {
            throw new IllegalArgumentException("Invalid rgb color: " + Arrays.toString(rgb));
        }

        return String.format("#%02x%02x%02x", rgb[0], rgb[1], rgb[2]);
    }
}
