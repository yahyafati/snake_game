package com.yahya.game.snake;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(813, 789);
        setResizable(false);
        setLocationRelativeTo(null);
//        setUndecorated(true);
        setTitle("Snake");
        init();
    }

    void init() {
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());

        SnakeCanvas canvas = new SnakeCanvas();
        panel.add(canvas, BorderLayout.CENTER);
        MenuBar menuBar = new MenuBar(canvas);
        panel.add(menuBar, BorderLayout.NORTH);

    }

}
