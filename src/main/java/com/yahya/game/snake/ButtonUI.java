package com.yahya.game.snake;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonUI extends MetalButtonUI implements java.io.Serializable,
        MouseListener, KeyListener {

    private final static ButtonUI m_buttonUI = new ButtonUI();

    public static ButtonUI getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ButtonUI();
//        }
        return m_buttonUI;
    }


    protected Border m_borderRaised = UIManager.getBorder("Button.border");

    protected Border m_borderLowered = UIManager
            .getBorder("Button.borderPressed");

    protected Color m_backgroundNormal = Colors.PRIMARY_COLOR;

    protected Color m_backgroundPressed = Colors.PRIMARY_COLOR.brighter().brighter();

    protected Color m_backgroundOver = Colors.PRIMARY_COLOR.brighter();

    protected Color m_foregroundNormal = Colors.TEXT_COLOR;

    protected Color m_foregroundActive = Colors.TEXT_COLOR.brighter();

    protected Color m_focusBorder = UIManager.getColor("Button.focusBorder");

    public static ComponentUI createUI(JComponent c) {
        return m_buttonUI;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton btn = (AbstractButton) c;
        btn.setBackground(m_backgroundNormal);
        btn.setForeground(m_foregroundNormal);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setVerticalAlignment(SwingConstants.CENTER);

        btn.setBorder(new LineBorder(m_backgroundNormal,0));

        btn.addMouseListener(this);
        btn.addKeyListener(this);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        c.removeMouseListener(this);
        c.removeKeyListener(this);
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
//        super.paintFocus(g, b, viewRect, textRect, iconRect);
        b.setBackground(m_backgroundPressed);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        JButton b = (JButton) c;
        Graphics2D g2d = (Graphics2D) g;
        Dimension d = b.getSize();

        c.setFont(Fonts.EVIL_EMPIRE_FONT);
        g2d.setFont(c.getFont());
        FontMetrics fm = g2d.getFontMetrics();


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(b.getForeground());
        String caption = b.getText();
        int x = (d.width - fm.stringWidth(caption)) / 2;
        int y = (d.height + fm.getAscent()) / 2;
        g2d.drawString(caption, fm.getAscent(), y);
//        super.paintText(g, c, b.getBounds(), caption);
    }


    @Override
    public Dimension getPreferredSize(JComponent c) {
        AbstractButton btn = (AbstractButton) c;
        Dimension d = btn.getPreferredSize();
        if (m_borderRaised != null) {
            Insets ins = m_borderRaised.getBorderInsets(c);
            d.setSize(d.width + ins.left + ins.right, d.height + ins.top
                    + ins.bottom);
        }
        return d;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
//        c.setBorder(m_borderLowered);
        c.setBackground(m_backgroundPressed);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
//        c.setBorder(m_borderRaised);
        c.setBackground(m_backgroundNormal);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        c.setForeground(m_foregroundActive);
        c.setBackground(m_backgroundOver);
        c.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        c.setForeground(m_foregroundNormal);
        c.setBackground(m_backgroundNormal);
        c.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
            JComponent c = (JComponent) e.getComponent();
//            c.setBorder(m_borderLowered);
            c.setBackground(m_backgroundPressed);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
            JComponent c = (JComponent) e.getComponent();
//            c.setBorder(m_borderRaised);
            c.setBackground(m_backgroundNormal);
        }
    }

}