package Control;

import View.Board;
import View.Square;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author IvanTse
 * @version 1.0
 * @description a listener handles everything with the button
 */
public class ButtonListener implements ActionListener {
    /**
     * 1. Check if the button clicked is a square, in order to make sure object casting would work properly
     * 2. Two situations:
     *      2.1 There is no selected square. Check if the event square is a frog, yes then select the square
     *      2.2 There is a selected square. It is a moving action, from the selected square to the event square,
     *          then check if the moving is legal, and execute it. After moving, set SelectSquare pointer to null.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Security check
        if(!(e.getSource() instanceof Square))
            throw new ClassCastException("The event source is not an instance of Square. ");

        //Variable declaration
        Square eventSquare = (Square)e.getSource();
        Board motherBoard = eventSquare.getMotherBoard();

        //If there is no square selected, goes here
        //handle a selection
        if (motherBoard.getSelectSquare() == null) {

            //Check if the event square is a frog
            if (eventSquare.isFrog()) {
                //Mark the selected square
                motherBoard.setSelectSquare(eventSquare);

                //change the picture to a yellow border one
                eventSquare.select();
            } else {
                //Otherwise alert a not selectable
                JOptionPane.showMessageDialog(motherBoard,"Not a selectable square");
            }
        }
        //If there is a selected square, goes here
        //handle a moving check
        else{
            //Check the moving is legal and moving the squares in the checking function
            if(!MainController.moving(motherBoard.getSelectSquare(), eventSquare, motherBoard)){
                //alert an illegal moving
                JOptionPane.showMessageDialog(motherBoard,"Illegal moving");
            }

            //reset the flag
            motherBoard.setSelectSquare(null);
        }
    }
}
