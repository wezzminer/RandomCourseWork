/* CSM 2670 Exercise Set 1
 *
 * Wessley Alexander
 * 01/27/2020
 *
 * File: Exercise06.java
 * This program prints a given message with a picture!
 */

package com.company;

public class Exercise06 {

    /**
     * Main tests if args is length 1 and if the string given is under 120
     * characters. If the input passes it is given to printMoge.
     *
     * @param args The users input, hopefully one string.
     *             Must be under 120 characters.
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Too many inputs given. If you intend to put spaces in your message, " +
                    "you must use quotes at the beginning and end.");
            System.exit(1);
        }

        if (args[0].length() > 120) {
            System.err.println("Message too long! Must be 120 characters or less, yours is at "
                    + args[0].length());
            System.exit(1);
        }

        printMoge(args[0]);
    }

    /**
     * Prints the users message enclosed in a bubble, then prints our 'Moge'.
     *
     *  /---\
     * | arg |
     *  \---/
     *    |
     *    |
     *    |
     *    \
     *      /\/\
     *     (^.^ )
     *      /|\
     *      / \
     *
     * @param arg A string 1 - 120 characters long
     */
    private static void printMoge(String arg) {
        System.out.printf(" /%s\\ \n", "-".repeat(arg.length()));
        System.out.printf("| %s |\n", arg);
        System.out.printf(" \\%s/ \n", "-".repeat(arg.length()));
        System.out.print("   |\n".repeat(3));
        System.out.println("   \\");
        System.out.println("     /\\/\\");
        System.out.println("    (^.^ )");
        System.out.println("     /|\\");
        System.out.println("     / \\");
    }
}
