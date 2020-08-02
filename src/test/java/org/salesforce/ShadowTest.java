package org.salesforce;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ShadowTest {
    ChromeOptions ops = new ChromeOptions();


    WebDriver driver=new ChromeDriver();
    driver.get("https://nagsftest-dev-ed.lightning.force.com/");
}
