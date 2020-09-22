/*
 * Wessley Alexander
 *
 *  FINAL EXAM
 *
 *  There are probably many things I could have done better,
 *  but i've already spent quite some time on this one.
 *
 *  It works though, and you can also use any set of characters in place of 1-6, too.
 */

import java.util.*;

public class Backtracking
{

    class Hexagon
    {

        ArrayList<Character> items = new ArrayList<>();

        Hexagon(ArrayList<Character> chars)
        {
            this.items = chars;
        }

        public Character getItem(int n) {return items.get(n);}

        public void rotate() {Collections.rotate(items, 1);}


    }

    public static void main(String[] args)
    {
        Backtracking b = new Backtracking();
    }

    Backtracking()
    {

        System.out.print("Enter how many puzzles you'd like to try:  ");
        Scanner numLoops = new Scanner(System.in);

        // Test first input
        int var = testInput(numLoops.next());

        for (int i = 1; i <= var; i++)
        {

            ArrayList<Hexagon> hexagons = new ArrayList<>();

            System.out.println("Puzzle " + i + ":");
            for (int j = 1; j <= 7; j++)
            {
                System.out.print("Nut " + j + ": ");
                Scanner line = new Scanner(System.in);
                // Each scanner line is put below to check for errors, turn into an array,
                // inserted into a hexagon, then added to our list.
                hexagons.add(new Hexagon(parseLine(line.nextLine())));
            }

            if (solvable(hexagons, new ArrayList<Hexagon>()))
            {
                System.out.println("\nSolution Found.\n");
            } else {
                System.out.println("\nNo Solution.\n");
            }

        }

    }

    private int testInput(String str)
    {
        int temp = 0;

        try
        {
            temp = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            System.err.println("Must be a single integer! (And no overflows)");
            System.exit(4);
        }

        return temp;
    }

    private static ArrayList<Character> parseLine(String line)
    {
        ArrayList<Character> chars = new ArrayList<>();

        Scanner reader = new Scanner(line);
        for (int i = 0; i < 6; i++)
        {

            if(!reader.hasNext() & i != 5)
            {
                System.err.println("Invalid line entered, must be six characters separated by spaces.");
                System.exit(1);
            }

            String str = reader.next();

            if (str.length() > 1)
            {
                System.err.println("Input too large, must be only one character.");
                System.exit(2);
            }

            Character newChar = str.charAt(0);

            if (chars.contains(newChar))
            {
                System.err.println("Cannot enter the same character twice.");
                System.exit(2);
            }

            chars.add(newChar);

        }

        return chars;
    }

    private boolean solvable(ArrayList<Hexagon> hexagons, ArrayList<Hexagon> currentPuzzle)
    {

        // If there are no more hexagons to choose from, and a second validity check returns true,
        // the solution has been found
        if (hexagons.isEmpty() && valid(currentPuzzle)) {

            return true;

        } else {

            for (int i = 0; i < hexagons.size(); i++)
            {
                // chooses one hexagon to put into our puzzle
                Hexagon hex = hexagons.remove(i);
                currentPuzzle.add(hex);

                if (valid(currentPuzzle))
                {
                    if (solvable(hexagons, currentPuzzle))
                    {
                        return true;
                    }
                }

                // "unchooses" the hexagon if invalid
                hexagons.add(i, hex);
                currentPuzzle.remove(hex);
            }
        }
        return false;
    }

    /*
     * the placement of our hexagons on the grid is:
     *
     *              1
     *          6       2
     *              0
     *          5       3
     *              4
     *
     * With the values corresponding to the values in our array.
     *
     * Each hexagon also has a value assigned to its sides:
     *
     *              4
     *          3       5
     *
     *          2       0
     *              1
     *
     * Checking these values with other hexagons in relation to
     * their positions on the grid is how validity is checked.
     *
     * EX: Hexagon 1 is connected to Hexagon 2 via 1's side 0 and 2's side 3.
     */
    private boolean valid(ArrayList<Hexagon> currentPuzzle)
    {

        // If it's just the center piece, no other checks can be made
        if (currentPuzzle.size() == 1)
        {
            return true;
        }

        int lastHexagonNum = currentPuzzle.size()-1;
        Hexagon lastInList = currentPuzzle.get(lastHexagonNum);
        boolean rotateSuccess = false;

        // This rotates each new hexagon so that their values are aligned with Hexagon 0
        for (int i = 0; i < 6; i++)
        {
            lastInList.rotate();
            if (lastInList.getItem(lastHexagonNum % 6) == currentPuzzle.get(0).getItem((lastHexagonNum + 3) % 6))
            {
                currentPuzzle.set(lastHexagonNum, lastInList);
                rotateSuccess = true;
                break;
            }
        }

        // This is for when to value on the new hexagon aligns with the middle.
        // Can only occur if the user enters different characters for each hexagon
        if (!rotateSuccess)
        {
            return false;
        }

        // Roadblock until there are at least 3 hexagons to compare.
        if (currentPuzzle.size() < 3)
        {
            return true;
        }

        // Checks the 2,4, and 6 hexagons for valid placement, if available.
        for (int i = 2; i <= currentPuzzle.size()-1; i = i + 2)
        {
            Hexagon hex = currentPuzzle.get(i);


            // checks with the hexagon ahead, if there is one.
            // 2 -> 3, 4 -> 5
            if(i < currentPuzzle.size()-1)
            {
                if (i == 6)
                {
                    // Wraps 6 around to check 1
                    if (!(hex.getItem(5) == currentPuzzle.get(1).getItem(2)))
                    {
                        return false;
                    }
                } else if (!(hex.getItem(i - 1) == currentPuzzle.get(i + 1).getItem((i + 2) % 6)))
                {
                    return false;
                }
            }

            // Checks the hexagon behind.
            // 2 -> 1, 4 -> 3
            if (i == 6)
            {
                if (!(hex.getItem(1) == currentPuzzle.get(5).getItem(4)))
                {
                    return false;
                }
            } else if (!(hex.getItem(i + 1) == currentPuzzle.get(i - 1).getItem(i - 2)))
            {
                return false;
            }


        }
        return true;

    }

}
