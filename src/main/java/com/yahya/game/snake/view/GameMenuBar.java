package com.yahya.game.snake.view;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.controller.Utils;
import com.yahya.game.snake.enums.GameState;
import com.yahya.game.snake.enums.ScaleType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameMenuBar extends JPanel {

    private final SnakeCanvas canvas;

    private GameButton resumeGameButton;

    public final static int WIDTH = 400;
    public final static int HEIGHT = 550;

    public GameMenuBar(SnakeCanvas canvas) {
        this.canvas = canvas;
        setSize(WIDTH, HEIGHT);
        setBackground(Colors.SECONDARY_COLOR);
        setOpaque(false);
        init();
    }

    void init() {
        setLayout(new BorderLayout());

        JPanel gameLogoPanel = new JPanel() {
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
        };

        gameLogoPanel.setPreferredSize(new Dimension(getWidth(), 130));

        gameLogoPanel.setOpaque(false);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

        buttonsPanel.setLayout(new GridLayout(-1, 1,0,10));

        resumeGameButton = new GameButton("Resume Game");
        resumeGameButton.addActionListener(e -> canvas.getController().resumeGame());
        resumeGameButton.setVisible(false);

        //    private GameButton generateButton;
        GameButton startGameButton = new GameButton("New Game");
        startGameButton.addActionListener(e -> canvas.getController().startNewGame());
        GameButton highScoreButton = new GameButton("High Scores");
        GameButton aboutButton = new GameButton("About");
        GameButton githubButton = new GameButton("Github");
        GameButton exitButton = new GameButton("Exit");

        buttonsPanel.add(resumeGameButton);
        buttonsPanel.add(startGameButton);
        buttonsPanel.add(highScoreButton);
        buttonsPanel.add(aboutButton);
        buttonsPanel.add(githubButton);
        buttonsPanel.add(exitButton);

        centerPanel.add(buttonsPanel);

        add(gameLogoPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void resetResumeButton() {
        resumeGameButton.setVisible(canvas.getController().getGameState() == GameState.PAUSED);
    }
}
