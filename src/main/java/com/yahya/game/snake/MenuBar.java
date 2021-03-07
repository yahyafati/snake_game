package com.yahya.game.snake;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {

    private final SnakeCanvas canvas;

    private JButton generateButton;
    private JButton moveUp;
    private JButton moveLeft;
    private JButton moveDown;
    private JButton moveRight;
    private JButton startGame;

    public MenuBar(SnakeCanvas canvas) {
        this.canvas = canvas;
        init();
    }

    void init() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> canvas.generateGame());

        moveUp = new JButton("Front");
        moveUp.addActionListener(e -> canvas.moveForward());

        moveLeft = new JButton("Left");
        moveLeft.addActionListener(e -> canvas.moveSnake(MoveDirection.LEFT));

        moveDown = new JButton("Back");
        moveDown.addActionListener(e -> canvas.moveSnake(MoveDirection.DOWN));

        moveRight = new JButton("Right");
        moveRight.addActionListener(e -> canvas.moveSnake(MoveDirection.RIGHT));

        startGame = new JButton("Start Game");
        startGame.addActionListener(e -> startGame.setText(canvas.startStopGame().name()));



        add(generateButton);
        add(moveUp);
        add(moveLeft);
        add(moveDown);
        add(moveRight);
        add(startGame);
    }
}
