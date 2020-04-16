package View;

import Control.MyImageIcon;

public class Icons{
    private final static String rootUrl = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    public final static MyImageIcon greenFrog = new MyImageIcon(rootUrl+"GreenFrog.png","greenFrog");
    public final static MyImageIcon greenFrogSelected = new MyImageIcon(rootUrl+"GreenFrog2.png","greenFrogSelected");
    public final static MyImageIcon redFrog = new MyImageIcon(rootUrl+"RedFrog.png","redFrog");
    public final static MyImageIcon redFrogSelected = new MyImageIcon(rootUrl+"RedFrog2.png","redFrogSelected");
    public final static MyImageIcon pad = new MyImageIcon(rootUrl+"LilyPad.png","pad");
    public final static MyImageIcon water = new MyImageIcon(rootUrl+"Water.png","water");

}
