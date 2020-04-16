package View;

import Control.MainController;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    private final MainController controller;
    private Square[] squares;
    private Square selectSquare = null;
    private int frogsLeft;

    public Board(MainController controller){
        this.controller = controller;

        setTitle("Hoppers - Level " + controller.getLevel() + " - " + frogsLeft + " Green Frogs Left");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public Board(Square[] squares, int frogsLeft, MainController controller){
//        this.squares = squares;
//        this.frogsLeft = frogsLeft;
//        this.controller = controller;
//
//        setTitle("Hoppers - Level " + controller.getLevel() + " - " + frogsLeft + " Green Frogs Left");
//        setSize(1000, 1000);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(5,5));
//        for(Square sq:squares)
//            panel.add(sq);
//        add(panel);
//        setVisible(true);
//
//    }

    public Square[] getSquares() {
        return squares;
    }

    public Square getSelectSquare() {
        return selectSquare;
    }

    public void setSelectSquare(Square selectSquare) {
        this.selectSquare = selectSquare;
    }

    public int decreaseAFrog() {
        if(frogsLeft <= 0)
            throw new RuntimeException();
        this.frogsLeft -= 1;
        setTitle("Hoppers - Level " + controller.getLevel() + " - " + frogsLeft + " Green Frogs Left");
        return frogsLeft;
    }

    public void newGame(Square[] squares, int frogsLeft){
        this.frogsLeft = frogsLeft;
        this.squares = squares;

//        removeAll();
        setTitle("Hoppers - Level " + controller.getLevel() + " - " + frogsLeft + " Green Frogs Left");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,5));
        for(Square sq:squares)
            panel.add(sq);
        add(panel);
        setVisible(true);
    }
    
    public void gameWon(){
        controller.nextLevel();
    }

}
