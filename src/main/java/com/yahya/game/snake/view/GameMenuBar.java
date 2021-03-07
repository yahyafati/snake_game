package com.yahya.game.snake.view;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.enums.GameState;

import javax.swing.*;
import java.awt.*;

public class GameMenuBar extends JPanel {

    private final SnakeCanvas canvas;

    private ScorePanel scorePanel;

    private InGameMenu inGameMenu;

    private MainGameMenu mainGameMenu;

    public final static int WIDTH = 400;
    public final static int HEIGHT = 550;

    public GameMenuBar(SnakeCanvas canvas) {
        this.canvas = canvas;
        scorePanel = new ScorePanel(canvas);
        inGameMenu = new InGameMenu(canvas);
        mainGameMenu = new MainGameMenu(canvas);

        setSize(WIDTH, HEIGHT);
        setBackground(Colors.SECONDARY_COLOR);
        setOpaque(false);
        init();
    }

    void init() {
        setLayout(new BorderLayout());

        LogoPanel gameLogoPanel = new LogoPanel();


        add(gameLogoPanel, BorderLayout.NORTH);
        add(mainGameMenu, BorderLayout.CENTER);
    }

    public void resetGameMenu() {
        if (canvas.getController().getGameState() == GameState.NOTHING) {
            remove(inGameMenu);
            add(mainGameMenu);
        } else {
            remove(mainGameMenu);
            add(inGameMenu);
        }
    }

    public void resetResumeButton() {
        inGameMenu.resetResumeButton();
    }
}
