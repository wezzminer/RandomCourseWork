/*
 * Wessley Alexander
 *
 * Exercise04
 *
 * Unfinished, issues with layout
 *
 */
import javax.swing.*;

public class Exercise04 extends JFrame {

    private class Calculator extends JPanel {

        Calculator() {

            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            JTextField text = new JTextField();
            text.setSize(50, 75);

            ButtonBox buttons = new ButtonBox();

            add(text);
            add(buttons);

        }

    }

    public Exercise04() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(200, 300);
        setResizable(false);

        Calculator calc = new Calculator();

        add(calc);
        pack();

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Exercise04().setVisible(true));
    }

}