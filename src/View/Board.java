package View;

import Control.MainController;

import javax.swing.*;
import java.awt.*;

/**
 * @author IvanTse
 * @version 1.0
 * @description The board which is extended from JFrame
 */
public class Board extends JFrame {

    private final MainController controller; /* The object of the controller for callback */
    private Square[] squares; /* All squares object */
    private Square selectSquare; /* The object of the selected square */
    private int frogsLeft; /* The number of left frogs */

    /**
     * Constructor function
     * Title, frame size and close operation is set here.
     * @param controller The object of the controller which initialized this board
     */
    public Board(MainController controller){
        this.controller = controller;

        setTitle("Hoppers - Level " + controller.getLevel() + " - " + frogsLeft + " Green Frogs Left");
        setSize(755, 778);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MainController getController(){
        return controller;
    }

    public Square[] getSquares() {
        return squares;
    }

    public Square getSelectSquare() {
        return selectSquare;
    }

    public void setSelectSquare(Square selectSquare) {
        this.selectSquare = selectSquare;
    }

    /**
     * Decrease a frog when this function is called.
     * Called when a frog was moved.
     * @return the number of rest frogs
     * @exception RuntimeException when the function is called with no frogs left
     */
    public int decreaseAFrog() {
        if(frogsLeft <= 0)
            throw new RuntimeException();
        this.frogsLeft -= 1;
        setTitle("Hoppers - Level " + controller.getLevel() + " - " + frogsLeft + " Green Frogs Left");
        return frogsLeft;
    }

    /**
     * Begin a new game, with new squares on the board
     * @param squares new squares
     * @param frogsLeft the number of green frogs in the squares in above parameter
     */
    public void newGame(Square[] squares, int frogsLeft){
        this.frogsLeft = frogsLeft;
        this.squares = squares;
        this.selectSquare = null;

        setTitle("Hoppers - Level " + controller.getLevel() + " - " + frogsLeft + " Green Frogs Left");
        JPanel panel = new JPanel(new GridLayout(5,5));
        for(Square sq:squares)
            panel.add(sq);
        setContentPane(panel);
        setVisible(true);
    }

}
