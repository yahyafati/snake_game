package com.yahya.game.snake.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scores implements Serializable {

    private List<Integer> scores;

    private static Scores INSTANCE;
    public static boolean isInitialized = false;

    private Scores() {
        scores = new ArrayList<>();
        INSTANCE = this;
    }

    public static Scores getInstance() {
        if (INSTANCE == null) {
            isInitialized = true;
            INSTANCE = new Scores();
        }
        return INSTANCE;
    }

    public static void setInstance(Scores instance) {
        INSTANCE = instance;
    }



    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + scores +
                '}';
    }
}
