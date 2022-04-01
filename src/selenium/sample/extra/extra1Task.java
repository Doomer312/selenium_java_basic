package selenium.sample.extra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";
    String new_url = "https://kristinek.github.io/site/examples/link1";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void navigateBack() throws Exception {
//        TODO
        String pagepo1 = "https://kristinek.github.io/site/examples/po1";
        String pagepo = "https://kristinek.github.io/site/examples/po";
        driver.get("https://kristinek.github.io/site/examples/po");
//        open page with url "https://kristinek.github.io/site/examples/po"
        driver.findElement(By.xpath("//*[contains(@class, 'w3-pale-red')]//a[contains(text(), 'More')]")).click(); //by tag name
//        click "More > " for the top left element
      assertEquals(pagepo1, driver.getCurrentUrl());
      driver.navigate().back();
//        check that the url now "https://kristinek.github.io/site/examples/po1"
       assertEquals(pagepo, driver.getCurrentUrl());
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
//        check that the page now is "https://kristinek.github.io/site/examples/po"
        driver.navigate().forward();
        assertEquals(pagepo1, driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
//        TODO
        String pagepo1 = "https://kristinek.github.io/site/examples/po1";
        String pagepo = "https://kristinek.github.io/site/examples/po";
        driver.get("https://kristinek.github.io/site/examples/po");

//        open page with url "https://kristinek.github.io/site/examples/po"
//        click "More > " for the top left element
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
//        using driver navigation go forward to "https://kristinek.github.io/site/examples/po1"
//        check that the page now is "https://kristinek.github.io/site/examples/po1"
    }

    @Test
    public void refresh() throws Exception {
//        TODO
        driver.get(base_url);
//        open page "https://kristinek.github.io/site/examples/act"
        driver.findElement(By.id("show_text")).click();
//        click on "Show" button in 'Button' section
        assertEquals("I am here!", driver.findElement(By.id("show_me")).getText());
//        check that text "I am here!" is seen
        driver.navigate().refresh();
//        refresh page
        assertEquals("", driver.findElement(By.id("show_me")).getText());
//        check that text "I am here!" is not seen
    }
}
