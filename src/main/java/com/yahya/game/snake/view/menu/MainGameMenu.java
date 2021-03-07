package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.view.SnakeCanvas;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainGameMenu extends Menu{


    public MainGameMenu(SnakeCanvas canvas) {
        super(canvas);
        init();
    }

    void init() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

        buttonsPanel.setLayout(new GridLayout(-1, 1,0,10));

        GameButton startGameButton = new GameButton("New Game");
        startGameButton.addActionListener(e -> getCanvas().getController().startNewGame());
        GameButton highScoreButton = new GameButton("High Scores");
        highScoreButton.addActionListener(e -> GameMenuBar.setMenu(MenuType.HIGH_SCORE_MENU));
        GameButton aboutButton = new GameButton("About");
        aboutButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/navy87/snake_game"));
            } catch (IOException | URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });
//        GameButton githubButton = new GameButton("Github");
        GameButton exitButton = new GameButton("Exit");
        exitButton.addActionListener(e -> getCanvas().getController().exitGame());

        buttonsPanel.add(startGameButton);
        buttonsPanel.add(highScoreButton);
        buttonsPanel.add(aboutButton);
//        buttonsPanel.add(githubButton);
        buttonsPanel.add(exitButton);

        add(buttonsPanel);
    }
}
