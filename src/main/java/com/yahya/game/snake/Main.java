package com.yahya.game.snake;

import com.yahya.game.snake.constants.Fonts;
import com.yahya.game.snake.view.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Fonts.REGISTER_FONTS();
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = MainFrame.getInstance();
            mainFrame.setVisible(true);
        });
    }
}
