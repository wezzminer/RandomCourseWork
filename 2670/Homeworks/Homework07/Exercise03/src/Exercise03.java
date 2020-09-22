/*
 * Wessley Alexander
 *
 * Exercise03
 *
 * Creates a small gui to convert between imperial units.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Exercise03 extends JFrame {

    class Converter_Panel extends JPanel
                            implements ActionListener {

        Converter_Component item1;
        Converter_Component item2;

        Converter_Panel() {

            setLayout(new FlowLayout());

            item1 = new Converter_Component();
            item2 = new Converter_Component();

            item1.getButton().addActionListener(this);
            item2.getButton().addActionListener(this);

            add(item1);
            add(item2);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            // Determines which button was the source so the program knows which component to target.
            // Also ignores any action from a component with no valid input.
            if (e.getSource() == item1.getButton()) {

                if (isInputValidDouble(item1.getText())) {
                    convertNumbers(item1, item2);
                }

            } else {

                if (isInputValidDouble(item2.getText())) {
                    convertNumbers(item2, item1);
                }

            }
        }

        private boolean isInputValidDouble(String str) {
            if (str.isEmpty()) {
                return false;
            }

            try {
                Double.parseDouble(str);
            } catch (NumberFormatException ex) {
                return false;
            }

            return true;
        }

        private void convertNumbers(Converter_Component actionSource, Converter_Component target) {

            double actionSourceInput = Double.parseDouble(actionSource.getText());

            int actionSourceSelection = actionSource.getComboSelection();
            int targetSelection = target.getComboSelection();

            // Same unit selected, no conversion needed
            if (actionSourceSelection == targetSelection) {
                target.setText(new DecimalFormat(".######").format(actionSourceInput));
                return;
            }

            // I used feet as a base for the conversions (oldUnit -> feet -> newUnit)
            double feetConversion = convertToFeet(actionSourceInput, actionSourceSelection);
            feetConversion = convertFromFeet(feetConversion, targetSelection);

            target.setText(new DecimalFormat(".######").format(feetConversion));

        }

        private double convertToFeet(double num, int selectionID) {

            switch (selectionID) {
                case 1: // from inches
                    num /= 12.0;
                    break;
                case 2: // from yards
                    num *= 3;
                    break;
                case 3: // from miles
                    num *= 5280;
                    break;
                case 4: // from rods
                    num *= 16.5;
                    break;
                case 5: // from furlongs
                    num *= 660;
                    break;
                case 6: // from fathoms
                    num *= 6;
                    break;
            }

            return num;
        }

        private double convertFromFeet(double num, int selectionID) {

            switch (selectionID) {
                case 1: // to inches
                    num *= 12;
                    break;
                case 2: // to yards
                    num /= 3;
                    break;
                case 3: // to miles
                    num /= 5280;
                    break;
                case 4: // to rods
                    num /= 16.5;
                    break;
                case 5: // to furlongs
                    num /= 660;
                    break;
                case 6: // to fathoms
                    num /= 6;
                    break;
            }

            return num;
        }

    }

    public Exercise03() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(200, 110);
        setResizable(false);
        setLocationRelativeTo(null);

        Converter_Panel converterPanel = new Converter_Panel();
        add(converterPanel);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Exercise03().setVisible(true));
    }

}