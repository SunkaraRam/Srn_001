package Practice.bvbvb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","D:\\LatestDrivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		
		Thread.sleep(5000);
		
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		
		allLinks.addAll(driver.findElements(By.tagName("img")));
		
		List<WebElement> activeLinks = new ArrayList<WebElement>();
		
		List<WebElement> deactiveLinks = new ArrayList<WebElement>();
		
		for (int i = 0; i < allLinks.size(); i++) {
			
			if (allLinks.get(i).getAttribute("href")!=null &&(!allLinks.get(i).getAttribute("href").contains("javascript"))) {
				
				activeLinks.add(allLinks.get(i));
				
			}else {
				deactiveLinks.add(allLinks.get(i));
			}
			
		}
		
		System.out.println("All Links  ----->" + allLinks.size());
		
		System.out.println("Active Links  ----->" + activeLinks.size());
		
		for (int j = 0; j < deactiveLinks.size(); j++) {
			
		System.out.println("DL _ "+deactiveLinks.get(j).getAttribute("href"));
			
		HttpURLConnection connect =	 (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href")).openConnection();
			
		connect.connect();
		
		String res = connect.getResponseMessage();
		
		connect.disconnect();
		
		System.out.println("AL _ "+activeLinks.get(j).getAttribute("href")+"-----"+res);
		
		}
		
	}
	
}
