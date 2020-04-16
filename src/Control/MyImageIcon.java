package Control;

import org.jetbrains.annotations.Contract;

import javax.swing.*;

public class MyImageIcon extends ImageIcon {
    private final String role;
    public MyImageIcon(String URL, String role){
        super(URL);
        if(legalRole(role))
            this.role = role;
        else
            throw new IllegalArgumentException();
    }

    public String getRole() {
        return role;
    }

    @Contract("null -> fail")
    private boolean legalRole(String role){
        if(role == null)
            throw new NullPointerException();
        return role.equals("greenFrog") || role.equals("greenFrogSelected") || role.equals("redFrog") || role.equals("redFrogSelected") || role.equals("pad") || role.equals("water");
    }
}
