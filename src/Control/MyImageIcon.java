package Control;

import javax.swing.*;

/**
 * @author IvanTse
 * @version 1.0
 * @description the icon class we will using in this project extended from ImageIcon
 */
public class MyImageIcon extends ImageIcon {
    private final String role; //The role description of the image icon

    /**
     * Constructor function.
     * @param URL the URL of the image file
     * @param role the role description of the image icon
     * @exception IllegalArgumentException when the role description is not a expecting one
     * @exception NullPointerException when the role description is null
     */
    public MyImageIcon(String URL, String role){
        super(URL);

        if(role == null)
            throw new NullPointerException();

        if(!legalRole(role))
            throw new IllegalArgumentException();

        this.role = role;
    }

    public String getRole() {
        return role;
    }

    /**
     * Check if the role description is an expecting value
     * @param role role description
     * @return true if it is an expecting value, false otherwise
     */
    private boolean legalRole(String role){
        if(role == null)
            throw new NullPointerException();
        return role.equals("greenFrog") || role.equals("greenFrogSelected") || role.equals("redFrog") || role.equals("redFrogSelected") || role.equals("pad") || role.equals("water");
    }
}
