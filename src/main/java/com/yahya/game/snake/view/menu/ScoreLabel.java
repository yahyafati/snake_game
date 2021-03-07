package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.constants.Fonts;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

    private final int score;
    private final int index;

    public ScoreLabel(int index, int score) {
        super(index + ". " + score + "");
        this.score = score;
        this.index = index;

        setSize(new Dimension(GameMenuBar.WIDTH, 50));
        setPreferredSize(new Dimension(GameMenuBar.WIDTH, 50));
        setMinimumSize(new Dimension(GameMenuBar.WIDTH, 50));
        setMaximumSize(new Dimension(GameMenuBar.WIDTH, 50));

        setFont(Fonts.SPACED_EVIL_EMPIRE_FONT().deriveFont(20f));
        setForeground(Colors.TEXT_COLOR);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

//        g2.setColor(getBackground());
//        g2.fillRect(0,0,w,h);

        g2.setColor(getForeground());
        g2.fillRect(0, h-1, w, 1);

        FontMetrics fm = g2.getFontMetrics();
        int startX = 10;
        int startY = (h+ fm.getAscent()) / 2;
        g2.drawString(index + ".", startX, startY);

        startX += fm.stringWidth("88. ");
        g.drawString(score + "", startX, startY);
    }

}
