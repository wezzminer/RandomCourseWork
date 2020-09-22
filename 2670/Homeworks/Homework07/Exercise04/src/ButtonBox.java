import javax.swing.*;
import java.awt.*;

class ButtonBox extends JPanel {

    JButton one = new JButton("1");
    JButton two = new JButton("2");
    JButton three = new JButton("3");
    JButton four = new JButton("4");
    JButton five = new JButton("5");
    JButton six = new JButton("6");
    JButton seven = new JButton("7");
    JButton eight = new JButton("8");
    JButton nine = new JButton("9");
    JButton clear = new JButton("C");
    JButton equals = new JButton("=");
    JButton add = new JButton("+");
    JButton subtract = new JButton("-");
    JButton multiply = new JButton("*");
    JButton divide = new JButton("/");

    ButtonBox() {

        setLayout(new FlowLayout());

        add(one);
        add(two);
        add(three);
        add(four);
        add(five);
        add(six);
        add(seven);
        add(eight);
        add(nine);
        add(clear);
        add(equals);
        add(add);
        add(subtract);
        add(multiply);
        add(divide);

    }

}
