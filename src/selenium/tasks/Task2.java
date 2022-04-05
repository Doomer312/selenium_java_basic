package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty
        assertEquals("", driver.findElement(By.id("fb_name")).getText());
        assertEquals("", driver.findElement(By.id("fb_age")).getText());
        assertEquals("", driver.findElement(By.name("comment")).getText());
//         and no tick are clicked
        WebElement English = driver.findElement(By.xpath("//*[@value='English']"));
        assertFalse(English.isSelected());
        WebElement French = driver.findElement(By.xpath("//*[@value='French']"));
        assertFalse(French.isSelected());
        WebElement Spanish = driver.findElement(By.xpath("//*[@value='Spanish']"));
        assertFalse(Spanish.isSelected());
        WebElement Chinese = driver.findElement(By.xpath("//*[@value='Chinese']"));
        assertFalse(Chinese.isSelected());
//         "Don't know" is selected in "Genre"
        assertEquals("Don't know (Disabled)", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][4]/label[@class='w3-validate'][3]")).getText());
//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)",
                driver.findElement(By.xpath("//*[@class='w3-btn-block w3-blue w3-section']")).getCssValue("background-color"));
    }


    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.xpath("//*[@class='w3-btn-block w3-blue w3-section']")).click();

//         check fields are empty or null
        assertEquals("Your name:", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][1]/p")).getText());
        assertEquals("Your age:", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][2]/p")).getText());
        assertEquals("Your language:", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][3]/p")).getText());
        assertEquals("Your genre: null", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][4]/p")).getText());
        assertEquals("Your option of us: null", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][5]/p")).getText());
        assertEquals("Your comment:", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][6]/p")).getText());
//         check button colors  green
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).getCssValue("background-color"));
        // check button colors  red
        assertEquals("rgba(244, 67, 54, 1)",
                driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).getCssValue("background-color"));
        //check buttons text colour - white
        assertEquals("rgb(255, 255, 255)",
                driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).getCssValue("text-decoration-color"));
        assertEquals("rgb(255, 255, 255)",
                driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).getCssValue("text-decoration-color"));

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
        //definining base url, Name, age, comment
        String base_url = "https://kristinek.github.io/site/tasks/provide_feedback";
        driver.get(base_url);
        String nameToEnter = "Dumbledore";
        String ageToEnter = "79";
        String commentToEnter = "5 million points to Gryffindor!";

        //Enter name, age, comment
        driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][1]/div[@class='w3-rest']/input[@id='fb_name']")).sendKeys(nameToEnter);
        driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][2]/div[@class='w3-rest']/input[@id='fb_age']")).sendKeys(ageToEnter);
        driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][6]/textarea[@class='w3-input w3-border']")).sendKeys(commentToEnter);

        //Language - English
        driver.findElement(By.xpath("//*[@value='English']")).click();
        //Genre - Male
        driver.findElement(By.xpath("//*[@value='male']")).click();

        //How do you like us dropdown - Select "Good"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        //click "Send"
        driver.findElement(By.xpath("//*[@class='w3-btn-block w3-blue w3-section']")).click();
        //Checking form

        assertEquals("Your name: Dumbledore", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][1]/p")).getText());
        assertEquals("Your age: 79", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][2]/p")).getText());
        assertEquals("Your language: English", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][3]/p")).getText());
        assertEquals("Your genre: male", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][4]/p")).getText());
        assertEquals("Your option of us: Good", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][5]/p")).getText());
        assertEquals("Your comment: 5 million points to Gryffindor!", driver.findElement(By.xpath("//*[@class='w3-row']/div[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-card-4']/div[@class='w3-container']/div[@class='description'][6]/p")).getText());
