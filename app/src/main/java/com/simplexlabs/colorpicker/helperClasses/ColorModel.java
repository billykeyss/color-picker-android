package com.simplexlabs.colorpicker.helperClasses;

import com.simplexlabs.colorpicker.utils.ColorUtils;

/**
 * Created by Bill on 2017-04-08.
 */
public class ColorModel extends android.graphics.Color {
    private ColorUtils colorUtils;
    private int r, g, b;
    private String hexCode;

    public ColorModel(ColorUtils colorUtils, int r, int g, int b) {
        this.colorUtils = colorUtils;
        this.r = r;
        this.g = g;
        this.b = b;
        this.hexCode = colorUtils.getHexCodeFromRGB(r, g, b);
    }

    public ColorModel(ColorUtils colorUtils, String hexCode) {
        this.colorUtils = colorUtils;
        ColorModel color = colorUtils.getColorObjectFromHexCode(hexCode);
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.hexCode = hexCode;
    }

    public String getColorName() {
        return colorUtils.getColorNameFromRgb(r, g, b);
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public String getHexCode() {
        return hexCode;
    }

    public int getColor() {
        return android.graphics.Color.rgb(r, g, b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorModel color = (ColorModel) o;

        if (r != color.r) return false;
        if (g != color.g) return false;
        if (b != color.b) return false;
        return hexCode != null ? hexCode.equals(color.hexCode) : color.hexCode == null;

    }

    @Override
    public int hashCode() {
        int result = r;
        result = 31 * result + g;
        result = 31 * result + b;
        result = 31 * result + (hexCode != null ? hexCode.hashCode() : 0);
        return result;
    }
}
