import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import task2.GooglePage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test2 {
	
	// declaring the search term
	private static final String SEARCH_TERM = "cheese";
	private static final int NUMB_REFERENCE = 777;
	WebDriver driver;
	
	@Before
	public void setup() throws IOException {
		System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
		File file = new File(".\\browser.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String browser_name = br.readLine();
		
		switch (browser_name) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			default: System.out.println("Browser not supported:" + browser_name);
		}
	}
	
	@Test
	public void CheeseTest() throws Exception {
		
		GooglePage googlePage = new GooglePage(driver);
		
		//navigating to Google search page and maximizing it
		driver.navigate().to(URLs.GOOGLE_PAGE);
		driver.manage().window().maximize();
		
		//inputting search term for search
		googlePage.inputSearchTerm(SEARCH_TERM);
		
		// waiting for the search results
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.srg h3")));
				
		
		String[] parts = googlePage.getNumbOfRes().split("\\("); // splitting string at "(" symbol to make sure that numbers of search duration don't end up with number of results
		String numbAndChar = parts[0]; // saving the first parth of a string without parentasis and content that was in them
		String numbString = numbAndChar.replaceAll("[^0-9]", ""); // removing everything that isn't number from 0-9
		int numb = Integer.parseInt(numbString);// converting the string into integer ant storing new integer into numb variable
		
		Assert.assertTrue("There is too much cheese on the internet", numb <= NUMB_REFERENCE); // asserting that number of results is lesser or equal than required number
		
		driver.close(); // closing the browser
	}
}