//         check button colors  green
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).getCssValue("background-color"));
        // check button colors  red
        assertEquals("rgba(244, 67, 54, 1)",
                driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).getCssValue("background-color"));
        //check buttons text colour - white
        assertEquals("rgb(255, 255, 255)",
                driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).getCssValue("text-decoration-color"));
        assertEquals("rgb(255, 255, 255)",
                driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).getCssValue("text-decoration-color"));

    }


    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
        //definining base url, Name
        String base_url = "https://kristinek.github.io/site/tasks/provide_feedback";
        driver.get(base_url);
        String nameToEnter = "Dumbledore";
        //Enter name only
        driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][1]/div[@class='w3-rest']/input[@id='fb_name']")).sendKeys(nameToEnter);

        //click "Send"
        driver.findElement(By.xpath("//*[@class='w3-btn-block w3-blue w3-section']")).click();
        //click "Yes"
        driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).click();
        //Checking message text
        assertEquals("Thank you, Dumbledore, for your feedback!", driver.findElement(By.xpath("//*[@id='message']")).getText());
        //Checking Color of text - white with green on the background
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.xpath("//*[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-panel w3-green']")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",
                driver.findElement(By.xpath("//*[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-panel w3-green']")).getCssValue("text-decoration-color"));

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
        //definining base url, Name
        String base_url = "https://kristinek.github.io/site/tasks/provide_feedback";
        driver.get(base_url);
        //Enter nothing
        //click "Send"
        driver.findElement(By.xpath("//*[@class='w3-btn-block w3-blue w3-section']")).click();
        //click "Yes"
        driver.findElement(By.xpath("//*[@class='w3-btn w3-green w3-xlarge']")).click();
        //Checking message text
        assertEquals("Thank you for your feedback!", driver.findElement(By.xpath("//*[@id='message']")).getText());
        //Checking Color of text - white with green on the background
        assertEquals("rgba(76, 175, 80, 1)",
                driver.findElement(By.xpath("//*[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-panel w3-green']")).getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",
                driver.findElement(By.xpath("//*[@class='w3-half']/div[@id='fb_thx']/div[@class='w3-panel w3-green']")).getCssValue("text-decoration-color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
        //definining base url, Name, age, comment
        String base_url = "https://kristinek.github.io/site/tasks/provide_feedback";
        driver.get(base_url);
        String nameToEnter = "Dumbledore";
        String ageToEnter = "79";
        String commentToEnter = "5 million points to Gryffindor!";

        //Enter name, age, comment
        driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][1]/div[@class='w3-rest']/input[@id='fb_name']")).sendKeys(nameToEnter);
        driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][2]/div[@class='w3-rest']/input[@id='fb_age']")).sendKeys(ageToEnter);
        driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][6]/textarea[@class='w3-input w3-border']")).sendKeys(commentToEnter);

        //Language - English
        driver.findElement(By.xpath("//*[@value='English']")).click();
        //Genre - Male
        driver.findElement(By.xpath("//*[@value='male']")).click();

        //How do you like us dropdown - Select "Good"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        //click "Send"
        driver.findElement(By.xpath("//*[@class='w3-btn-block w3-blue w3-section']")).click();
        //click "No"
        driver.findElement(By.xpath("//*[@class='w3-btn w3-red w3-xlarge']")).click();
        //Checking form
        assertEquals("Dumbledore", driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][1]/div[@class='w3-rest']/input[@id='fb_name']")).getAttribute("value"));
        assertEquals("79", driver.findElement(By.xpath("*//div[@class='w3-row']/div[@class='w3-half']/div[@id='fb_form']/form/div[@class='w3-row w3-section'][2]/div[@class='w3-rest']/input[@id='fb_age']")).getAttribute("value"));
        assertEquals("5 million points to Gryffindor!", driver.findElement(By.name("comment")).getAttribute("value"));
        //English selected
        assertTrue(driver.findElement(By.xpath("//*[@id='lang_check']/input[1]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id='lang_check']/input[2]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id='lang_check']/input[3]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id='lang_check']/input[4]")).isSelected());
        //Opinion check
        assertTrue(driver.findElement(By.xpath("//*[@id='like_us']/option[2]")).isSelected());
        //Gendre check
        assertTrue(driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[1]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[2]")).isSelected());
    }
    }

