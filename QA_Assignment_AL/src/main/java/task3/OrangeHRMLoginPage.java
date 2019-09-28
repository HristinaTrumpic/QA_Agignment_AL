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

public class OrangeHRMLoginPage {
    
    static WebDriver driver;
    
    public OrangeHRMLoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // --------------------------------------------
    // ****ELEMENT LOCATORS****
    // --------------------------------------------
    
    // login button locator
    private static WebElement loginBtn() {
        return driver.findElement(By.name("Submit"));
    }
    
    // --------------------------------------------
    // ****ACTIONS****
    // --------------------------------------------
    
    // click on login button
    public void clickLoginBtn() {
        loginBtn().click();
    }
    
}