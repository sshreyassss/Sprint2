package pages;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;

import java.lang.Thread;



public class RegistrationPage {
    WebDriver driver;

    public WebDriver getDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    // Locators
    By studentName = By.id("studName");
    By fatherName = By.id("fatherName");
    By contact = By.id("phoneNo");
    By email = By.id("emaiId");
    By department = By.id("department");

    By dobDay = By.id("dob-day");
    By dobMonth = By.id("dob-month");
    By dobYear = By.id("dob-year");



    By genderMale = By.id("male");
    By genderFemale = By.id("female");
    By country = By.id("country");
    By state = By.id("state");
    By city = By.id("city");
    By sslc = By.id("sslcMark");
    By hsc = By.id("plusTwoMark");
    By photoUpload = By.id("file1");
    By registerBtn = By.id("addStudent-now");

    By lastRowName = By.xpath("/html/body/div[2]/div/div[2]/table/tbody/tr[2]/td[1]");
    By lastRowEmail = By.xpath("/html/body/div[2]/div/div[2]/table/tbody/tr[2]/td[4]");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void registerStudent(String nameVal, String father, String phone, String emailVal, String dept,
                                String dobVal, String gender, String ctry, String st, String cty,
                                String sslcVal, String hscVal) {
        // Inside registerStudent() method




        driver.findElement(studentName).clear();
        driver.findElement(studentName).sendKeys(nameVal);


        driver.findElement(fatherName).clear();
        driver.findElement(fatherName).sendKeys(father);

        driver.findElement(contact).clear();
        driver.findElement(contact).sendKeys(phone);


        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(emailVal);

        driver.findElement(department).sendKeys(dept);

        // Parse dobVal (format: dd/MM/yyyy)
        String[] dobParts = dobVal.split("/");
        String day = dobParts[0];
        String monthNum = dobParts[1];
        String year = dobParts[2];

        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        int monthIndex = Integer.parseInt(monthNum) - 1;  // convert "01" → 0
        String monthName = monthNames[monthIndex];


        // Use Select to choose dropdown values
        Select dayDropdown = new Select(driver.findElement(dobDay));
        Select monthDropdown = new Select(driver.findElement(dobMonth));
        Select yearDropdown = new Select(driver.findElement(dobYear));

        dayDropdown.selectByVisibleText(day);     // e.g., "01"
        monthDropdown.selectByVisibleText(monthName); // e.g., "01"
        yearDropdown.selectByVisibleText(year);   // e.g., "2000"



        if (gender.equalsIgnoreCase("Male")) {
            driver.findElement(genderMale).click();
        } else {
            driver.findElement(genderFemale).click();
        }

        driver.findElement(country).sendKeys(ctry);
        driver.findElement(state).sendKeys(st);
        driver.findElement(city).sendKeys(cty);

        driver.findElement(sslc).clear();
        driver.findElement(sslc).sendKeys(sslcVal);

        driver.findElement(hsc).clear();
        driver.findElement(hsc).sendKeys(hscVal);

        driver.findElement(photoUpload).click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("addStudent-now")).click();

    }

    public boolean isStudentInTable(String expectedName, String expectedEmail) {

        String actualName = driver.findElement(lastRowName).getText();
        String actualEmail = driver.findElement(lastRowEmail).getText();
        System.out.println("✅ Student record found: " + actualName + ", " + actualEmail);
        return actualName.equalsIgnoreCase(expectedName) && actualEmail.equalsIgnoreCase(expectedEmail);

    }

}
