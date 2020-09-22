package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Invalid arguments. Program takes only a file location in quotes.");
            System.exit(1);
        }

        BufferedImage image = ImageIO.read(new File(args[0]));

        int h = image.getHeight(), w = image.getWidth();

        Color pix;

        String dummy = "";

        for (int x = 0; x < w; x++) {

            for (int y = 0; y < h; y++) {

                pix = new Color(image.getRGB(x, y));

                if (pix.getRed() == 0) {
                    break;
                }

                dummy += (char) pix.getRed();

            }
        }

        System.out.println("**START**");
        System.out.println(dummy);
        System.out.println("**END**");

    }

}
