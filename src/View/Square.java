package View;

import javax.swing.*;

public class Square extends JButton {
    private int x_coor;
    private int y_coor;
    private String url;

    public Square(int x_coor, int y_coor, String url) {
        super(new ImageIcon(url));
        this.x_coor = x_coor;
        this.y_coor = y_coor;
        this.url = url;
    }

    public int getX_coor() {
        return x_coor;
    }

    public void setX_coor(int x_coor) {
        this.x_coor = x_coor;
    }

    public int getY_coor() {
        return y_coor;
    }

    public void setY_coor(int y_coor) {
        this.y_coor = y_coor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        setIcon(new ImageIcon(url));
    }

}
