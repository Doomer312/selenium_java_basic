package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

public class Sample2Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception { //document.querySelector("#heading_2")
//         TODO:
        System.out.println(driver.findElement(By.id("heading_2")).getText());
//         get text "Heading 2 text" using id
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
        WebElement randomButton = driver.findElement(By.name("randomButton2"));
        System.out.println("Button id:" +randomButton.getAttribute("id"));
        System.out.println("Button value:" +randomButton.getAttribute("value"));
//         get attribute "id" and "value" of button "This is also a button" using name "randombutton2"
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
        System.out.println(driver.findElement(By.className("test")).getText());
//         get first text of class "test" (should be "Test Text 1")
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
        List<WebElement> allElements = driver.findElements(By.className("test"));
        System.out.println("Size of class test: "+ driver.findElements(By.className("text")).size());
//         get size text of class "test" (should be 5)
        System.out.println("Size: " + allElements.size());
        System.out.println("All text:");
//
        for (WebElement element : allElements) {
            System.out.println(element.getText());
        }

//         get text of class "test"
        System.out.println("Third: " + allElements.get(2).getText());
//         get third text of class "test" (should be "Test Text 4")
    }
}
