package com.yahya.game.snake.controller;

import com.yahya.game.snake.enums.GameState;
import com.yahya.game.snake.enums.MoveDirection;
import com.yahya.game.snake.enums.SnakeDirection;
import com.yahya.game.snake.enums.TileStatus;
import com.yahya.game.snake.view.SnakeCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GameController {

    private final SnakeCanvas canvas;

    private ScheduledFuture<?> gamePlayer;
    private GameState gameState = GameState.NOTHING;

    private final Queue<MoveDirection> keyEventQueue;

    public GameController(SnakeCanvas canvas) {
        this.canvas = canvas;
        getHighScores();
        keyEventQueue = new LinkedList<>();
    }

    void generateSnake() {
        canvas.getSnake().getPoints().forEach(point -> {
            canvas.getTileStatuses()[point.y][point.x] = TileStatus.SNAKE_BODY;
        });
        Point headPoint = canvas.getSnake().getPoints().get(0);
        canvas.getTileStatuses()[headPoint.y][headPoint.x] = TileStatus.SNAKE_HEAD;
    }

    void generateMap() {
        int w = canvas.getWidth(), h = canvas.getHeight();
        int rows = h / canvas.getTileSize();
        int cols = w / canvas.getTileSize();
        if (rows > 0 && cols > 0) {
            canvas.setTileStatuses(new TileStatus[rows][cols]);
        } else {
            return;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                canvas.getTileStatuses()[i][j] = TileStatus.EMPTY;
            }
        }
    }

    void createSnake() {
        canvas.getSnake().addPoint(11, 5);
        for (int i = 10; i > 0; i--) {
            canvas.getSnake().addPoint(i, 5);
        }
    }
    void generateGame() {
        canvas.setTileStatuses(null);
        canvas.getSnake().getPoints().clear();
        canvas.getSnake().setDirection(SnakeDirection.EAST);

        generateMap();
//        TODO Create Default Snake
        createSnake();
        generateSnake();
        createFood();
        canvas.repaint();
    }

    int row() {
        return canvas.getTileStatuses().length;
    }

    int col() {
        return canvas.getTileStatuses()[0].length;
    }

    void createFood() {
        int row, col;
        do {
            int foodPlace = (int) (Math.random() * (row() - 1) * (col()-1));
            row = foodPlace/col();
            col = foodPlace%col();
        } while (canvas.getTileStatuses()[row][col] != TileStatus.EMPTY);
        canvas.getTileStatuses()[row][col] = TileStatus.FOOD;
    }

    public void moveSnake(MoveDirection direction) {
        switch (direction) {
            case UP -> {
                if (canvas.getSnake().getDirection().ordinal() > 1) {
                    canvas.getSnake().setDirection(SnakeDirection.NORTH);
                }
            } case LEFT -> {
                if (canvas.getSnake().getDirection().ordinal() <= 1) {
                    canvas.getSnake().setDirection(SnakeDirection.WEST);
                }
            } case RIGHT -> {
                if (canvas.getSnake().getDirection().ordinal() <= 1) {
                    canvas.getSnake().setDirection(SnakeDirection.EAST);
                }
            } case DOWN -> {
                if (canvas.getSnake().getDirection().ordinal() > 1) {
                    canvas.getSnake().setDirection(SnakeDirection.SOUTH);
                }
            }
        }
    }

    boolean isInSnake(Point point) {
        return canvas.getTileStatuses()[point.y][point.x] == TileStatus.SNAKE_BODY ||canvas.getTileStatuses()[point.y][point.x] == TileStatus.SNAKE_HEAD;
    }

    boolean isSafeTile(Point tile) {
        boolean isInBoard = (tile.y >= 0 && tile.y < row()) && (tile.x >= 0 && tile.x < col());
        return isInBoard && !isInSnake(tile);
    }

    boolean isFoodTile(Point tile) {
        return canvas.getTileStatuses()[tile.y][tile.x] == TileStatus.FOOD;
    }

    boolean moveForward() {
        Point lastPoint = new Point(canvas.getSnake().getPoints().getLast().getLocation());
        Point snakeHead = canvas.getSnake().getFront();
        if (!isSafeTile(snakeHead)) {
            return false;
        }
        boolean isFood = isFoodTile(snakeHead);
        canvas.getSnake().moveForward();
        if (isFood) {
            canvas.getSnake().getPoints().add(lastPoint);
            createFood();
        } else {
           canvas.getTileStatuses()[lastPoint.y][lastPoint.x] = TileStatus.EMPTY;
        }
        generateSnake();
        canvas.repaint();
        return true;
    }

    public void resumeGame() {
        canvas.getMenuBar().setVisible(false);
        gameState = GameState.PLAYING;
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        gamePlayer = executorService.scheduleAtFixedRate(() -> {
            boolean moved = false;
            if (gameState == GameState.PLAYING) {
                MoveDirection direction = getKeyEventQueue().poll();
                if (direction != null) {
                    // TODO Maybe never even add it at first.
                    while (direction == getKeyEventQueue().peek()) {
                        getKeyEventQueue().poll();
                    }
                    moveSnake(direction);
                }
                moved = moveForward();
            }
            if (!moved) {
                gameOver();
            }
        },0, 100, TimeUnit.MILLISECONDS);
    }

    public void gotoMainMenu() {
        gameState = GameState.NOTHING;
        showGameMenu();
    }

    private void showGameMenu() {
        canvas.getMenuBar().resetGameMenu();
        canvas.getMenuBar().resetResumeButton();
        canvas.getMenuBar().setVisible(true);
        canvas.repaint();
    }



    private void gameOver() {
        int score = getScore();

        saveHighScore(score);
        gamePlayer.cancel(false);
        gameState = GameState.GAME_OVER;
        showGameMenu();
    }

    public Scores getHighScores() {
        try (FileInputStream file = new FileInputStream("scores");
             ObjectInputStream in = new ObjectInputStream(file); ) {
            Scores scores = (Scores) in.readObject();
            if(!Scores.isInitialized) {
                Scores.setInstance(scores);
            }
            return scores;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveHighScore(int score) {
        Scores.getInstance().getScores().add(score);
        Scores.getInstance().setScores(
                Scores.getInstance().getScores()
                        .stream()
                        .sorted(Comparator.reverseOrder())
                        .limit(10)
                        .collect(Collectors.toList())
        );
        try(FileOutputStream file = new FileOutputStream("scores");
            ObjectOutputStream out = new ObjectOutputStream(file);)
        {
            out.writeObject(Scores.getInstance());
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void startStopGame() {
        if (gameState == GameState.PLAYING) {
            pauseGame();
        } else {
            resumeGame();
        }
    }

    public void startNewGame() {
        generateGame();
        startStopGame();
    }

    public void pauseGame() {
        gamePlayer.cancel(false);
        gameState = GameState.PAUSED;
        showGameMenu();
    }

    public GameState getGameState() {
        return gameState;
    }

    public Queue<MoveDirection> getKeyEventQueue() {
        return keyEventQueue;
    }

    public int getScore() {
        return canvas.getSnake().getPoints().size();
    }


    public void exitGame() {
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(canvas);
        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
    }
}
