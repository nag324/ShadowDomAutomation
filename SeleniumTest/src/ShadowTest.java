import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.sukgu.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class ShadowTest {
	
	static WebDriver driver;
	
	 public static void main(String[] args) throws FindFailed 
	 {

		 String filepath = "C:\\VisualAutomation\\SeleniumTest\\files\\";
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-notifications");	 
		   System.setProperty("webdriver.chrome.driver", "C:\\chrome\\latest\\chromedriver.exe");
		    driver = new ChromeDriver(ops);
		    Shadow shadow = new Shadow(driver);
		    
		   WebDriverWait wait = new WebDriverWait(driver,10);
		   Screen s=new Screen();
		   Pattern shopBtn = new Pattern(filepath + "btn.PNG");
    
		   driver.get("https://shop.polymer-project.org/");
		   driver.manage().window().maximize();
		   s.click(shopBtn);
		   
		   

   
 	
 
    
    
    
	 }
	 
	 
	 

	
}
