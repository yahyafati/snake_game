package com.yahya.game.snake;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static com.yahya.game.snake.CONSTANTS.*;

public class CanvasListeners extends AbstractAction {

    private final SnakeCanvas canvas;

    public CanvasListeners(String actionCommand, SnakeCanvas canvas) {
        this.canvas = canvas;
        putValue(ACTION_COMMAND_KEY, actionCommand);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case VK_LEFT -> canvas.moveSnake(MoveDirection.LEFT);
            case VK_RIGHT -> canvas.moveSnake(MoveDirection.RIGHT);
            case VK_UP -> canvas.moveSnake(MoveDirection.UP);
            case VK_DOWN -> canvas.moveSnake(MoveDirection.DOWN);
        }
        System.out.println(canvas.getSnake().getDirection());
    }

//
//    public CanvasListeners(SnakeCanvas canvas) {
//        this.canvas = canvas;
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//        System.out.println(e);
//        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            canvas.moveSnake(MoveDirection.LEFT);
//        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            canvas.moveSnake(MoveDirection.RIGHT);
//        }
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_UP) {
//            canvas.moveForward();
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
}
