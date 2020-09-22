/*
 * Wessley Alexander
 *
 * FINAL EXAM
 */
public class ClassDesign
{

    private static int ticketIDCount = 1;

    public static abstract class Ticket
    {

        final int ID;
        int price;

        Ticket()
        {
            this.ID  = ticketIDCount;
            ticketIDCount++;
        }

        public int getPrice() {return price;}

        public int getNumber() {return ID;}

        @Override
        public String toString() {
            return "Number: " + ID + ", Price $" + price;
        }
    }

    public static class WalkUpTicket extends Ticket
    {
        WalkUpTicket()
        {
            super();
            price = 50;
        }
    }

    public static class AdvanceTicket extends Ticket
    {
        AdvanceTicket(int days) {
            super();
            if (days < 1) {
                ticketIDCount--;
                throw new IllegalArgumentException("Days should not be less than one.");
            } else if (days >= 10) {
                price = 30;
            } else {
                price = 40;
            }
        }
    }

    public static class StudentAdvanceTicket extends AdvanceTicket
    {

        StudentAdvanceTicket(int days)
        {
            super(days);
            price /= 2;
        }

        @Override
        public String toString() {
            return "Number: " + ID + ", Price $" + price + " (ID required)";
        }
    }

    // Do not make edits below here
    // ************************************************************************************
    private static void error(Ticket t)
    {
        System.out.println("Error found with " + t);
        System.exit(1);
    }

    public static void main(String[] s)
    {
        // Build some walk up tickets. There are lots of magic numbers in this test code,
        // so be careful if you make edits. Do not worry I will not take off
        // points for this bad code ;) It is better to use JUint for this but 
        // I did not want you to have to mess with getting it setup.
        //
        Ticket test = null;
        for(int i = 1; i <= 10 ; i++)
        {
            test = new WalkUpTicket();
            if(test.getNumber() != i || test.getPrice() != 50)
            {
                error(test);
            }
        }

        if(!test.toString().equals("Number: 10, Price $50")) error(test);

        // Build some good advance tickets
        for(int i = 1; i <= 9; i++)
        {
            test = new AdvanceTicket(i);
            if(test.getNumber() != i + 10 || test.getPrice() != 40)
            {
                error(test);
            }
        }

        // Build some bad advance tickets
        for(int i = 0; i < 13; i++)
        {
            try
            {
                test = new AdvanceTicket(-i);
                error(test);
            }
            catch (IllegalArgumentException ex)
            {
                // Good we caught that we tried to make a bad ticket
            }
        }

        // Next ticket should be number 20
        test = new WalkUpTicket();
        if(test.getNumber() != 20 || test.getPrice() != 50)
        {
            error(test);
        }

        // Build some good advance tickets
        for(int i = 21; i <= 29; i++)
        {
            test = new AdvanceTicket(i);
            if(test.getNumber() != i || test.getPrice() != 30)
            {
                error(test);
            }
        }

        // Build some good advance tickets
        for(int i = 1; i <= 9; i++)
        {
            test = new StudentAdvanceTicket(i);
            if(test.getNumber() != i + 29 || test.getPrice() != 20)
            {
                error(test);
            }
        }

        // Build some bad advance tickets
        for(int i = 0; i < 13; i++)
        {
            try
            {
                test = new StudentAdvanceTicket(-i);
                error(test);
            }
            catch (IllegalArgumentException ex)
            {
                // Good we caught that we tried to make a bad ticket
            }
        }

        // Next ticket should be number 39
        test = new WalkUpTicket();
        if(test.getNumber() != 39 || test.getPrice() != 50)
        {
            error(test);
        }

        // Build some good advance tickets
        for(int i = 40; i <= 50; i++)
        {
            test = new StudentAdvanceTicket(i);
            if(test.getNumber() != i || test.getPrice() != 15)
            {
                error(test);
            }
        }

        if(!test.toString().equals("Number: 50, Price $15 (ID required)")) error(test);

        System.out.println("Passed tests");
    }
}