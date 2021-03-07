package com.yahya.game.snake;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class Fonts {

    private static boolean registered = false;

    public static final Font EVIL_EMPIRE_FONT = new Font("Evil Empire", Font.PLAIN, 20);

    public static void REGISTER_FONTS() {
        if (registered) {
            return;
        }
        final URL resource = Fonts.class.getResource("/fonts");
        try {
            File file = new File(resource.toURI());
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(font -> {
                try {
                    Font createdFont;
                    ge.registerFont(createdFont = Font.createFont(Font.TRUETYPE_FONT, font));
                    System.out.println(createdFont.getFamily());
                } catch (FontFormatException | IOException e) {
                    e.printStackTrace();
                }
            });
//            Arrays.stream(ge.getAvailableFontFamilyNames()).forEach(System.out::println);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        registered = true;
    }
}