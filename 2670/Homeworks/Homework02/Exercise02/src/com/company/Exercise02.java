/*
 * CSM 2670 Exercise set 2
 *
 * Name: Wessley Alexander
 * File: Exercise02.java
 *
 * Program takes homework average, exam average, and final exam scores to determine a students final average
 * and letter grade.
 */

package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise02 {

    /**
     * The purpose of main is to receive input with the scanner, test that each input is a number between 0 and 100,
     * then call the methods to check the students final grade.
     *
     * @param args No args
     */
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        // This is where the input scores are kept.
        // 0 = homework, 1 = exam, 2 = final exam
        double[] scores = new double[3];

        String[] averageNames = {"homework", "exam", "final exam"};

        // This is the loop that calls the scanner for user input then tests those inputs.
        for (int inputNumber = 0; inputNumber < 3; inputNumber++) {

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
        evaluateFinalGrade(scores);

    }

    /**
     * This checks if any of the given scores are below 70. If any are, the method will send the lowest score
     * to printFinalGrade as the final grade.
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
            printFinalGrade(getLetterGrade(minScore), minScore);
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
     * This prints the final grade to the user.
     * EX) Final Grade: %50.0 (F)
     *
     * @param letterGrade The single character string given to represent the letter grade
     *
     * @param finalGrade The students final grade.
     */
    private static void printFinalGrade(String letterGrade, double finalGrade) {
        System.out.printf("Final Grade: %%%.2f (%s)\n", finalGrade, letterGrade);
    }

    /**
     * If all values are above 70, then this method is used to calculate the students final grade.
     * It multiplies the input scores by their weights and adds them together to get the final score.
     * Homework - %30
     * Exams - %40
     * Final Exam - %30
     *
     * @param scores the given input scores in a double array.
     */
    private static void evaluateFinalGrade(double[] scores) {

        double homeworkGrade = scores[0] * .3;
        double examGrade = scores[1] * .4;
        double finalExamGrade = scores[2] * .3;

        double finalGrade = homeworkGrade + examGrade + finalExamGrade;

        printFinalGrade(getLetterGrade(finalGrade), finalGrade);

    }

}
