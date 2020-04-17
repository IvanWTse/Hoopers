package Modal;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
/**
 * @author IvanTse
 * @version 1.0
 * @description Get the 1D position of the red frog and green frogs from Java properties file.
 * */
public class FrogsPosFromProperties {

    /**
     * Get the positions of frogs by the level number, and saving into an ArrayList.
     * @param level The level number 1..40
     * @return ArrayList, the first element is the pos of the red frog, rest are green frogs'
     */
    public static ArrayList<Integer> byLevel(int level) {
        if(level > 40)
            return null;
        ArrayList<Integer> result = new ArrayList<>();
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath()+"frogsPos.properties"));
            String[] key = properties.getProperty("level" + level).split("#");
            for(String value:key)
                result.add(Integer.parseInt(value));
        } catch (IOException e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

}
