package com.yahya.game.snake;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame MAIN_FRAME;

    private MainFrame() {
        MAIN_FRAME = this;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(813, 789);
        setResizable(false);
        setLocationRelativeTo(null);
//        setUndecorated(true);
        setTitle("Snake");
        init();
    }

    public static MainFrame getInstance() {
        if (MAIN_FRAME == null) {
            new MainFrame();
        }
        return MAIN_FRAME;
    }

    void init() {
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());

        SnakeCanvas canvas = new SnakeCanvas();
        panel.add(canvas, BorderLayout.CENTER);
//        GameMenuBar gameMenuBar = new GameMenuBar(canvas);
//        panel.add(gameMenuBar, BorderLayout.NORTH);

    }

}
