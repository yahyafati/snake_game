package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.enums.GameState;
import com.yahya.game.snake.view.SnakeCanvas;

import javax.swing.*;
import java.awt.*;

public class InGameMenu extends Menu {

    private GameButton resumeGameButton;

    private final ScorePanel scorePanel;

    private JPanel buttonsPanel;

    public InGameMenu(SnakeCanvas canvas) {
        super(canvas);
        scorePanel = new ScorePanel(canvas);
        init();
    }

    void init() {
        buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(-1, 1,0,10));

        resumeGameButton = new GameButton("Resume Game");
        resumeGameButton.addActionListener(e -> getCanvas().getController().resumeGame());
        GameButton startGameButton = new GameButton("New Game");
        startGameButton.addActionListener(e -> getCanvas().getController().startNewGame());
        GameButton backToMenuGameButton = new GameButton("Back To Menu");
        backToMenuGameButton.addActionListener(e -> getCanvas().getController().gotoMainMenu());

        buttonsPanel.add(scorePanel);
        buttonsPanel.add(resumeGameButton);
        buttonsPanel.add(startGameButton);
        buttonsPanel.add(backToMenuGameButton);
        add(buttonsPanel);
    }

    public void resetResumeButton() {
        if (getCanvas().getController().getGameState() == GameState.PAUSED) {
            buttonsPanel.add(resumeGameButton, 1);
        }else  {
            buttonsPanel.remove(resumeGameButton);
        }
    }
}
