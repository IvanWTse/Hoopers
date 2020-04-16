package View;

import Control.ButtonListener;
import Control.Coordinate2D;
import Control.MyImageIcon;

import javax.swing.*;

public class Square extends JButton {
    private final Coordinate2D coor;
    private final Board motherBoard;

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
                try {
                    throw new Exception("Not a selectable square");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public boolean isFrog(){
        return getIcon().getRole().equals("redFrog") || getIcon().getRole().equals("greenFrog");
    }

    public void set2Pad(){
        if(!getIcon().getRole().equals("water"))
            setIcon(Icons.pad);
        else
            try {
                throw new Exception("Not a pad");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
