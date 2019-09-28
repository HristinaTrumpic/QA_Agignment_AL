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

import java.util.List;

public class OrangeHRMCandidatePage {
    
    static WebDriver driver;
    
    public OrangeHRMCandidatePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // --------------------------------------------
    // ****ELEMENT LOCATORS****
    // --------------------------------------------
    
    // Locator for the list of candidates
    private static List<WebElement> candidatesLoc() {
        return driver.findElements(By.cssSelector("tr.dataDefaultRaw.dataRaw.handCuser"));
    }
    
    // locator for add button for adding the applicants
    private static WebElement addBtn() {
        return driver.findElement(By.cssSelector("#addItemBtn > i"));
    }
    
    // first name field locator
    private static WebElement firstName() {
        return driver.findElement(By.name("addCandidate[firstName]"));
    }
    
    // last name field locator
    private static WebElement lastName() {
        return driver.findElement(By.name("addCandidate[lastName]"));
    }
    
    // email field locator
    private static WebElement email() {
        return driver.findElement(By.name("addCandidate[email]"));
    }
    
    // save button locator
    private static WebElement saveBtn() {
        return driver.findElement(By.id("saveCandidateButton"));
    }
    
    // drop down menu locator
    private static WebElement menuBtnLoc() {
        return driver.findElement(By.id("ohrmList_Menu"));
    }
    
    // Locator for items in drop down menu
    private static List<WebElement> menuItemsLoc() {
        return driver.findElements(By.cssSelector("ul#ohrmList_dropDownMenu li"));
    }
    
    // locator for button to confirm user delete
    private static WebElement confDeleteBtn() {
        return driver.findElement(By.cssSelector("a#candidate-delete-button"));
    }
    
    // locator for button to decline user delete
    private static WebElement declDeleteBtn() {
        return driver.findElement(By.cssSelector("a#candidate-delete-cancel-button"));
    }
    
    // locator for side menu
    private static WebElement sideMenuBtn() {
        return driver.findElement(By.cssSelector("a.button-collapse.show-on-large"));
    }
    
    // locator for user button
    private static WebElement userBtn() {
        return driver.findElement(By.cssSelector("a#welcome"));
    }
    
    // locator for logout button
    private static WebElement logoutBtn() {
        return driver.findElement(By.cssSelector("a.nova-left-menu-logout"));
    }
    
    // --------------------------------------------
    // ****ACTIONS****
    // --------------------------------------------
    
    // getting the list of Candidates
    public List<WebElement> getCandList() {
        return candidatesLoc();
    }
    
    // getting number of candidates
    public int getCandNumb() {
        return getCandList().size();
    }
    
    // clicking on add button to add applicant
    public void clickAddBtn() {
        addBtn().click();
    }
    
    // inputting first name in the required field
    public void inputName(String name){
        firstName().sendKeys(name);
    }
    
    // inputting last name in the required field
    public void inputLastName(String lastName){
        lastName().sendKeys(lastName);
    }
    
    // inputting email in the required field
    public void inputEmail(String email){
        email().sendKeys(email);
    }
    
    // click on save button
    public void clickSaveBtn() {
        saveBtn().click();
    }
    
    // clicking check box of the first candidate on the list
    public void clickCheckbox() {
        getCandList().get(0).findElement(By.cssSelector("tr > td.material-checkbox")).click();
    }
    
    // clicking check box of the first candidate
    public void clickMenuBtn() {
        menuBtnLoc().click();
    }
    
    // clicking delete from the drop down menu
    public void clickDelete() {
        menuItemsLoc().get(2).click();
    }
    
    // click on confirm delete button
    public void clickConfDeleteBtn() {
        confDeleteBtn().click();
    }
    
    // click on decline delete button
    public void clickDeclDeleteBtn() {
        declDeleteBtn().click();
    }
    
    // click on side menu button
    public void clickSideMenuBtn() {
        sideMenuBtn().click();
    }
    
    // click on user button
    public void clickUserBtn() {
        userBtn().click();
    }
    
    // click on logout button
    public void clickLogoutBtn() {
        logoutBtn().click();
    }
}