package com.yahya.game.snake.view;

import com.yahya.game.snake.constants.Colors;

import javax.swing.*;
import java.awt.*;

public class MainGameMenu extends JPanel{

    private final SnakeCanvas canvas;

    public MainGameMenu(SnakeCanvas canvas) {
        this.canvas = canvas;
        setLayout(new GridBagLayout());
        setOpaque(false);
        init();
    }

    void init() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

        buttonsPanel.setLayout(new GridLayout(-1, 1,0,10));

        //    private GameButton generateButton;
        GameButton startGameButton = new GameButton("New Game");
        startGameButton.addActionListener(e -> canvas.getController().startNewGame());
        GameButton highScoreButton = new GameButton("High Scores");
        GameButton aboutButton = new GameButton("About");
        GameButton githubButton = new GameButton("Github");
        GameButton exitButton = new GameButton("Exit");

        buttonsPanel.add(startGameButton);
        buttonsPanel.add(highScoreButton);
        buttonsPanel.add(aboutButton);
        buttonsPanel.add(githubButton);
        buttonsPanel.add(exitButton);

        add(buttonsPanel);
    }
}
