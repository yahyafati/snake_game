package com.yahya.game.snake.view;

import com.yahya.game.snake.controller.AudioController;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static MainFrame MAIN_FRAME;

    private MainFrame() {
        MAIN_FRAME = this;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(820, 800);
        setResizable(false);
        setLocationRelativeTo(null);
//        setUndecorated(true);
        setTitle("Snake");
        try {
            init();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static MainFrame getInstance() {
        if (MAIN_FRAME == null) {
            new MainFrame();
        }
        return MAIN_FRAME;
    }

    void init() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());

        SnakeCanvas canvas = new SnakeCanvas();
        panel.add(canvas, BorderLayout.CENTER);

        AudioController.getInstance(canvas).backgroundPlay();

    }

}
