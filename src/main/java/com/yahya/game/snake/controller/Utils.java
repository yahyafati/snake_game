package com.yahya.game.snake.controller;

import com.yahya.game.snake.enums.ScaleType;

import java.awt.*;

public class Utils {

    /**
     * (x+y) % m
     * @param x - x
     * @param y - y
     * @param m - m
     * @return (x+y) % m
     */
    static int mod(int x, int y, int m) {
        int xMod = x % m;
        int yMod = y % m;
        int sign = y > 0 ? 1 : -1;
        return (xMod + sign*yMod) % m;
    }

    public static Dimension scaledDimension(double aspectRatio, int length, ScaleType type) {
        Dimension scaled = new Dimension();
        if (type == ScaleType.HEIGHT_BASED) {
            scaled.height = length;
            scaled.width = (int) (length*aspectRatio);
        } else {
            scaled.height = (int) (aspectRatio/length);
            scaled.width = length;
        }
        return scaled;
    }
}
