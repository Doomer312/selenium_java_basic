package selenium.sample;


import org.junit.Test;
import org.openqa.selenium.WebDriver; // launches a server which communicates through API with WeBdriver and browser
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver

//         go to https://kristinek.github.io/site/index2.html
//         get title of page
//         get URL of current page
//         close browser
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Master\\Documents\\Bootcamp Day 5\\TABootcampJava2022\\selenium_java_basic\\lib\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(); //driver is name and variable, doesnt matter. If we dont specify path, it wont know
        driver.get("https://kristinek.github.io/site/index2.html");
        System.out.println("Title: " +driver.getTitle());
        System.out.println("URL: " +driver.getCurrentUrl());
        Thread.sleep(10000);
        driver.quit();
    }
}
