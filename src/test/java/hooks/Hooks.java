package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utility.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    @Before
    public void setUp() {
        WebDriver driver = new ChromeDriver();  // No WebDriverManager
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
