import multiScreenShot.MultiScreenShot;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import task3.OrangeHRMCandidatePage;
import task3.OrangeHRMDashboardPage;
import task3.OrangeHRMLoginPage;
import task3.OrangeHRMVacanciesPage;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Test3 {
	
	private static final String LAST_NAME = "Samo";
	private static final String EMAIL = "asdasd@asdfg.com";
	
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
	public void OrangeHRM() throws Exception {
		
		OrangeHRMLoginPage loginPage = new OrangeHRMLoginPage(driver);
		OrangeHRMDashboardPage dashboardPage = new OrangeHRMDashboardPage(driver);
		OrangeHRMVacanciesPage vacanciesPage = new OrangeHRMVacanciesPage(driver);
		OrangeHRMCandidatePage candidatePage = new OrangeHRMCandidatePage(driver);
		Date objDate = new Date(); // here current system date is assigned to objDate
		MultiScreenShot mShot = new MultiScreenShot(".\\", "Test3");
		//mShot.multiScreenShot(driver);
		
		//navigating to Orange HRM page and maximizing it
		driver.navigate().to(URLs.ORANGE_HRM);
		driver.manage().window().maximize();
		
		// waiting login button to load
		WebElement waitForLoginBtn = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.name("Submit")));
		
		//clicking login button
		loginPage.clickLoginBtn();
		
		// waiting for page to load (items that are loaded last)
		WebElement waitForHomePageToLoad = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".collection-item.avatar")));
		
		//clicking on recruitment button
		dashboardPage.clickRecruitmentBtn();
		
		// switching to iframe
		driver.switchTo().frame("noncoreIframe");
		
		// waiting for page to load
		WebElement waitForRecruitmentPageToLoad = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#content > div.navbar-fixed > nav > div > a.button-collapse.show-on-large")));
		Thread.sleep(1000);
		//clicking on menu button to open sidebar
		vacanciesPage.clickSideMenuBtn();
		
		Thread.sleep(700);
		// clicking on candidates button from menu sidebar
		vacanciesPage.clickCandidatesBtn();
		
		// waiting for page to load
		WebElement waitForCandidatesPageToLoad = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content > div:nth-child(10) > div > div.nova-content-wrap > table > thead > tr > th:nth-child(5)")));
		
		// storing candidates number in a variable candNumb and printing it to console
		int candNumbBeforeAdd = candidatePage.getCandNumb();
		System.out.println("Number of Cancicates on the list:" + candNumbBeforeAdd);
		
		//clicking on add button to register new candidate
		candidatePage.clickAddBtn();
		
		// waiting for floating page to load (field First Name)
		WebElement waitForLoginFloatPageToLoad = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(By.name("addCandidate[firstName]")));
		
		//converting java.util.Date to String for name of the new candidate
		String nameDate = objDate.toString();
		
		// inputting first name (date) into the required field
		candidatePage.inputName(nameDate);

		// inputting last name into the required field
		candidatePage.inputLastName(LAST_NAME);

		// inputting email into the required field
		candidatePage.inputEmail(EMAIL);

		// clicking on save button
		candidatePage.clickSaveBtn();
		
		// storing candidates number in a variable candNumb and printing it to console
		int candNumbAfterAdd = candidatePage.getCandNumb();
		System.out.println("Number of candidates on list after adding a Candidate: " + candNumbAfterAdd);
		
		// waiting for page to load
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.name("addCandidate[firstName]")));
		
		// taking screenshot if candidate isn't added to the list
		if ((candNumbBeforeAdd + 1) != candNumbAfterAdd) {
			mShot.multiScreenShot(driver);
		}
		
		// asserting that candidate is added to the list
		assertEquals(candNumbBeforeAdd + 1, candNumbAfterAdd);
		

		// clicking on the check box to select candidate
		candidatePage.clickCheckbox();

		// clicking on menu button to uncover drop down menu
		candidatePage.clickMenuBtn();

		// slicking on Delete field from drop down menu to delete the candidate
		candidatePage.clickDelete();

		// waiting for button to be clickable
		WebElement waitButtonToBeClickable = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#candidate-delete-button")));

//		// declining to delete candidate
//		candidatePage.clickDeclDeleteBtn();

		// confirming the delete of candidate
		candidatePage.clickConfDeleteBtn();
		
		Thread.sleep(5000);

		// storing candidates number in a variable candNumb and printing it to console
		int candNumbAfterDelete = candidatePage.getCandNumb();
		System.out.println("Candidate number after delete: " + candNumbAfterDelete);
		
		// taking screenshot if candidate isn't deleted from the list
		if ((candNumbBeforeAdd - 1) != candNumbAfterAdd) {
			mShot.multiScreenShot(driver);
		}

		// asserting that candidate is deleted from the list
		assertEquals(candNumbAfterAdd - 1, candNumbAfterDelete);
		
		// waiting for button to be clickable
		WebElement waitMenuButtonToBeClickable = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button-collapse.show-on-large")));
		
		// opening the side menu
		candidatePage.clickSideMenuBtn();
		
		// clicking on user button
		candidatePage.clickUserBtn();
		
		// logging out by clicking logout button
		candidatePage.clickLogoutBtn();
		
		driver.close(); // closing the browser
		
	}
}