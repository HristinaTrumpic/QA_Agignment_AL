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

public class OrangeHRMVacanciesPage {
    
    static WebDriver driver;
    
    public OrangeHRMVacanciesPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // --------------------------------------------
    // ****ELEMENT LOCATORS****
    // --------------------------------------------
    
    // menu button locator
    private static WebElement sideMenuBtn() {
        return driver.findElement(By.cssSelector("a.button-collapse.show-on-large"));
    }
    
    // candidates button locator
    private static WebElement candidatesBtn() {
        return driver.findElement(By.cssSelector("#slide-out > li:nth-child(7) > a"));
    }
    
    // --------------------------------------------
    // ****ACTIONS****
    // --------------------------------------------
    
    // click on side menu button
    public void clickSideMenuBtn() {
        sideMenuBtn().click();
    }
    
    // click on candidates button
    public void clickCandidatesBtn() {
        candidatesBtn().click();
    }
    
}