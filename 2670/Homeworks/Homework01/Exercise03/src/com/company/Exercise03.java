/* CSM 2670 Exercise Set 1
 *
 * Wessley Alexander
 * 01/24/2020
 *
 * File: Exercise03.java
 * This program prints a hallow triangle of asterisks,
 * starting at one to a given number.
 */

package com.company;

public class Exercise03 {

    /**
     * Main receives a user input, tests that it is and integer between
     * 1 and 80, then gives the input to printStarPattern.
     *
     * @param args One user input, an integer between 1 and 80.
     *             This input is how many stars the user wants on the bottom.
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
     * This method prints the triangle pattern using linesWanted.
     * If linesWanted is even, then the top will be two * instead of one.
     *
     * @param linesWanted How many stars the user wants on the bottom
     */
    private static void printStarPattern(int linesWanted) {
         int outsideWhitespace = linesWanted / 2;
         int insideWhitespace = linesWanted % 2;
         int linesLooped = 1;

         if ((linesWanted % 2) == 1) { // Check if lineWanted is odd
             System.out.println(" ".repeat(outsideWhitespace) + "*");
         }

         if (linesWanted != 1) {

             while (linesLooped != (linesWanted / 2)) {

                 outsideWhitespace--;

                 System.out.println(" ".repeat(outsideWhitespace) + "*"
                         + " ".repeat(insideWhitespace) + "*");

                 insideWhitespace += 2;
                 linesLooped++;
             }
             System.out.println("*".repeat(linesWanted));
         }
    }

}
