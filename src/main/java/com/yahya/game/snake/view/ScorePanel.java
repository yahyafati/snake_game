package com.yahya.game.snake.view;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.constants.Fonts;
import com.yahya.game.snake.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private final SnakeCanvas canvas;

    public ScorePanel(SnakeCanvas canvas) {
        this.canvas = canvas;
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (isHighScore()) {
            g2.setColor(Color.decode("#FFD700"));
        } else {
            g2.setColor(Colors.TEXT_COLOR);
        }
        g2.setFont(Fonts.SPACED_EVIL_EMPIRE_FONT().deriveFont(26f));

        String caption = "Score: " + getScore();
        FontMetrics fm = g2.getFontMetrics();

        int startX = getWidth() / 2 - fm.stringWidth(caption)/2;
        int startY = (getHeight() + fm.getAscent()) /2;

        g2.drawString(caption, startX, startY);

    }

    public boolean isHighScore() {
        return false;
    }

    public int getScore() {
        return canvas.getScore();
    }
}
