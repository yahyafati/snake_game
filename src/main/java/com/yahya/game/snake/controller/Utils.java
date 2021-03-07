package com.yahya.game.snake.controller;

import com.yahya.game.snake.Main;
import com.yahya.game.snake.enums.ScaleType;
import com.yahya.game.snake.enums.TileStatus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

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

    public static Image getImage(TileStatus tileStatus, int tileSize) {
        String fileName = "/images/";
        switch (tileStatus) {
            case FOOD -> fileName += "FoodApple.png";
            case SNAKE_BODY -> fileName += "SnakeBody.png";
            case SNAKE_HEAD -> fileName += "SnakeHead.png";
        }

        try {
            BufferedImage bImg = ImageIO.read(Main.class.getResourceAsStream(fileName));
//            Dimension scaledDimension = Utils.scaledDimension(((float) bImg.getHeight())/ bImg.getWidth(), 120, ScaleType.HEIGHT_BASED);
            return bImg.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
