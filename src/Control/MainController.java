package Control;

import Modal.FrogsPosFromProperties;
import View.Board;
import View.Icons;
import View.Square;

import javax.swing.*;
import java.util.ArrayList;

public class MainController {
    //Static functions part
    public static boolean moving(Square origin, Square dest, Board motherBoard){
        //Security check
        if(origin == null || dest == null || motherBoard == null)
            throw new NullPointerException();

        //Get the coordinates of squares
        Coordinate2D originCoor = origin.getCoor();
        Coordinate2D destCoor = dest.getCoor();

        //Check if the dest is a pad
        if(!dest.getIcon().getRole().equals("pad")){
            origin.select();
            return false;
        }

        //Horizontal one step moving check
        if(((originCoor.getY_pos() == destCoor.getY_pos()&&Math.abs(originCoor.getX_pos()-destCoor.getX_pos()) == 2)
                || (originCoor.getX_pos() == destCoor.getX_pos() && Math.abs(originCoor.getY_pos() - destCoor.getY_pos()) == 2)) && dest.getIcon().getRole().equals("pad")) {

            //Move it
            origin.select();
            moveIcon(origin,dest);

            //return true if it's empty
            return true;
        }
        //Check a crossing moving
        else if((originCoor.getX_pos() - destCoor.getX_pos() == originCoor.getY_pos() - destCoor.getY_pos()
                || originCoor.getX_pos() - destCoor.getX_pos() == destCoor.getY_pos() - originCoor.getY_pos())
                && Math.abs(originCoor.getY_pos() - destCoor.getY_pos()) == 1){

            //Move it
            origin.select();
            moveIcon(origin,dest);

            return true;
        }

        //Check a horizontal jumping
        else if(Math.abs(originCoor.getX_pos()-destCoor.getX_pos()) == 4 && originCoor.getY_pos() == destCoor.getY_pos()){

            //The pos of the frog between
            int middleSquarePos = (new Coordinate2D((originCoor.getX_pos()+destCoor.getX_pos())/2,originCoor.getY_pos()).convert2OneD());

            //return the success of green frog removing
            return removeFrog(middleSquarePos, motherBoard, origin, dest);
        }

        //Check a vertical jumping
        else if(Math.abs(originCoor.getY_pos() - destCoor.getY_pos()) == 4 && originCoor.getX_pos() == destCoor.getX_pos()){

            //The pos of the frog between
            int middleSquarePos = (new Coordinate2D(originCoor.getX_pos(),(destCoor.getY_pos()+originCoor.getY_pos())/2)).convert2OneD();

            //return the success of green frog removing
            return removeFrog(middleSquarePos, motherBoard, origin, dest);
        }

        //Check a crossing jumping
        else if((originCoor.getX_pos() - destCoor.getX_pos() == originCoor.getY_pos() - destCoor.getY_pos()
                ||originCoor.getX_pos() - destCoor.getX_pos() == destCoor.getY_pos() - originCoor.getY_pos())
                && Math.abs(originCoor.getY_pos() - destCoor.getY_pos()) == 2){

            //The pos of the frog between
            int middleSquarePos = (new Coordinate2D((originCoor.getX_pos()+destCoor.getX_pos())/2,(originCoor.getY_pos()+destCoor.getY_pos())/2)).convert2OneD();

            //return the success of green frog removing
            return removeFrog(middleSquarePos, motherBoard, origin, dest);
        }

        //Not in any cases
        else {
            //Deselect the origin square
            origin.select();

            return false;
        }
    }

    private static boolean removeFrog(int middleSquarePos, Board motherBoard, Square origin, Square dest){

        if(motherBoard.getSquares()[middleSquarePos].getIcon().getRole().equals("greenFrog")) {

            //Remove the frog in the middle square
            //Change the middle square to a pad one
            motherBoard.getSquares()[middleSquarePos].set2Pad();

            //Move the frog
            origin.select();
            moveIcon(origin,dest);
            if(motherBoard.decreaseAFrog() == 0)
                motherBoard.getController().nextLevel();

            return true;

        }
        //The middle square is not a green frog
        //Therefore not able to do a crossing moving
        else {
            //Deselect the origin square
            origin.select();

            return false;
        }
    }

    private static void moveIcon(Square origin, Square dest){
        dest.setIcon(origin.getIcon());
        origin.set2Pad();
    }

    private static boolean linearSearchInArray(int[] arr, int key){
        for(int i:arr)
            if(i == key)
                return true;
        return false;
    }



    //Controller part
    private final Board motherBoard;
    private int level;

    public MainController(){
        this.level = 0;

        motherBoard = new Board(this);
        nextLevel();
    }

    public int getLevel(){
        return level;
    }

    private void nextLevel() {
        level += 1;
        //get the squares of the next level from modal
        ArrayList<Integer> frogsPos = FrogsPosFromProperties.byLevel(level);
        if(frogsPos == null){
            JOptionPane.showMessageDialog(motherBoard,"You have passed all forty levels");
            System.exit(0);
        }
        //Alert to the next level
        JOptionPane.showMessageDialog(motherBoard,"Congrats! Here's to level "+ level + "/40. ");
        int level1RedFrogAt = frogsPos.remove(0);
        int[] level1GreenFrogsAt  = frogsPos.stream().mapToInt(i -> i).toArray();
        motherBoard.newGame(squaresGenerator(level1GreenFrogsAt,level1RedFrogAt),level1GreenFrogsAt.length);

    }

    private Square[] squaresGenerator(int[] level1GreenFrogsAt, int level1RedFrogAt) {
        Square[] squares = new Square[25];
        for(int i = 0;i < 25;i++){
            if(i%2 == 1)
                squares[i] = new Square(Coordinate2D.convertBack2D(i), Icons.water, motherBoard);
            else if(linearSearchInArray(level1GreenFrogsAt,i))
                squares[i] = new Square(Coordinate2D.convertBack2D(i), Icons.greenFrog, motherBoard);
            else if(level1RedFrogAt == i)
                squares[i] = new Square(Coordinate2D.convertBack2D(i), Icons.redFrog, motherBoard);
            else
                squares[i] = new Square(Coordinate2D.convertBack2D(i), Icons.pad, motherBoard);
        }
        return squares;
    }

    public static void main(String[] args){
        new MainController();
    }

}
