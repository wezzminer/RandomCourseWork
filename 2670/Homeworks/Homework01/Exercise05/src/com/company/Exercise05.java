/* CSM 2670 Exercise Set 1
 *
 * Wessley Alexander
 * 01/22/2020
 *
 * file: Exercise05.java
 *
 * This program prints a pine tree using two user inputs 'section' and 'height'
 *
 * args:
 * <section> <height>
 */

package com.company;

public class Exercise05 {

    /**
     * Tests each user input then calls the method to begin drawing the tree.
     *
     * @param args User should input two integers between 1 and 10, the first
     *             for number of sections, the second for the height of each
     *             section.
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Incorrect amount of inputs given. Program expects two whole numbers between 1 and 10");
            System.exit(1);
        }

        int inputSection = testInput(args[0]);
        int inputHeight = testInput(args[1]);

	    drawTree(inputSection - 1, inputHeight - 1);
    }

    /**
     * This method tests if given input is an integer between 1 and 10.
     *
     * @param input A given user input, from args
     *
     * @return An integer between 1 and 10
     */
    private static int testInput(String input) {

        int testedInput = 0;

        try {
            testedInput = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.err.println("Input \"" + input + "\" is not an whole number.");
            System.exit(1);
        }

        if (testedInput > 10 || testedInput < 1) {
            System.err.println("Input \"" + testedInput + "\" is invalid, must be between 1 and 10.");
            System.exit(2);
        }

        return testedInput;

    }

    /**
     * The main method for drawing the tree. It loops and calls the method for
     * printing each section, then calls the method for printing the bottom.
     *
     * @param inputSection Users section input minus one
     *
     * @param inputHeight Users height input minus one
     */
    private static void drawTree(int inputSection, int inputHeight) {

        // This equation determines the amount of * that should be at the bottom of the tree
        // so that the program will always end with a single * at the top of the tree.
        int thickness = 1 + ((inputHeight + inputSection) * 2);

        for (int section = inputSection; section >= 0; section--) {
            drawSection(section, inputHeight, thickness);
        }

        drawBottom(thickness);
    }

    /**
     * This method prints each section, up to a given height.
     *
     * @param section The section number.
     *
     * @param inputHeight Users height input minus one.
     *
     * @param thickness The size of the bottom of our tree. In this case it is
     *                  used to determine how many * are printed, because for
     *                  each section and line of height you increase, two is
     *                  subtracted from the total *.
     */
    private static void drawSection(int section, int inputHeight, int thickness) {

        for (int height = inputHeight; height >= 0; height--) {
            System.out.println(" ".repeat(section + height) +
                    "*".repeat((thickness - (2 * (section + height)))));
        }
    }

    /**
     * This method calls getBaseLength with the given thickness to determine
     * how long the base should be and how many spaces go should go inside
     * each stem. It then gives the created strings to drawBottomLine.
     *
     * @param thickness The size of the bottom of our tree.
     */
    private static void drawBottom(int thickness) {

        int baseLength = getBaseLength(thickness);

        String stem = " ".repeat(baseLength / 2) + "*";
        String base = "*".repeat(baseLength);

        drawBottomLine(thickness, baseLength, stem);
        drawBottomLine(thickness, baseLength, stem);
        drawBottomLine(thickness, baseLength, base);
    }

    /**
     * This method returns what size the base should be relative to the given
     * thickness.
     *
     * @param thickness The size of the bottom of our tree.
     *
     * @return The assigned base length according to the given thickness.
     */
    private static int getBaseLength(int thickness) {

        // lol twig
        int length = 1;

        if (thickness > 10 && thickness < 27){
            length = 7;
        } else if (thickness >= 27) {
            length = 11;
        }

        return length;
    }

    /**
     * This method is used for printing the bottom section of the tree.
     *
     * @param thickness The size of the bottom of our tree. Used here to
     *                  determine how many spaces need to come before
     *                  the given string.
     *
     * @param baseLength The size of the bottom line (our base).
     *
     * @param string The string to print after the spaces.
     */
    private static void drawBottomLine(int thickness, int baseLength, String string) {
        System.out.println(" ".repeat((thickness - baseLength) / 2) + string);
    }

}
