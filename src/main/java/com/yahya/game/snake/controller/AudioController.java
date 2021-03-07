package com.yahya.game.snake.controller;

import com.yahya.game.snake.view.SnakeCanvas;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

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
            InputStream audioSrc = getClass().getResourceAsStream("/audio/snake.wav");
            //add buffer for mark/reset support
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);

            Clip backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgClipControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            bgClipControl.setValue(-15f);
            backgroundClip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void play(String fileName) {

        try {
            InputStream audioSrc = getClass().getResourceAsStream("/audio/" + fileName);
            //add buffer for mark/reset support
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
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

    public void gameover() {
        play("gameover.wav");
//        try {
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/gameover.wav"));
//            Clip eatingClip = AudioSystem.getClip();
//            eatingClip.open(audioInputStream);
//            eatingClip.start();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }



}
