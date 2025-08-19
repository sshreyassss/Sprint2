package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
public class ScreenshotUtil {
    public static void capture(WebDriver driver, String filename) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots/" + filename + ".png");
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}
