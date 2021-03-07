package com.yahya.game.snake.view;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.enums.GameState;

import javax.swing.*;
import java.awt.*;

public class InGameMenu extends JPanel{

    private final SnakeCanvas canvas;

    private GameButton resumeGameButton;

    private final ScorePanel scorePanel;

    public InGameMenu(SnakeCanvas canvas) {
        this.canvas = canvas;
        scorePanel = new ScorePanel(canvas);
        setLayout(new GridBagLayout());
        setOpaque(false);
        init();
    }

    void init() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(-1, 1,0,10));

        resumeGameButton = new GameButton("Resume Game");
        resumeGameButton.addActionListener(e -> canvas.getController().resumeGame());
        GameButton startGameButton = new GameButton("New Game");
        startGameButton.addActionListener(e -> canvas.getController().startNewGame());
        GameButton backToMenuGameButton = new GameButton("Back To Menu");
        backToMenuGameButton.addActionListener(e -> canvas.getController().gotoMainMenu());

        buttonsPanel.add(scorePanel);
        buttonsPanel.add(resumeGameButton);
        buttonsPanel.add(startGameButton);
        buttonsPanel.add(backToMenuGameButton);
        add(buttonsPanel);
    }

    public void resetResumeButton() {
        resumeGameButton.setVisible(canvas.getController().getGameState() == GameState.PAUSED);
    }
}
