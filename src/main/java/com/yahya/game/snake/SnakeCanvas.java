package com.yahya.game.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static com.yahya.game.snake.CONSTANTS.*;

public class SnakeCanvas extends JPanel {

    public final int tileSize;
//    private final Color bgColor;

    private Color snakeBodyColor = Color.WHITE;
    private Color snakeHeadColor = Color.RED;
    private Color foodColor = Color.GREEN;

    private TileStatus[][] tileStatuses = null;
    private final Snake snake;

    private GameState gameState = GameState.NOTHING;
    private ScheduledFuture<?> gamePlayer;

    public SnakeCanvas() {
        this(10);
    }

    public SnakeCanvas(int tileSize) {
        this.tileSize = tileSize;
        setBackground(Colors.PRIMARY_COLOR);
        snake = new Snake();
        setKeyBinding();
        setLayout(null);

        GameMenuBar menuBar = new GameMenuBar(this);
        Dimension size = menuBar.getSize();

        int startX = MainFrame.getInstance().getWidth()/2 - size.width/2;
        int startY = MainFrame.getInstance().getHeight()/2 - size.height/2;
        menuBar.setBounds(startX,startY, size.width, size.height);
        add(menuBar);
    }

    int row() {
        return tileStatuses.length;
    }

    int col() {
        return tileStatuses[0].length;
    }

    void setKeyBinding() {
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
;


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), VK_LEFT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), VK_RIGHT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), VK_UP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), VK_DOWN);

        actionMap.put(VK_LEFT, new CanvasListeners(VK_LEFT, this));
        actionMap.put(VK_RIGHT, new CanvasListeners(VK_RIGHT, this));
        actionMap.put(VK_UP, new CanvasListeners(VK_UP, this));
        actionMap.put(VK_DOWN, new CanvasListeners(VK_DOWN, this));
    }


    void generateSnake() {
        snake.getPoints().forEach(point -> {
            tileStatuses[point.y][point.x] = TileStatus.SNAKE_BODY;
        });
        Point headPoint = snake.getPoints().get(0);
        tileStatuses[headPoint.y][headPoint.x] = TileStatus.SNAKE_HEAD;
    }

    void generateMap() {
        int w = getWidth(), h = getHeight();
        int rows = h / tileSize;
        int cols = w / tileSize;
        if (rows > 0 && cols > 0) {
            tileStatuses = new TileStatus[rows][cols];
        } else {
            return;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tileStatuses[i][j] = TileStatus.EMPTY;
            }
        }
    }

    void createSnake() {
        snake.addPoint(11, 5);
        for (int i = 10; i > 0; i--) {
            snake.addPoint(i, 5);
        }
    }
    void generateGame() {
        tileStatuses = null;
        snake.getPoints().clear();
        snake.setDirection(SnakeDirection.EAST);

        generateMap();
//        TODO Create Default Snake
        createSnake();
        generateSnake();
        createFood();
        repaint();
    }

    void createFood() {
        int row, col;
        do {
            int foodPlace = (int) (Math.random() * (row() - 1) * (col()-1));
//        System.out.println(foodPlace);
//        System.out.println("row: " + foodPlace/col());
//        System.out.println("col: " + foodPlace%col());
            row = foodPlace/col();
            col = foodPlace%col();
        } while (tileStatuses[row][col] != TileStatus.EMPTY);
        tileStatuses[row][col] = TileStatus.FOOD;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (tileStatuses == null) {
            return;
        }
        assert false;
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

                if (tileStatuses[i][j] == TileStatus.FOOD) {
                    g2.fillOval(j*tileSize, i*tileSize, tileSize, tileSize);
                } else {
                    g2.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
//                    g2.setColor(bgColor.darker());
//                    g2.drawRect(j*tileSize, i*tileSize, tileSize, tileSize);
                }


            }
        }
    }

    public void moveSnake(MoveDirection direction) {
        switch (direction) {
            case UP -> {
                if (snake.getDirection().ordinal() > 1) {
                    snake.setDirection(SnakeDirection.NORTH);
                }
            } case LEFT -> {
                if (snake.getDirection().ordinal() <= 1) {
                    snake.setDirection(SnakeDirection.WEST);
                }
            } case RIGHT -> {
                if (snake.getDirection().ordinal() <= 1) {
                    snake.setDirection(SnakeDirection.EAST);
                }
            } case DOWN -> {
                if (snake.getDirection().ordinal() > 1) {
                    snake.setDirection(SnakeDirection.SOUTH);
                }
            }
        }
    }

    boolean isInSnake(Point point) {
        return tileStatuses[point.y][point.x] == TileStatus.SNAKE_BODY || tileStatuses[point.y][point.x] == TileStatus.SNAKE_HEAD;
    }

    boolean isSafeTile(Point tile) {
        boolean isInBoard = (tile.y >= 0 && tile.y < row()) && (tile.x >= 0 && tile.x < col());
        if (!isInBoard) {
            System.out.println(tile);
            System.out.println("Row: " + row() + " Cols: " + col());
        }

        boolean isSnake = isInBoard && isInSnake(tile);
        if (isSnake) {
            System.out.println("Is In Snake");
        }

        return !isSnake && isInBoard;
    }

    boolean isFoodTile(Point tile) {
        return tileStatuses[tile.y][tile.x] == TileStatus.FOOD;
    }

    boolean moveForward() {
        Point lastPoint = new Point(snake.getPoints().getLast().getLocation());
        Point snakeHead = snake.getFront();
        if (!isSafeTile(snakeHead)) {
            System.out.println("Not Safe");
            return false;
        }
        boolean isFood = isFoodTile(snakeHead);
        snake.moveForward();
        if (isFood) {
            snake.getPoints().add(lastPoint);
            createFood();
        } else {
            tileStatuses[lastPoint.y][lastPoint.x] = TileStatus.EMPTY;
        }
        generateSnake();
        repaint();
        return true;
    }

    public void startGame() {
        gameState = GameState.PLAYING;
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        gamePlayer = executorService.scheduleAtFixedRate(() -> {
            boolean moved = false;
            if (gameState == GameState.PLAYING) {
                moved = moveForward();
            }
            if (!moved) {
                gameState = GameState.GAME_OVER;
                gamePlayer.cancel(false);
            }
        },0, 100, TimeUnit.MILLISECONDS);
    }

    public GameState startStopGame() {
        if (gameState == GameState.PLAYING) {
            gamePlayer.cancel(false);
            gameState = GameState.PAUSED;
        } else {
            startGame();
        }
        return gameState;
    }

    public Snake getSnake() {
        return snake;
    }
}
