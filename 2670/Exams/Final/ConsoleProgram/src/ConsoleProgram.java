/*
 * Wessley Alexander
 *
 * Final Exam
 *
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleProgram
{

    public static void main(String[] args) {

        if (args.length != 1)
        {
            System.out.println("Filename required.");
            System.exit(2);
        }

        FileInputStream stream = null;
        Scanner lines;

        try
        {
            stream = new FileInputStream(args[0]);
        } catch (IOException ex) {
            System.err.println("Error loading file.");
            System.exit(1);
        }

        lines = new Scanner(stream);

        while (lines.hasNextLine())
        {
            Scanner data = new Scanner(lines.nextLine());

            int value = data.nextInt();

            ArrayList<Integer> bids = new ArrayList<>();
            while (data.hasNextInt())
            {
                bids.add(data.nextInt());
            }

            int winnerNumber = priceIsRight(bids, value);

            if (winnerNumber == -1)
            {
                System.out.println("No Winner!");
            } else
            {

                System.out.printf("Player %d Wins!\n", winnerNumber);
            }

        }

    }

    public static int priceIsRight(List<Integer> bids, int value)
    {
        Integer[] bestBid = {-1, value};

        for (int i = 0; i < bids.size(); i++)
        {
            int distanceFromValue = value - bids.get(i);

            if (distanceFromValue < bestBid[1] && distanceFromValue >= 0)
            {
                bestBid = new Integer[]{i + 1, distanceFromValue};
            }
        }

        return bestBid[0];
    }


}
