package task1.googlePage;

/*  INFO
 *	-----------------------------------
 *   In this class there are locators of the elements, required actions, some logic for getting required data
 * 	-----------------------------------
 */

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
    
    // locators for elements to store in the list
    private static List<WebElement> searchResults() {
        return driver.findElements(By.cssSelector("div.srg h3"));
    }
    
    // --------------------------------------------
    // ****ACTIONS****
    // --------------------------------------------
    
    // inputting search term in search field and activating search with ENTER key
    public void inputSearchTerm(String searchTerm){
        searchBar().sendKeys(searchTerm);
        searchBar().sendKeys(Keys.ENTER);
    }
    
    // getting list of search results
    public List<WebElement> getSearchResults() {
        return searchResults();
    }

    // click on desired search result
    public void clickSearchResult(int i) {
        getSearchResults().get(i).click();
    }
    
}