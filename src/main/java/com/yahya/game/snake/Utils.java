package com.yahya.game.snake;

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
}
