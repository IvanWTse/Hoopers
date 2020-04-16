package Control;

public class Coordinate2D {
    private final int x_pos;
    private final int y_pos;

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

    public int convert2OneD(){
        return (this.x_pos)+(this.y_pos*5);
    }

    public static Coordinate2D convertBack2D(int oneDCoor){
        return new Coordinate2D(oneDCoor%5,oneDCoor/5);
    }

}
