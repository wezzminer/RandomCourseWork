/* CSM 2670 Exercise Set 3
 *
 * Wessley Alexander
 * 02/12/2020
 *
 * File: Exercise01.java
 * This program prints a (given if specified) fortune with a picture!
 * Must have a fortune file
 */

package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Exercise01 {

    private static boolean DEBUG = false;
    private static boolean FORTUNE_CHOSEN = false;
    private static int FORTUNE_NUMBER = -1;
    private static int NUMBER_OF_FORTUNES = 0;

    private static Scanner fortune;

    /**
     * Method counts the total fortunes in the file (NUMBER_OF_FORTUNES),
     * and sets up the scanner used to find the fortune.
     *
     * @throws FileNotFoundException
     */
    private static void setupFortunes() throws FileNotFoundException {

        String fortuneFile = "fortunes";

        fortune = new Scanner(new FileReader(fortuneFile));
        fortune.useDelimiter("%");

        Scanner setup = new Scanner(new FileReader(fortuneFile));
        setup.useDelimiter("%");

        while (setup.hasNext()) {

            NUMBER_OF_FORTUNES++;
            setup.next();

        }

        setup.close();

    }

    /**
     * The main method runs various checks and setups before getting the fortune and
     * sending it to printMoge().
     *
     * @param args The users input
     */
    public static void main(String[] args) {

        try {
            setupFortunes();
        } catch (FileNotFoundException ex) {
            System.err.println("Fortune file not found.");
            System.exit(1);
        }

        if (args.length != 0) {

            if (args.length > 2) {
                System.err.println("Too many inputs entered, must be at most two.\n" +
                                    "OPTIONS: \"debug\" OR/AND fortune number");
                System.exit(1);
            }

            try {
                for (String arg : args) {

                    if (arg.equals("debug")) {
                        DEBUG = true;
                    } else {
                        FORTUNE_NUMBER = testInput(arg);
                    }

                }
            } catch (IndexOutOfBoundsException ignored){
            }

        }

        printMoge(getFortune());

    }

    /**
     * This is to test if any given integers are valid.
     *
     * @param input
     * @return
     */
    private static int testInput(String input) {

        int testedInput = 0;

        try {
            testedInput = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.err.println("Input \"" + input + "\" is not a valid input.");
            System.exit(1);
        }

        if (testedInput > NUMBER_OF_FORTUNES || testedInput < 1) {
            System.err.println("Input \"" + testedInput + "\" is invalid, must be between 1 and " + NUMBER_OF_FORTUNES);
            System.exit(2);
        }

        if (!FORTUNE_CHOSEN) { //This is to make sure only the first number is accepted
            FORTUNE_CHOSEN = true;
            return testedInput;
        } else {
            return FORTUNE_NUMBER;
        }

    }

    /**
     * This method checks if the user gave an input number, if not it generates a random number.
     *
     * @return The fortune associated with the given/generated number.
     */
    private static String getFortune() {

        int getFortuneNumber;

        if (FORTUNE_CHOSEN) {
            getFortuneNumber = FORTUNE_NUMBER;
        } else {
            Random rand = new Random();
            getFortuneNumber = rand.nextInt(NUMBER_OF_FORTUNES ) + 1;
            FORTUNE_NUMBER = getFortuneNumber;
        }

        for (int currentFortuneNumber = 1; currentFortuneNumber < getFortuneNumber; currentFortuneNumber++) {
            fortune.next();
        }

        return fortune.next();

    }

    /**
     * Prints the users message enclosed in a bubble, then prints our 'Moge'.
     *
     *  /-------\
     * | fortune |
     *  \-------/
     *    |
     *    |
     *    |
     *    \
     *      /\/\
     *     (^.^ )
     *      /|\
     *      / \
     *    *OPTIONAL DEBUG MESSAGES*
     *
     * @param
     */
    private static void printMoge(String fortune) {

        fortune = fortune.strip(); // remove extra \n

        String[] fortuneInfo = formatFortune(fortune);
        
        System.out.printf(" /%s\\ \n", "-".repeat(Integer.parseInt(fortuneInfo[1])));
        System.out.printf("%s\n", fortuneInfo[0]);
        System.out.printf(" \\%s/ \n", "-".repeat(Integer.parseInt(fortuneInfo[1])));
        System.out.print("   |\n".repeat(3));
        System.out.println("   \\");
        System.out.println("     /\\/\\");
        System.out.println("    (^.^ )");
        System.out.println("     /|\\");
        System.out.println("     / \\");

        if (DEBUG) {
            System.out.print("   # of Fortunes in file: " + NUMBER_OF_FORTUNES + "  Fortune # displayed: " + FORTUNE_NUMBER);
        }

    }

    /**
     * This method preps our fortune for printing inside the speech bubble.
     * Ex.
     * | *fortune* |
     *            ^
     * Padding will be added here
     * to make sure all | end up
     * in the same spot.
     *
     * @param fortune our given fortune string.
     * @return String[]{New formatted string, Size of longest line (as a string)}
     */
    private static String[] formatFortune(String fortune) {

        fortune = fortune.replace("\t", "    ");

        int maxLength = 0;
        String returnString = "";

        Scanner findMax = new Scanner(fortune);
        findMax.useDelimiter("\n");

        while (findMax.hasNext()) {

            String currentLine = findMax.next();
            if (currentLine.length() > maxLength) {
                maxLength = currentLine.length();
            }
        }
        findMax.close();

        Scanner format = new Scanner(fortune);
        format.useDelimiter("\n");

        while (format.hasNext()) {

            String currentLine = format.next();
            currentLine = currentLine.stripTrailing();
            returnString += ("| " + currentLine + " ".repeat(maxLength - currentLine.length()) + " |\n");

        }

        returnString = returnString.stripTrailing();

        return new String[]{returnString, Integer.toString(maxLength)};

    }

}
