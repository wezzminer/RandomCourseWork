/*
 * CSM 2670 Exercise set 2
 *
 * Name: Wessley Alexander
 * File: Exercise01.java
 *
 * Program takes an integer input from the user, then calculates and prints the solution for the sequence:
 *   n
 *   Σ 1/i
 *  i=1
 *
 * Where n is the users input.
 */

package com.company;

public class Exercise01 {

    /**
     * Checks for the correct args.length, sends the input to be tested with testInput,
     * then passes the input to calculateSum to be printed.
     *
     * @param args Users input, hopefully an integer between 1 and 10000.
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Incorrect amount of inputs entered. " +
                    "Program needs one whole number between 1 and 10000.");
            System.exit(1);
        }

        int n = testInput(args[0]);

        calculateSum(n);
    }

    /**
     * Tests if the users input is an integer within 1 to 10000.
     *
     * @param input The given users input.
     *
     * @return A tested input that satisfies the requirements.
     */
    private static int testInput(String input) {

        int testedInput = 0;

        try {
            testedInput = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.err.println("Input \"" + input + "\" is not an whole number.");
            System.exit(2);
        }

        if (testedInput > 10000 || testedInput < 1) {
            System.err.println("Input \"" + testedInput + "\" is invalid, must be between 1 and 10000.");
            System.exit(3);
        }

        return testedInput;
    }

    /**
     * Calculates and prints the sum for the sequence.
     *
     * @param n The number of times we need to repeat the sequence.
     */
    private static void calculateSum(int n) {

        double sum = 0;

        for (double i = 1; i <= n; i++) {
            sum += (1 / i);
        }

        System.out.printf("%.2f", sum);
    }
}
