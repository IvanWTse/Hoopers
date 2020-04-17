package View;

import Control.ButtonListener;
import Control.Coordinate2D;
import Control.MyImageIcon;

import javax.swing.*;

/**
 * @author IvanTse
 * @version 1.0
 * @description Square extended from JButton
 */
public class Square extends JButton {
    private final Coordinate2D coor; /* co-ordinate of the square */
    private final Board motherBoard; /* object of the mother board */

    /**
     * Constructor
     * @param coor the co-ordinate of the square
     * @param icon the icon of the square
     * @param motherBoard the object of the mother board
     */
    public Square(Coordinate2D coor, MyImageIcon icon, Board motherBoard) {
        super(icon);
        this.coor = coor;
        this.motherBoard = motherBoard;
        this.addActionListener(new ButtonListener());
    }

    public Coordinate2D getCoor() {
        return coor;
    }

    public MyImageIcon getIcon(){
        return (MyImageIcon)super.getIcon();
    }

    public Board getMotherBoard() {
        return motherBoard;
    }

    /**
     * 'Select' or 'Deselect' the square
     * means to flip between the frog with and without the yellow border
     * @exception RuntimeException when the icon of the square is not either a frog with or without yellow border
     */
    public void select(){
        switch (getIcon().getRole()){
            case "redFrog":
                setIcon(Icons.redFrogSelected);break;
            case "greenFrog":
                setIcon(Icons.greenFrogSelected);break;
            case "redFrogSelected":
                setIcon(Icons.redFrog);break;
            case "greenFrogSelected":
                setIcon(Icons.greenFrog);break;
            default:
                throw new RuntimeException("Not a selectable square");
        }
    }

    /**
     * @return the icon is an either red or green frog
     */
    public boolean isFrog(){
        return getIcon().getRole().equals("redFrog") || getIcon().getRole().equals("greenFrog");
    }

    /**
     * change the icon to a pad if icon is not water
     * @exception RuntimeException when icon is water
     */
    public void set2Pad(){
        if(!getIcon().getRole().equals("water"))
            setIcon(Icons.pad);
        else
            throw new RuntimeException("Not a pad");
    }
}
