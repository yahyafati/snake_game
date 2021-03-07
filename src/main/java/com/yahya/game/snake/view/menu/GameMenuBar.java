package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.enums.GameState;
import com.yahya.game.snake.view.SnakeCanvas;

import javax.swing.*;
import java.awt.*;

public class GameMenuBar extends JPanel {

    private final SnakeCanvas canvas;

    private ScorePanel scorePanel;

    private InGameMenu inGameMenu;
    private MainGameMenu mainGameMenu;
    private HighScoreMenu highScoreMenu;

    public final static int WIDTH = 400;
    public final static int HEIGHT = 550;

    private static GameMenuBar gameMenuBar;

    public GameMenuBar(SnakeCanvas canvas) {
        gameMenuBar = this;
        this.canvas = canvas;
        scorePanel = new ScorePanel(canvas);

        inGameMenu = new InGameMenu(canvas);
        mainGameMenu = new MainGameMenu(canvas);
        highScoreMenu = new HighScoreMenu(canvas);

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

    public static void setMenu(MenuType menuType) {
        gameMenuBar.remove(1); // FIXME Possible Error in the Future
        switch (menuType) {
            case MAIN_MENU -> gameMenuBar.add(gameMenuBar.mainGameMenu, BorderLayout.CENTER);
            case IN_GAME_MENU -> gameMenuBar.add(gameMenuBar.inGameMenu, BorderLayout.CENTER);
            case HIGH_SCORE_MENU -> gameMenuBar.add(gameMenuBar.highScoreMenu, BorderLayout.CENTER);
        }
        gameMenuBar.repaint();
    }

    public void resetGameMenu() {
        if (canvas.getController().getGameState() == GameState.NOTHING) {
            setMenu(MenuType.MAIN_MENU);
        } else {
            setMenu(MenuType.IN_GAME_MENU);
        }
    }

    public void resetResumeButton() {
        inGameMenu.resetResumeButton();
    }
}
