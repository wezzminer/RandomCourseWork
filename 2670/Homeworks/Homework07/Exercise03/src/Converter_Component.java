/*
 * Wessley Alexander
 *
 * Exercise03
 *
 * The component class to be used with exercise03
 */

import javax.swing.*;
import java.awt.*;

class Converter_Component extends JPanel {

    JComboBox comboBox;
    JTextField textField;
    JButton button;

    public Converter_Component() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        comboBox = dropDown();
        textField = textBox();
        button = convertButton();

        add(comboBox);
        add(textField);
        add(button);
    }

    private JComboBox dropDown() {
        String[] values = {"Feet", "Inches", "Yards", "Miles", "Rods", "Furlongs", "Fathoms"};
        return new JComboBox(values);
    }

    private JTextField textBox() {
        return new JTextField();
    }

    private JButton convertButton() {
        JButton button = new JButton("Convert");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    public int getComboSelection() {
        return comboBox.getSelectedIndex();
    }

    public String getText() {
        return textField.getText();
    }

    public JButton getButton() {
        return button;
    }

    public void setText(String str) {
        this.textField.setText(str);
    }

}