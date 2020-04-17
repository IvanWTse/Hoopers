package Control;

import Modal.FrogsPosFromProperties;
import View.Board;
import View.Icons;
import View.Square;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author IvanTse
 * @version 1.0
 * @description Main controller of the whole game, handle moving, remove and change icon to the squares
 */
public class MainController {

    //Static functions part

    /**
     * When receiving a moving order, check whether it is a legal moving, if true, complete the order
     * @param origin the object of origin square
     * @param dest the object of destination square
     * @param motherBoard the object of mother board
     * @return true when the moving is legal and completed, false otherwise
     * @exception NullPointerException when any from three parameters are null
     */
    public static boolean moving(Square origin, Square dest, Board motherBoard){
        //Security check
        //throw NullPointerException if any parameters are null
        if(origin == null || dest == null || motherBoard == null)
            throw new NullPointerException();

        //Get the coordinates from the squares in parameters
        Coordinate2D originCoor = origin.getCoor();
        Coordinate2D destCoor = dest.getCoor();

        //Check if the dest is a pad, if dest is not a pad
        //deselect the origin square and return false
        if(!dest.getIcon().getRole().equals("pad")){
            origin.select();
            return false;
        }

        /* Horizontal or vertical one step moving check.
          If the y-coors of dest and origin are the same and difference between x-coors of dest and origin is 2.
          OR if the x-coors of dest and origin are the same and difference between y-coors of dest and origin is 2.
          Then deselect the origin square and move the origin square to dest,
          finally return true
          */
        if(((originCoor.getY_pos() == destCoor.getY_pos()&&Math.abs(originCoor.getX_pos()-destCoor.getX_pos()) == 2)
                || (originCoor.getX_pos() == destCoor.getX_pos() && Math.abs(originCoor.getY_pos() - destCoor.getY_pos()) == 2))) {

            //Move it
            origin.select();
            moveIcon(origin,dest);

            //return true if it's empty
            return true;
        }

        /*Check a crossing moving
        If the absolute value of difference of x-coor between origin and dest is equal to absolute value of difference of y-coor between origin and dest and equals 1
        Then deselect the origin square and move the origin square to dest
        Finally return true
         */
        else if((Math.abs(originCoor.getX_pos() - destCoor.getX_pos()) == Math.abs(originCoor.getY_pos() - destCoor.getY_pos()))
                && Math.abs(originCoor.getY_pos() - destCoor.getY_pos()) == 1){

            //Move it
            origin.select();
            moveIcon(origin,dest);

            return true;
        }

        /*Check a horizontal jumping
        If the absolute value of difference of x-coor of origin and dest equals to 4 and x-coor of origin and dest are the same
        Then the middle square pos is average of x-coor of origin and dest and y-coor is the same
        Move origin to dest and remove the frog among them
         */
        else if(Math.abs(originCoor.getX_pos()-destCoor.getX_pos()) == 4 && originCoor.getY_pos() == destCoor.getY_pos()){

            //The pos of the frog between
            int middleSquarePos = (new Coordinate2D((originCoor.getX_pos()+destCoor.getX_pos())/2,originCoor.getY_pos()).convert2OneD());

            //return the success of green frog removing
            return removeFrog(middleSquarePos, motherBoard, origin, dest);
        }

        //Check a vertical jumping
        //Similar way
        else if(Math.abs(originCoor.getY_pos() - destCoor.getY_pos()) == 4 && originCoor.getX_pos() == destCoor.getX_pos()){

            //The pos of the frog between
            int middleSquarePos = (new Coordinate2D(originCoor.getX_pos(),(destCoor.getY_pos()+originCoor.getY_pos())/2)).convert2OneD();

            //return the success of green frog removing
            return removeFrog(middleSquarePos, motherBoard, origin, dest);
        }

        //Check a crossing jumping
        //Similar way
        else if((Math.abs(originCoor.getX_pos() - destCoor.getX_pos()) == Math.abs(originCoor.getY_pos() - destCoor.getY_pos()))
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

    /**
     * Remove the frog among origin and dest if the middle square is a green frog
     * and moving the square from origin to dest
     * @param middleSquarePos the 1D position of the middle square among
     * @param motherBoard the object of the mother board for callback
     * @param origin the object of the origin square
     * @param dest the object of the destination square
     * @return true when success moving squares and removing the middle one, false otherwise
     */
    private static boolean removeFrog(int middleSquarePos, Board motherBoard, Square origin, Square dest){

        //Check if the square between is a green frog
        if(motherBoard.getSquares()[middleSquarePos].getIcon().getRole().equals("greenFrog")) {
            //If true, goes here

            //Change the middle square to a pad one
            motherBoard.getSquares()[middleSquarePos].set2Pad();
            //Deselect the origin square
            origin.select();
            //Move the frog
            moveIcon(origin,dest);

            //If there are no left frogs on mother board, call nextLevel() in mother board
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

    /**
     * Change the icon in dest square to the icon of the origin square,
     * then set the origin square to a pad
     * @param origin the object of the origin square
     * @param dest the object of the destination square
     */
    private static void moveIcon(Square origin, Square dest){
        dest.setIcon(origin.getIcon());
        origin.set2Pad();
    }

    /**
     * Linear search in an array of int
     * @param arr array of int
     * @param key the key looking for in the array
     * @return true when found, false otherwise
     */
    private static boolean linearSearchInArray(int[] arr, int key){
        for(int i:arr)
            if(i == key)
                return true;
        return false;
    }



    //Controller part
    private final Board motherBoard; //The object of the mother board
    private int level; //the current level counter

    /**
     * Constructor function <br/ >
     * initialized the board with the controller itself
     * Set the current level as zero
     * Use nextLevel() to start a new game from Level 1
     */
    public MainController(){
        this.level = 0;

        motherBoard = new Board(this);
        nextLevel();
    }

    public int getLevel(){
        return level;
    }

    /**
     * Increase the level itself and start a new game with the level.
     * Get the positions of frogs from Modal, in an ArrayList of Integer.
     * If the ArrayList is null, it means user won all forty games and there are no more levels.
     * The first elements in the ArrayList indicates the position of the red frog
     * and the rest of elements indicates the position of the green frogs.
     * Use squareGenerator() to get an array of totally 25 squares,
     * then load them to the board.
     * @see FrogsPosFromProperties
     */
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

    /**
     * Create an array of 25 squares by the given array of green frogs position and position of the red frog.
     * @param level1GreenFrogsAt an array of 1D position of green frogs, all elements are even number between 0 and 24
     * @param level1RedFrogAt the 1D position of the red frog, between 0 and 24
     * @return array of 25 initialized squares with their corresponding icon
     * @exception NullPointerException when the parameter level1GreenFrogsAt is null
     * @exception RuntimeException when the values of parameteres are not expecting values. \\
     */
    private Square[] squaresGenerator(int[] level1GreenFrogsAt, int level1RedFrogAt) {

        //Security check
        if(level1GreenFrogsAt == null)
            throw new NullPointerException();
        if(level1RedFrogAt%2 == 1 || level1RedFrogAt > 24 || level1RedFrogAt < 0)
            throw new RuntimeException("level1RedFrogAt should be an even number between 0 and 24");
        for(int i = 0; i < level1GreenFrogsAt.length;i++)
            if(level1GreenFrogsAt[i]%2 == 1 || level1GreenFrogsAt[i] > 24 || level1GreenFrogsAt[i] < 0)
                throw new RuntimeException("level1GreenFrogsAt["+i+"] should be an even number between 0 and 24");

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
