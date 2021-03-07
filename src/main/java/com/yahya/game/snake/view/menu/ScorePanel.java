package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.constants.Fonts;
import com.yahya.game.snake.controller.GameController;
import com.yahya.game.snake.view.SnakeCanvas;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private final SnakeCanvas canvas;

    public ScorePanel(SnakeCanvas canvas) {
        this.canvas = canvas;
        setOpaque(false);
        setUI(new TextPanelUI("Score: " + getScore()));
    }

    public boolean isHighScore() {
        return false;
    }

    public int getScore() {
        return canvas.getScore();
    }
}
