/*
 * CSM 2670 Exercise set 2
 *
 * Name: Wessley Alexander
 * File: Exercise05.java
 *
 * Program prints pascals triangle.
 */

package com.company;

public class Exercise05 {

    // TODO: Spaces + Comments

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 1 && args.length != 2) {
            System.err.println("Invalid amount of inputs entered.");
            System.exit(1);
        }

        int rowsWanted = testInput(args[0]);
        int mod = -1;

        if (args.length == 2) {
            mod = testInput(args[1]);
        }

        printTriangle(rowsWanted, mod);

    }

    private static int testInput(String input) {

        int testedInput = 0;

        try {
            testedInput = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.err.println("Input \"" + input + "\" is not an whole number.");
            System.exit(1);
        }

        if (testedInput < 0) {
            System.err.println("Input \"" + testedInput + "\" is invalid. Must be positive.");
            System.exit(2);
        }

        return testedInput;
    }

    private static void printTriangle(int rowsWanted, int mod) {
        if (mod > -1) {
            System.out.println(" ".repeat(rowsWanted+1) + (1 % mod));
        } else {
            System.out.println(" ".repeat(rowsWanted+1) + 1);
        }

        int[] line = {1, 1};

        for (int i = 1; i <= rowsWanted; i++) {
            String string = " ".repeat(rowsWanted - i);

            if (mod > -1) {

                for (int number : line) {
                    string += " " + String.valueOf(number % mod);
                }

            } else {

                for (int number : line) {
                    string += " " + String.valueOf(number);
                }

            }

            System.out.println(string);

            line = reader(line);

        }

    }

    private static int[] reader(int[] line) {

        int[] array = new int[line.length + 1];

        array[0] = 1;

        for (int i = 1; i <= line.length-1; i++) {
            array[i] = line[i-1] + line[i];
        }

        array[line.length] = 1;

        return array;

    }

}
