package stepdefinitions;
import io.cucumber.java.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import utility.ConfigReader;
import utility.ScreenshotUtil;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
public class RegistrationSteps {
    WebDriver driver;
    RegistrationPage reg;
    @Before
    public void setup() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.getProperty("url"));
        reg = new RegistrationPage(driver);

    }

    @Given("I open the Smart University registration page")
    public void openRegistrationPage() {
        // Already opened in setup()
    }

    @When("I register a student with the following details:")
    public void registerStudentVertical(DataTable data) {
        Map<String, String> studentData = data.asMap(String.class, String.class);
        reg.registerStudent(
                studentData.get("studName"),
                studentData.get("fatherName"),
                studentData.get("phone"),
                studentData.get("email"),
                studentData.get("department"),
                studentData.get("dob"),
                studentData.get("gender"),
                studentData.get("country"),
                studentData.get("state"),
                studentData.get("city"),
                studentData.get("sslc"),
                studentData.get("hsc")
        );
    }


    @Then("the student {string} with email {string} should appear in the table")
    public void validateTable(String name, String email) {
        try {
            Assertions.assertTrue(reg.isStudentInTable(name, email));

        } catch (AssertionError e) {
            ScreenshotUtil.capture(driver, "StudentTableFail");
            throw e;
        }
    }

    @And("I register the same student again with the same details:")
    public void registerSameStudentAgain(DataTable data) {
        Map<String, String> studentData = data.asMap(String.class, String.class);
        reg.registerStudent(
                studentData.get("studName"),
                studentData.get("fatherName"),
                studentData.get("phone"),
                studentData.get("email"),
                studentData.get("department"),
                studentData.get("dob"),
                studentData.get("gender"),
                studentData.get("country"),
                studentData.get("state"),
                studentData.get("city"),
                studentData.get("sslc"),
                studentData.get("hsc")
        );
    }

    @Then("I should see the student {string} with email {string} listed twice in the table")
    public void checkDuplicateStudentInTable(String name, String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("studTable")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");




        List<WebElement> rows = driver.findElements(By.cssSelector("#studTable > tbody > tr"));



        int matchCount = 0;
        for (WebElement row : rows) {
            if (row.getText().contains(name) && row.getText().contains(email)) {
                matchCount++;
            }
        }

        if (matchCount != 2) {

            throw new AssertionError("Expected 2 identical student records, but found " + matchCount);

        } else {



            ScreenshotUtil.capture(driver, "StudentDuplicateEntry");
            System.out.println("✅ Found 2 duplicate entries as expected.");
        }
    }


    @And("I click the submit button twice")
    public void iClickTheSubmitButtonTwice() {
        driver.findElement(By.id("addStudent-now")).click();
        driver.findElement(By.id("addStudent-now")).click();
    }

    @Then("I should see the student {string} with email {string} listed twice in the table.")
    public void iShouldSeeTheStudentWithEmailListedTwiceInTheTable(String name, String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("studTable")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");




        List<WebElement> rows = driver.findElements(By.cssSelector("#studTable > tbody > tr"));



        int matchCount = 0;
        for (WebElement row : rows) {
            if (row.getText().contains(name) && row.getText().contains(email)) {
                matchCount++;
            }
        }

        if (matchCount != 3) {

            throw new AssertionError("Expected 3 identical student records, but found " + matchCount);

        } else {



            ScreenshotUtil.capture(driver, "TripleClickSubmitButton");
            System.out.println("✅ Found 3 duplicate entries as expected.");
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }



}
