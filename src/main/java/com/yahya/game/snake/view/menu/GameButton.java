package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.view.menu.ButtonUI;
import com.yahya.game.snake.view.menu.GameMenuBar;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {

    public GameButton() {
        this(null, null);
    }

    public GameButton(String text) {
        this(text, null);
    }

    public GameButton(String text, Icon icon) {
        super(text, icon);
        setPreferredSize(new Dimension(GameMenuBar.WIDTH - 50, 50));
        setUI(ButtonUI.getInstance());
    }

    void setPreferredWidth(int width) {
        setPreferredSize(new Dimension(width, getPreferredSize().height));
    }

    void setPreferredHeight(int height) {
        setPreferredSize(new Dimension(getPreferredSize().width, height));
    }

}
