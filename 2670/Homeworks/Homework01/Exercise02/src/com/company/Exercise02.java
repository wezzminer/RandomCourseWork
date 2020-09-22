/* CSM 2670 Exercise Set 1
 *
 * Wessley Alexander
 * 01/24/2020
 *
 * File: Exercise02.java
 * This program prints a hallow 'stairway' of asterisks
 * starting at one to a given number.
 */

package com.company;

public class Exercise02 {

    /**
     * Main receives a user input, tests that it is and integer between
     * 1 and 80, then gives the input to printStarPattern.
     *
     * @param args One user input, an integer between 1 and 80.
     *             This input is how many lines of stars the user wants.
     */
    public static void main(String[] args) {

        int input = 0;

        if (args.length == 0) {
            System.err.println("No inputs given. Program expects one whole number between 1 and 80");
            System.exit(1);
        }

        if (args.length > 1) {
            System.err.println("Too many inputs given. Expected only one.");
            System.exit(1);
        }

        try {
            input = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            System.err.println("Input \"" + args[0] + "\" is not an whole number.");
            System.exit(1);
        }

        if (input > 80 || input < 1) {
            System.err.println("Input \"" + args[0] + "\" is invalid, must be between 1 and 80.");
            System.exit(2);
        }

        printStarPattern(input);

    }

    /**
     * Method prints each line of the pattern up to lineWanted.
     *
     * @param linesWanted This is how many lines of stars the user wants.
     */
    private static void printStarPattern(int linesWanted) {
        int spaces = 0;

        System.out.println("*");

        // 2 is subtracted to adjust for the first and last lines.
        while (spaces < linesWanted - 2) {
            System.out.println("*" +  " ".repeat(spaces) + "*");
            spaces++;
        }

        if (linesWanted != 1) {
            System.out.println("*".repeat(linesWanted));
        }
    }

}
