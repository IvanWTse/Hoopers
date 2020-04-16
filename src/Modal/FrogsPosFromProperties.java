package Modal;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class FrogsPosFromProperties {

    //The first element is the pos of red frog
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
