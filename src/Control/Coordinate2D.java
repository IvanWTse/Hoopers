package Control;

/**
 * @author IvanTse
 * @version 1.0
 * @description Immutable co-ordinate class for squares on Board
 */
public class Coordinate2D {
    private final int x_pos; /* x coordinate */
    private final int y_pos; /* y coordinate */

    /**
     * Constructor
     * @param x_pos x coordinate
     * @param y_pos y coordinate
     */
    public Coordinate2D(int x_pos,int y_pos){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    /**
     * Convert the 2D co-ordinate to 1D co-ordinate in a 5x5 board
     * @return 1D co-ordinate in 5*5 started from 0
     */
    public int convert2OneD(){
        return (this.x_pos)+(this.y_pos*5);
    }

    /**
     * Static function, return a new Coordinate2D object by the given 1D coordinate in a 5x5 board
     * @param oneDCoor 1D coordinate
     * @return instance of Coordinate2D by the given 1D coordinate
     */
    public static Coordinate2D convertBack2D(int oneDCoor){
        return new Coordinate2D(oneDCoor%5,oneDCoor/5);
    }

}
