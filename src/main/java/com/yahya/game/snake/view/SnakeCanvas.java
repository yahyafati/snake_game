package com.yahya.game.snake.view;

import com.yahya.game.snake.controller.Snake;
import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.constants.Fonts;
import com.yahya.game.snake.controller.GameController;
import com.yahya.game.snake.controller.Utils;
import com.yahya.game.snake.enums.TileStatus;
import com.yahya.game.snake.view.menu.GameMenuBar;
import com.yahya.game.snake.view.menu.InGameMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import static com.yahya.game.snake.constants.CONSTANTS.*;

public class SnakeCanvas extends JPanel {

    public final int tileSize;
//    private final Color bgColor;

    private final Color snakeBodyColor = Color.WHITE;
    private final Color snakeHeadColor = Color.RED;
    private final Color foodColor = Color.GREEN;

    private TileStatus[][] tileStatuses = null;
    private final Snake snake;

    private final GameController controller;

    private final GameMenuBar menuBar;
    private final InGameMenu inGameMenu;

    public SnakeCanvas() {
        this(20);
    }

    public SnakeCanvas(int tileSize) {
        this.tileSize = tileSize;
        controller = new GameController(this);
        setBackground(Colors.PRIMARY_COLOR);
        snake = new Snake();
        setKeyBinding();
        setLayout(null);

        menuBar = new GameMenuBar(this);
        inGameMenu = new InGameMenu(this);
        Dimension size = menuBar.getSize();

        int startX = MainFrame.getInstance().getWidth()/2 - size.width/2;
        int startY = MainFrame.getInstance().getHeight()/2 - size.height/2;
        menuBar.setBounds(startX,startY, size.width, size.height);
        add(menuBar);
    }

    public TileStatus[][] getTileStatuses() {
        return tileStatuses;
    }

    public void setTileStatuses(TileStatus[][] tileStatuses) {
        this.tileStatuses = tileStatuses;
    }

    public int getTileSize() {
        return tileSize;
    }

    void setKeyBinding() {
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), VK_LEFT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), VK_RIGHT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), VK_UP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), VK_DOWN);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), VK_ESCAPE);

        actionMap.put(VK_LEFT, new CanvasListeners(VK_LEFT, controller));
        actionMap.put(VK_RIGHT, new CanvasListeners(VK_RIGHT, controller));
        actionMap.put(VK_UP, new CanvasListeners(VK_UP, controller));
        actionMap.put(VK_DOWN, new CanvasListeners(VK_DOWN, controller));
        actionMap.put(VK_ESCAPE, new CanvasListeners(VK_ESCAPE, controller));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);



        switch (controller.getGameState()) {
            case PLAYING -> {
                if (tileStatuses == null) {
                    return;
                }
                int rows = tileStatuses.length;
                int cols = tileStatuses[0].length;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {



                        switch (tileStatuses[i][j]) {
                            case EMPTY -> g2.setColor(Colors.PRIMARY_COLOR);
                            case SNAKE_HEAD -> g2.setColor(snakeHeadColor);
                            case SNAKE_BODY -> g2.setColor(snakeBodyColor);
                            case FOOD -> g2.setColor(foodColor);
                        }

                        if (tileStatuses[i][j] == TileStatus.EMPTY) {
                            g2.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
//                            g2.setColor(Color.white);
//                            g2.drawRect(j*tileSize, i*tileSize, tileSize, tileSize);
                        } else {
                            Image img = Utils.getImage(tileStatuses[i][j], tileSize);
                            g2.drawImage(img, j * tileSize, i * tileSize, tileSize, tileSize,
                                    (img1, infoFlags, x, y, width, height) -> false);
                        }


//                        if (tileStatuses[i][j] == TileStatus.FOOD) {
//                            g2.fillOval(j*tileSize, i*tileSize, tileSize, tileSize);
//                        } else {
//                            g2.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
//                        }
                    }
                }
                g2.setFont(Fonts.SPACED_EVIL_EMPIRE_FONT().deriveFont(50f));
                g2.setColor(Colors.SCORE_COLOR);
                String score = getScore() + "";
                g2.drawString(score, getWidth() - g2.getFontMetrics().stringWidth(score) - 30, 50);
            } case PAUSED, NOTHING, GAME_OVER -> {
                String caption = "";
                switch (controller.getGameState()) {
                    case PAUSED -> caption = "PAUSED";
                    case NOTHING -> caption = "Snake";
                    case GAME_OVER -> caption = "Game Over";
                }
                g2.setFont(Fonts.SPACED_EVIL_EMPIRE_FONT().deriveFont(100f));
                g2.setColor(Colors.TEXT_COLOR);

                FontMetrics fm = g2.getFontMetrics();
                int startX = getWidth()/2 - fm.stringWidth(caption)/2;


                g2.drawString(caption, startX, 100);
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public GameMenuBar getMenuBar() {
        return menuBar;
    }

    public GameController getController() {
        return this.controller;
    }

    public int getScore() {
        return snake.getPoints().size();
    }
}
