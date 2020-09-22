/* CSM 2670 Exercise Set 1
 *
 * Wessley Alexander
 * 01/24/2020
 *
 * File: Exercise04.java
 * This program prints a 'stairway' using a given user input.
 */

package com.company;

public class Exercise04 {

    /**
     * Main receives a user input, tests that it is and integer between
     * 0 and 20, then gives the input subtracted by one to printStairPattern.
     *
     * @param args One user input, an integer between 0 and 20.
     *             This input is how many stairs the user wants.
     */
    public static void main(String[] args) {

        int input = 0;

        if (args.length == 0) {
            System.err.println("No inputs given. Program expects one whole number between 0 and 20");
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

        if (input > 20 || input < 0) {
            System.err.println("Input \"" + args[0] + "\" is invalid, must be between 0 and 20.");
            System.exit(2);
        }

        printStairPattern(input - 1);

    }

    /**
     * This method loops and prints each stairway, then prints the bottom line.
     *
     * @param stepsWanted How many steps on the stairway the user wants printed.
     */
    private static void printStairPattern(int stepsWanted) {
        int stepsMade = 0;

        for (int step = stepsWanted; step >= 0; step--) {

            printLine(step, stepsMade, "  @  ******");

            stepsMade++;

            printLine(step, stepsMade," /|\\ *");

            printLine(step, stepsMade," / \\ *");

        }
        System.out.println("*".repeat((5 * (stepsWanted + 2)) + 2));
    }

    /**
     * This method prints out each line in the stairway.
     *
     * @param step The current step to print.
     *
     * @param stepsMade How many steps have been made so far.
     *
     * @param body A string containing a section of the man and stair.
     */
    private static void printLine(int step, int stepsMade, String body) {
        System.out.println(" ".repeat(step * 5) + body + " ".repeat(stepsMade * 5) + "*");
    }

}
