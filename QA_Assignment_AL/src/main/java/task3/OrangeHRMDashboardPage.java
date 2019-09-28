package task3;

/*  INFO
 *	-----------------------------------
 *   This class contains locators of the elements, required actions, some logic for getting required data
 *	 It is composed of two main parts: ELEMENT LOCATORS and ACTIONS
 * 	-----------------------------------
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrangeHRMDashboardPage {
    
    static WebDriver driver;
    
    public OrangeHRMDashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // --------------------------------------------
    // ****ELEMENT LOCATORS****
    // --------------------------------------------
    
    // recruitment button locator
    private static WebElement recruitmentBtn() {
        return driver.findElement(By.cssSelector("a#menu_recruitment_viewRecruitmentModule"));
    }
    
    // --------------------------------------------
    // ****ACTIONS****
    // --------------------------------------------
    
    // click on recruitment button
    public void clickRecruitmentBtn() {
        recruitmentBtn().click();
    }
    
}