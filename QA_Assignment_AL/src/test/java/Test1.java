
import multiScreenShot.MultiScreenShot;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import task1.googlePage.GooglePage;
import org.junit.Before;
import org.junit.Test;
import task1.wedoqa.WedoqaHomePage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Test1 {
	
	// declaring the search term
	private static final String SEARCH_TERM = "wedoqa.com";
	private static final String SEARCH_TERM2 = "test";
	
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
	public void WedoqaTest() throws Exception {
		
		GooglePage googlePage = new GooglePage(driver);
		WedoqaHomePage wedoqaHomePage = new WedoqaHomePage(driver);
		Date objDate = new Date(); // here current system date is assigned to objDate
		MultiScreenShot mShot = new MultiScreenShot(".\\", "Test1");
		//mShot.multiScreenShot(driver);
		
		//navigating to Google search page and maximizing it
		driver.navigate().to(URLs.GOOGLE_PAGE);
		driver.manage().window().maximize();
		
		//inputting search term in google search and hitting enter
		googlePage.inputSearchTerm(SEARCH_TERM);
		
		// waiting for the search results to load in the google after search
		WebElement waitForResToLoad = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.srg h3")));
		
		//clicking on the first search result
		googlePage.clickSearchResult(0);
		Thread.sleep(2000);
		
		//scrolling element Testimonials into view
		WebElement element = driver.findElement(By.id("testimonials"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);

		//sliding element from right to left
		WebElement slider = driver.findElement(By.cssSelector("div.owl-stage-outer"));
		Actions moveSlider = new Actions(driver);
		Action action = moveSlider.clickAndHold(slider)
				.moveByOffset(10, 0)
				.release()
				.build();

		//evaluating if element is one of the required ones trough for loop
		for (int i = 0; i < wedoqaHomePage.sliderElLength(); i++) {
				Thread.sleep(500);
			boolean tEversave = (wedoqaHomePage.eversave().isDisplayed() && wedoqaHomePage.eversaveText().contentEquals("EverSave"));
			boolean tSimplymap = (wedoqaHomePage.simplymap().isDisplayed() && wedoqaHomePage.simplymapText().contentEquals("SimplyMap"));
			boolean tPatternPublishing = (wedoqaHomePage.patternPublishing().isDisplayed() && wedoqaHomePage.patternPublishingText().contentEquals("Pattern Publishing"));
			if (tEversave || tSimplymap || tPatternPublishing) {
				mShot.multiScreenShot(driver); //taking the screenshot
				Thread.sleep(500);
			}
			action.perform(); //performing slide action
		}
		
		// printing out number of letters appearing in the all team members names
		System.out.println("There are " + (wedoqaHomePage.numOfOccurences(wedoqaHomePage.namesOfTeamMembers().toLowerCase(), 't') + " T and t letters in names."));
		
		// clicking on Blog icon
		wedoqaHomePage.clickBlogLink();
		
		// waiting for Blog page to load
		WebElement waitBlogToLoad = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div.container")));
		
		mShot.multiScreenShot(driver); // taking a screenshot
		
		// inputting search term and hitting enter
		wedoqaHomePage.inputSearchTerm(SEARCH_TERM2);
		
		// this whole last section is for comparing date from the latest article to current date
//		String strDateFormat = "MMM dd, yyyy"; // date format is specified
//		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //date format string is passed as an argument to the date format object
		String date = wedoqaHomePage.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy"); // date format is specified
		String[] date_list = date.split(" ");//splitting date String on month, day and year
		date_list[0] = date_list[0].substring(0,3);
		// taking full name of month and getting only first three letters
		// (in some environments doesn't accept more than three characters for month format)
		date = date_list[0] + " " + date_list[1] + " " + date_list[2];
		Date dt_1 = sdf.parse(date); // getting the date from the article
		Date dt_2 = sdf.parse(sdf.format(objDate)); // formatting the date
		long diffMs = dt_2.getTime() - dt_1.getTime(); // subtracting current and article date
		Integer  diffMin = (int) (diffMs/(1000*60));
		Integer  diffH = (diffMin/60);
		Integer  diffD = diffH/24;
		Integer  diffM = diffD/30;
		Integer  diffY = diffM/12;
		diffMin = diffMin % 60;
		diffH   = diffH % 24;
		diffD   = diffD %30;
		diffM   = diffM % 12;
		String message =  "has passed since blog update";
		message = (diffMin == 0 ? "": diffMin.toString() + " minutes ") + message;
		message = (diffH == 0 ? "": diffH.toString() + " hours ") + message;
		message = (diffD == 0 ? "": diffD.toString() + " days ") + message;
		message = (diffM == 0 ? "": diffM.toString() + " months ") + message;
		message = (diffY == 0 ? "": diffY.toString() + " year ") + message;
		System.out.println(message);
		
		
		driver.close(); // closing the browser
	}
}