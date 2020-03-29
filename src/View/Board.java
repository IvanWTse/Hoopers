package View;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    public Board(int length, Square[] squares){
        setTitle("Hoppers");
        setSize(length, length);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(length,length));
        for(Square sq:squares)
            panel.add(sq);
        add(panel);
        setVisible(true);
    }

}
