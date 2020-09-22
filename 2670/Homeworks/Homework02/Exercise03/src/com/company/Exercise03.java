/*
 * CSM 2670 Exercise set 2
 *
 * Name: Wessley Alexander
 * File: Exercise03.java
 *
 * Program takes homework average and exam average to calculate the final exam score needed to
 * obtain each letter grade. Prints a nice table.
 */

package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise03 {

    /**
     * The purpose of main is to receive input with the scanner, test that each input is a number between 0 and 100,
     * then call the methods to check/display the student's possible final grades.
     *
     * @param args No args
     */
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        // This is where the input scores are kept.
        // 0 = homework, 1 = exam
        double[] scores = new double[2];

        String[] averageNames = {"homework", "exam"};

        // This is the loop that calls the scanner for user input then tests those inputs.
        for (int inputNumber = 0; inputNumber < 2; inputNumber++) {

            try {
                System.out.printf("Enter the student's %s average: ", averageNames[inputNumber]);
                scores[inputNumber] = userInput.nextDouble();
            } catch (InputMismatchException ex) {
                System.err.printf("\nERROR: input \"%s\" is not a number.", userInput.next());
                System.exit(1);
            }

            if (scores[inputNumber] < 0 || scores[inputNumber] > 100) {
                System.err.printf("\nERROR: input in %s average is invalid. Scores need to be between" +
                        " 100 and 0.", averageNames[inputNumber]);
                System.exit(2);
            }



        }

        checkMinGrade(scores);
        printTable(scores);

    }

    /**
     * This checks if any of the given scores are below 70. If any are, the method will print a message
     * saying that the lowest is the highest grade they can receive.
     *
     * @param scores The given inputs as a double array.
     */
    private static void checkMinGrade(double[] scores) {

        // This is where the lowest value will be saved.
        double minScore = 100;

        // Loop evaluates the lowest value in scores.
        for (double score: scores) {
            if (score < minScore) {
                minScore = score;
            }
        }

        if (minScore < 70) {
            System.out.printf("The highest score you can get is %%%s (%s)."
                    , minScore, getLetterGrade(minScore));
            System.exit(0);
        }

    }

    /**
     * Returns the letter associated with the final grade as a string.
     * A = 100 - 90
     * B = 89 - 80
     * C = 79 - 70
     * D = 69 - 55
     * F = All else.
     *
     * @param finalGrade The grade we need to associate a letter with.
     *
     * @return A string with a singe character, representing the letter grade.
     */
    private static String getLetterGrade(double finalGrade) {

        String letterGrade = "F";

        if (finalGrade >= 90) {
            letterGrade = "A";
        } else if (finalGrade < 90 && finalGrade >= 80) {
            letterGrade = "B";
        } else if (finalGrade < 80 && finalGrade >= 70) {
            letterGrade = "C";
        } else if (finalGrade < 70 && finalGrade >= 55) {
            letterGrade = "D";
        }

        return letterGrade;

    }

    /**
     * Prints a pretty table and the (possible) scores it would take to obtain a
     * 70, 80, or 90 in the class.
     *
     * @param scores
     */
    private static void printTable(double[] scores) {

        System.out.print("Grade | Score Needed\n");
        int[] scoresToCheck = {70, 80, 90};

        for (int scoreToCheck: scoresToCheck) {

            double scoreNeeded = calculateFinalExamGrade(scoreToCheck, scores);

            if (scoreNeeded >= 0 && scoreNeeded <=100) {
                System.out.printf("%s     | %%%.2f\n", getLetterGrade(scoreToCheck), scoreNeeded);
            }

        }

    }

    /**
     * Calculates the score needed to obtain the desired number.
     *
     * @param desiredScore
     * @param scores
     *
     * @return the score needed to reach the desired number
     */
    private static double calculateFinalExamGrade(int desiredScore, double[] scores) {
        // Because our final exam weight is %30 of our total score, we need to multiply
        // by 3.33 (represented by (10 * x)/3) because 30 + 30 + 30 + 10 = 100
        return (10 * (desiredScore - ((scores[0] * .3) + (scores[1] * .4)))) / 3;
    }

}
