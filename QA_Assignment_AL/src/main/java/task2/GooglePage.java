package task2;

/*  INFO
 *	-----------------------------------
 *   This class contains locators of the elements, required actions, some logic for getting required data
 *	 It is composed of two main parts: ELEMENT LOCATORS and ACTIONS
 * 	-----------------------------------
 */

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GooglePage {
    
    static WebDriver driver;
    
    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // --------------------------------------------
    // ****ELEMENT LOCATORS****
    // --------------------------------------------
    
    // search bar locator
    private static WebElement searchBar() {
        return driver.findElement(By.name("q"));
    }
    
    // locator for number of results found
    private static WebElement numbOfRes() {
        return driver.findElement(By.id("resultStats"));
    }
    
    // --------------------------------------------
    // ****ACTIONS****
    // --------------------------------------------
    
    // inputting search term in search field and activating search with ENTER key
    public void inputSearchTerm(String searchTerm) {
        searchBar().sendKeys(searchTerm);
        searchBar().sendKeys(Keys.ENTER);
    }
    
    // getting String with number of results of the result page
    public String getNumbOfRes() {
        return numbOfRes().getText();
    }
}
