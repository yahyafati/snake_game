package com.yahya.game.snake.view;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

//        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/snake.wav"));
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioInputStream);
//        clip.start();


    }

}
