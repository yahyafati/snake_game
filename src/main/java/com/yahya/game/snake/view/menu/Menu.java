package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.view.SnakeCanvas;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    private final SnakeCanvas canvas;

    public Menu(SnakeCanvas canvas) {
        this.canvas = canvas;
        setLayout(new GridBagLayout());
        setOpaque(false);
    }

    public SnakeCanvas getCanvas() {
        return canvas;
    }
}
