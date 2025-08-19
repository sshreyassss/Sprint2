package utility;

import java.io.*;
import java.util.Properties;
public class ConfigReader {
    static Properties prop;
    public static String getProperty(String key) throws IOException {
        if (prop == null) {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop = new Properties();
            prop.load(fis);
        }
        return prop.getProperty(key);
    }
}
