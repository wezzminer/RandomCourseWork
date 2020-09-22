/*
 * CSM 2670 Exercise set 2
 *
 * Name: Wessley Alexander
 * File: Exercise04.java
 *
 * Program simulates a random walk across an x-y plane. Ends when the coords reach (0,0).
 * May last forever.
 */

package com.company;

import java.util.Random;

public class Exercise04 {

    /**
     * Starts the run.
     *
     * @param args
     */
    public static void main(String[] args) {

        startRun();

    }

    /**
     * This does a random walk across an x-y plane. May last forever.
     */
    private static void startRun() {

        int x = 0;
        int y = 0;
        Random rand = new Random();

        System.out.print("***START***\n(0,0)\n");

        while (true) {

            switch (rand.nextInt(4)) {
                case 0: x += 1;
                        break;
                case 1: x -= 1;
                        break;
                case 2: y += 1;
                        break;
                case 3: y -=1;
                        break;
            }

            System.out.printf("(%d,%d)\n", x, y);

            if (x == 0 && y == 0) {
                System.out.println("***END***");
                System.exit(0);
            }

        }

    }
}
