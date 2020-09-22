/*
 *
 * Wessley Alexander exam03-03
 *
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exam03 {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Invalid amount of inputs. Expected the single name of a .txt file.");
            System.exit(2);
        }

        File target = getRequestedFile(args[0]);
        File newFile = new File("reversedExam03.txt");

        Scanner counter = null;
        Scanner loader = null;

        int lines = 0;

        try {
            counter = new Scanner(target);
            loader = new Scanner(target);
        } catch (FileNotFoundException ex) {
            System.err.println("File not Found!");
            System.exit(1);
        }

        // Counts the lines in the given file
        while (counter.hasNextLine()) {
            lines += 1;
            counter.nextLine();
        }

        // Array to store strings backwards
        String[] fileLines = new String[lines];

        for (int stringNumber = lines - 1; stringNumber >= 0; stringNumber--) {
            fileLines[stringNumber] = loader.nextLine();
        }

        // Reads and prints each word in each line using arrays
        for (int lineNumber = 0; lineNumber <= lines - 1; lineNumber++) {

            Scanner wordCounter = new Scanner(fileLines[lineNumber]);
            Scanner wordLoader = new Scanner(fileLines[lineNumber]);

            int words = 0;

            while (wordCounter.hasNext()) {
                words += 1;
                wordCounter.next();
            }

            String[] wordsInLine = new String[words];

            for (int wordNumber = words - 1; wordNumber >= 0; wordNumber--) {
                wordsInLine[wordNumber] = wordLoader.next();
            }

            for (int wordNumber = 0; wordNumber <= words - 1; wordNumber++) {

                System.out.print(wordsInLine[wordNumber] + " ");

            }

            System.out.println("");

        }



    }

    private static File getRequestedFile(String name) {
        File temp = new File(name + ".txt");
        File temp2 = new File(name);

        if (temp.exists()) {
            return temp;
        } else if (temp2.exists()) {
            return temp2;
        } else {
            System.err.println("File not Found!");
            System.exit(1);
        }
        return temp;
    }

}
