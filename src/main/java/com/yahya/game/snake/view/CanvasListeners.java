package com.yahya.game.snake.view;

import com.yahya.game.snake.controller.GameController;
import com.yahya.game.snake.enums.MoveDirection;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static com.yahya.game.snake.constants.CONSTANTS.*;

public class CanvasListeners extends AbstractAction {

    private final GameController controller;

    public CanvasListeners(String actionCommand, GameController controller) {
        this.controller = controller;
        putValue(ACTION_COMMAND_KEY, actionCommand);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case VK_LEFT -> this.controller.getKeyEventQueue().add(MoveDirection.LEFT);
            case VK_RIGHT -> this.controller.getKeyEventQueue().add(MoveDirection.RIGHT);
            case VK_UP -> this.controller.getKeyEventQueue().add(MoveDirection.UP);
            case VK_DOWN -> this.controller.getKeyEventQueue().add(MoveDirection.DOWN);
            case VK_ESCAPE -> controller.pauseGame();
        }
    }

}
