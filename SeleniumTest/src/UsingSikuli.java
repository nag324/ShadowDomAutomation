import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;



public class UsingSikuli {
	
	static WebDriver driver;
	
	 public static void main(String[] args) throws FindFailed 
	 {
		 
		 String filepath = "C:\\VisualAutomation\\SeleniumTest\\files\\";

		 ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-notifications");	 
		   System.setProperty("webdriver.chrome.driver", "C:\\chrome\\latest\\chromedriver.exe");
		    driver = new ChromeDriver(ops);
		   WebDriverWait wait = new WebDriverWait(driver,10);

		   Screen s=new Screen();
		   Pattern fileInputTextBox = new Pattern(filepath + "text.PNG");
		   
		   driver.get("chrome://downloads/");
		   driver.manage().window().maximize();
		   s.type(fileInputTextBox, "Hi");
		   s.x = 17;
		   s.y = 138;
		   s.h = 20;
		   s.w = 100;
		  
		   s.highlight(3);   
		   Region r = s.grow();
		   r.highlight(5);
		   System.out.println("*****"+r.text()+"****");
		   
		   
		   
		   
	 }

}
