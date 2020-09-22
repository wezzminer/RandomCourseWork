/* CSM 2670 Exercise Set 1
 *
 * Wessley Alexander
 * 01/22/2020
 *
 * file: Exercise01.java
 * This program prints a 'stairway' of asterisks
 * starting at one to a given number.
 */

package com.company;

public class Exercise01 {

    /**
     * Main receives a user input, tests that it is and integer between
     * 1 and 80, then gives the input to printStars.
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

        printStars(input);

    }

    /**
     * Method prints each line up to linesWanted, where the stars in each line
     * are determined by the current line.
     *
     * @param linesWanted This is how many lines of stars the user wants.
     */
    private static void printStars(int linesWanted) {
        int currentLine = 1;

        while (currentLine <= linesWanted) {
            System.out.println("*".repeat(currentLine));
            currentLine++;
        }
    }

}
