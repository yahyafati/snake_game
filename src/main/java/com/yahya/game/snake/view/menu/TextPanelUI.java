package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.constants.Fonts;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;

public class TextPanelUI extends BasicPanelUI {

    private final Color color;
    private final String text;

    public TextPanelUI(String text) {
        this(text, Colors.TEXT_COLOR);
    }

    public TextPanelUI(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        c.setOpaque(false);
//        c.setMaximumSize(new Dimension(-1, 50));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(color);
        g2.setFont(Fonts.SPACED_EVIL_EMPIRE_FONT().deriveFont(26f));

        FontMetrics fm = g2.getFontMetrics();

        int startX = c.getWidth() / 2 - fm.stringWidth(text)/2;
        int startY = (c.getHeight() + fm.getAscent()) /2;

        g2.drawString(text, startX, startY);
    }
}
