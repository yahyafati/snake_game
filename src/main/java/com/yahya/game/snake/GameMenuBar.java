package com.yahya.game.snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class GameMenuBar extends JPanel {

    private final SnakeCanvas canvas;

    private GameButton generateButton;
    private GameButton startGameButton;
    private GameButton highScoreButton;
    private GameButton aboutButton;
    private GameButton githubButton;
    private GameButton exitButton;

    public final static int WIDTH = 350;
    public final static int HEIGHT = 500;

    public GameMenuBar(SnakeCanvas canvas) {
        this.canvas = canvas;
        setSize(350, 500);
        setBackground(Colors.SECONDARY_COLOR);
        init();
    }

    void init() {
        setLayout(new BorderLayout());

        JPanel gameLogoPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                BufferedImage bImg = null;
                try {
                    bImg = ImageIO.read(getClass().getResourceAsStream("/images/logo.png"));
                    Dimension scaledDimension = Utils.scaledDimension(((float) bImg.getHeight())/ bImg.getWidth(), 150, ScaleType.HEIGHT_BASED);
                    Image img = bImg.getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
                    Graphics2D g2 = (Graphics2D) g;

                    int startX = getPreferredSize().width/2 - scaledDimension.width/2;
                    int startY = getPreferredSize().height/2 - scaledDimension.height/2;

                    g2.drawImage(img, startX, startY, (img1, infoFlags, x, y, width, height) -> false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        gameLogoPanel.setPreferredSize(new Dimension(getWidth(), 150));

        gameLogoPanel.setOpaque(false);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

        buttonsPanel.setLayout(new GridLayout(-1, 1,0,10));

        startGameButton = new GameButton("New Game");
        startGameButton.addActionListener(e -> startGameButton.setText(canvas.startStopGame().name()));
        highScoreButton = new GameButton("High Scores");
        aboutButton = new GameButton("About");
        githubButton = new GameButton("Github");
        exitButton = new GameButton("Exit");

        buttonsPanel.add(startGameButton);
        buttonsPanel.add(highScoreButton);
        buttonsPanel.add(aboutButton);
        buttonsPanel.add(githubButton);
        buttonsPanel.add(exitButton);

        centerPanel.add(buttonsPanel);

        add(gameLogoPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
