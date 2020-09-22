package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Invalid amount of arguments. Make sure you enter both arguments in quotes (for now).");
            System.err.println("Syntax: <Message> <File path>");
            System.exit(1);
        }

        int w = 50;
        int h = 50;

        if (args[0].length() > (w*h)) {
            System.err.printf("\nMessage too big (%d Characters). " +
                    "Must be less than %d", args[0].length(), (w*h)+1);
            System.exit(1);
        }

        BufferedImage image = new BufferedImage(w, h , BufferedImage.TYPE_INT_ARGB);

        File file;

        Color pix;

        int totalPixels = 0;

        for (int x = 0; x < w; x++) {

            for (int y = 0; y < h; y++) {

                if (totalPixels >= args[0].length()){
                    break;
                }

                int code = args[0].charAt(totalPixels);

                int r = code;
                int g = code;
                int b = code;

                pix = new Color(r,g,b,255);

                image.setRGB(x, y, pix.getRGB());

                totalPixels++;

            }
        }

        file = new File(args[1]);

        if ( file.createNewFile() ) {
            System.out.println("File created");
        } else {
            System.out.println("File overwritten");
        }

        ImageIO.write(image, "png", file);

    }

}
