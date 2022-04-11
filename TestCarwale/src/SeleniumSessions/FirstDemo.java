package SeleniumSessions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/// To say system that my chromedriver is located at the mentioned location
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Darshan\\Documents\\chromedriver.exe");
		
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.facebook.com/");
		
		System.out.println(driver.getTitle());
		
	}

}