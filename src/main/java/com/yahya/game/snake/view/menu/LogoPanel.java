package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.controller.Utils;
import com.yahya.game.snake.enums.ScaleType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LogoPanel extends JPanel {

    public LogoPanel() {
        setPreferredSize(new Dimension(GameMenuBar.WIDTH, 130));
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        BufferedImage bImg;
        try {
            bImg = ImageIO.read(getClass().getResourceAsStream("/images/logo.png"));
            Dimension scaledDimension = Utils.scaledDimension(((float) bImg.getHeight())/ bImg.getWidth(), 120, ScaleType.HEIGHT_BASED);
            Image img = bImg.getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
            Graphics2D g2 = (Graphics2D) g;

            int startX = getPreferredSize().width/2 - scaledDimension.width/2;
            int startY = getPreferredSize().height/2 - scaledDimension.height/2;

            g2.drawImage(img, startX, startY, (img1, infoFlags, x, y, width, height) -> false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
