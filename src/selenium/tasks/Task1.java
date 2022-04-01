package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
        String textToEnter = "WrongText";

//        TODO
        driver.findElement(By.id("numb")).sendKeys(textToEnter);
        driver.findElement(By.tagName("button")).click();

        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());

//        enter a text instead of a number, check that correct error is seen

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
        //BUG number 49 is accepted, no error message
        String textToEnter = "49";
        driver.findElement(By.id("numb")).sendKeys(textToEnter);
        driver.findElement(By.tagName("button")).click();

        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());

//        enter number which is too small (below 50), check that correct error is seen
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
        String textToEnter = "111";
        driver.findElement(By.id("numb")).sendKeys(textToEnter);
        driver.findElement(By.tagName("button")).click();
        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());

//        enter number which is too big (above 100), check that correct error is seen
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
        String textToEnter = "100";
        driver.findElement(By.id("numb")).sendKeys(textToEnter);
        driver.findElement(By.tagName("button")).click();


        Alert CorrectAlert = driver.switchTo().alert();
        assertEquals("Square root of 100 is 10.00", CorrectAlert.getText());
        driver.switchTo().alert().accept();

        System.out.println(driver.findElement(By.id("ch1_error")).getText());
        assertEquals("", driver.findElement(By.id("ch1_error")).getText()); //another Alternative is isDisplayed function


//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
    }

    @Test
    public void correctSquareRootWithRemainder() {

//        TODO
            String textToEnter = "77";
            driver.findElement(By.id("numb")).sendKeys(textToEnter);
            driver.findElement(By.tagName("button")).click();


            Alert CorrectAlert = driver.switchTo().alert();
            assertEquals("Square root of 77 is 8.77", CorrectAlert.getText());
            driver.switchTo().alert().accept();

            System.out.println(driver.findElement(By.id("ch1_error")).getText());
            assertEquals("", driver.findElement(By.id("ch1_error")).getText());
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
    }
}
