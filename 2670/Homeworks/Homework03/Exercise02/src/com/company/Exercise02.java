package com.company;

import java.util.Random;
import java.util.Scanner;

public class Exercise02 {

    private static int gamesWon = 0;
    private static int gamesLost = 0;
    private static int gamesTied = 0;
    private static boolean endGame = false;

    public static void main(String[] args) {

        System.out.println("Welcome to Rock-paper-scissors-lizard-Spock!\nLet's begin!");

        while (true) {
            startGame();
            if (endGame) {
                break;
            }
        }

        System.out.println("\nGames won: " + gamesWon + "\nGames lost: " + gamesLost
                + "\nGames tied: " + gamesTied);

        if (gamesWon > gamesLost) {
            System.out.println("\nYou win!");
        } else if (gamesWon == gamesLost) {
            System.out.println("\nIt's a Draw!");
        } else {
            System.out.println("\nYou lose!");
        }

    }

    private static void startGame() {

        while (true) {

            switch (playGame()) {
                case 0:
                    gamesTied++;
                    break;
                case 1:
                    gamesWon++;
                    break;
                case 2:
                    gamesLost++;
                    break;
                }

            int optionChosen = 0;

            System.out.print("Would you like to play another game? (1 for yes, 0 for no): ");

            while (true) {
                Scanner input = new Scanner(System.in);

                try {
                    optionChosen = testInput(input.next());
                } catch (NumberFormatException ex) {
                    System.err.println("Input must be and integer and either 1 or 0");
                    continue;
                }

                input.close();
                break;
             }

            if (optionChosen == 0) {
                endGame = true;
                break;
            }

        }

    }

    private static int testInput(String input) throws NumberFormatException{

        int numberChosen = 0;

        numberChosen = Integer.parseInt(input);

        if (numberChosen == 1 || numberChosen == 0) {
            return numberChosen;
        }

        throw new NumberFormatException();

    }

    private static int playGame() {

        System.out.print("\nChoose your weapon!\n1. Rock\n2. Lizard\n3. Spock\n4. Scissors\n5. Paper\nEnter(1-5):");

        int weaponChosen = 0;

        while (true) {
            Scanner input = new Scanner(System.in);

            try {
                weaponChosen = testInput(input.next());
            } catch (NumberFormatException ex) {
                System.err.println("Input must be and integer and either 1 or 0");
                continue;
            }

            input.close();
            break;
        }

        Random rand = new Random();

        int oppWeapon = rand.nextInt(5) + 1;

        System.out.print("\n\nYou chose: " + getWeaponName(weaponChosen) + "\nOpponent chose: " + getWeaponName(oppWeapon) + "\n\n");

        int result = (weaponChosen - oppWeapon) % 5;

        switch (result) {
            case 1: case 3:
                System.out.println("You win!");
                return 1;
            case 2: case 4:
                System.out.println("You lose!");
                return 2;
        }

        System.out.println("It's a draw!");
        return 0;

    }

    private static int testWeapon(String input) throws NumberFormatException {

        int numberChosen = 0;

        numberChosen = Integer.parseInt(input);

        if (numberChosen < 1 || numberChosen > 5) {
            throw new NumberFormatException();
        }

        return numberChosen;

    }

    private static String getWeaponName(int id) {
        String name = "";
        switch (id) {
            case 1: name += "Rock";
            break;
            case 2: name += "Lizard";
            break;
            case 3: name += "Spock";
            break;
            case 4: name += "Scissors";
            break;
            case 5: name += "Paper";
            break;
        }
        return name;
    }
}
