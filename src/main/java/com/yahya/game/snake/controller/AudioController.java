package com.yahya.game.snake.controller;

import com.yahya.game.snake.view.SnakeCanvas;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;

public class AudioController {

    private final SnakeCanvas canvas;

    private static AudioController INSTANCE;

    private FloatControl bgClipControl;

    private AudioController(SnakeCanvas canvas) {
        INSTANCE = this;
        this.canvas = canvas;
        backgroundPlay();
    }

    public static AudioController getInstance(SnakeCanvas canvas) {
        if (INSTANCE == null) {
            new AudioController(canvas);
        }
        return INSTANCE;
    }

    public void backgroundPlay() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/snake.wav"));
            Clip backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            bgClipControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            bgClipControl.setValue(-15f);
            backgroundClip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void eat() {
//        try {
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/eat.wav"));
//            Clip eatingClip = AudioSystem.getClip();
//            eatingClip.open(audioInputStream);
//            eatingClip.start();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        Toolkit.getDefaultToolkit().beep();
    }


}
