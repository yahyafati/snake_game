package com.yahya.game.snake.view.menu;

import com.yahya.game.snake.constants.Colors;
import com.yahya.game.snake.view.SnakeCanvas;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.Comparator;

public class HighScoreMenu extends Menu {

    private JPanel highScorePanel;

    public HighScoreMenu(SnakeCanvas canvas) {
        super(canvas);
        setLayout(new BorderLayout());
        init();
    }

    void init() {
        JPanel highScore = new JPanel();
        highScore.setUI(new TextPanelUI("High scores"));
        highScore.setPreferredSize(new Dimension(getWidth(), 50));
        highScorePanel = new JPanel();
//        highScorePanel.setLayout(new GridLayout(-1, 1, 5,0));
        highScorePanel.setLayout(new BoxLayout(highScorePanel, BoxLayout.Y_AXIS));
        highScorePanel.setOpaque(false);
//        for (int i = 0; i < 10; i++) {
//            highScorePanel.add(new ScoreLabel(i+1, (int) (Math.random()*1000)));
//        }
        JScrollPane scrollPane = new JScrollPane(highScorePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setVisible(false);
                return button;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setVisible(false);
                return button;
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                Color color;
                JScrollBar sb = (JScrollBar) c;
                if (!sb.isEnabled() || r.width > r.height) {
                    return;
                } else if (isDragging) {
                    color = Colors.PRIMARY_COLOR.darker().darker();
                } else if (isThumbRollover()) {
                    color = Colors.PRIMARY_COLOR.darker();
                } else {
                    color = Colors.PRIMARY_COLOR.brighter();
                }
                g2.setPaint(color);
                sb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                g2.fillRoundRect(10, r.y, 7, r.height, 50, 10);
                g2.dispose();
            }
        });
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        GameButton backToMenuGameButton = new GameButton("Back To Menu");
        backToMenuGameButton.addActionListener(e -> getCanvas().getController().gotoMainMenu());

        add(highScore, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(backToMenuGameButton, BorderLayout.SOUTH);
    }

    void populateList() {
        highScorePanel.removeAll();
        getCanvas().getController()
                .getHighScores()
                .getScores()
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(integer -> highScorePanel.add(new ScoreLabel(integer)));
    }
}
