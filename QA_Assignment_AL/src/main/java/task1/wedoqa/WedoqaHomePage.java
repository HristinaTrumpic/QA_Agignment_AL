package task1.wedoqa;

/*  INFO
 *	-----------------------------------
 *   This class contains locators of the elements, required actions, some logic for getting required data
 *	 It is composed of two main parts: ELEMENT LOCATORS and ACTIONS
 * 	-----------------------------------
 */

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class WedoqaHomePage {
    
    static WebDriver driver;
    
    public WedoqaHomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // --------------------------------------------
    // ****ELEMENT LOCATORS****
    // --------------------------------------------
    
    // locator for elements in testimonials slider
    private static List<WebElement> sliderElList() {
        return driver.findElements(By.cssSelector("div.carousel-testimonial"));
    }
    
    //Locator for EverSave card
    private static WebElement eversaveLoc() {
		return driver.findElement(By.cssSelector("#illdy_testimonial-20 > div > div.testimonial-meta > h6"));
	}
	
	//Locator for SimplyMap card
    private static WebElement simplymapLoc() {
        return driver.findElement(By.cssSelector("#illdy_testimonial-6 > div > div.testimonial-meta > h6"));
    }
	
	//Locator for Pattern Publishing card
    private static WebElement patternPublishingLoc() {
        return driver.findElement(By.cssSelector("#illdy_testimonial-10 > div > div.testimonial-meta > h6"));
    }
    
	// locators for team members in the team section
    private static List<WebElement> teamMembersList() {
        return driver.findElements(By.cssSelector("#team h6"));
    }
	
	//Locator for Blog link located in navigation menu
	private static WebElement blogLinkLoc() {
		return driver.findElement(By.cssSelector("#menu-item-3127 a"));
	}
	
	//Locator for Search Bar on the Blog page
	private static WebElement searchBar() {
		return driver.findElement(By.name("s"));
	}
	
	//Locator for articles on the blog page
	private static List<WebElement> articleList() {
		return driver.findElements(By.cssSelector("article"));
	}
    
    // --------------------------------------------
    // ****ACTIONS****
    // --------------------------------------------
    
    // getting list of elements from the slider
    public List<WebElement> getSliderEl() {
        return sliderElList();
    }
    
    // getting number of elements in the list
    public int sliderElLength() {
        return sliderElList().size();
    }
    
    //eversave for .isDisplayed()
    public WebElement eversave() {
        return eversaveLoc();
    }
    
    // getting text from eversave card to compare for a match
    public String eversaveText() {
        return eversaveLoc().getText();
    }
    
    //simplymap for .isDisplayed()
    public WebElement simplymap() {
        return simplymapLoc();
    }
    
    // getting text from simplymap card to compare for a match
    public String simplymapText() {
        return simplymapLoc().getText();
    }
    
    //patern pudlishing for .isDisplayed()
    public WebElement patternPublishing() {
        return patternPublishingLoc();
    }
    
    // getting text from pattern publishing card to compare for a match
    public String patternPublishingText() {
        return patternPublishingLoc().getText();
    }
    
    // getting list of members from the team section
    public List<WebElement> getTeamMember() {
        return teamMembersList();
    }
    
    // getting number of members in the list
    public int numbOfTeamMembers() {
        return teamMembersList().size();
    }
    
    //Logic for getting names of members from the list and concatenating all the names onto one String
    public String namesOfTeamMembers() {
        String names = new String();
        for (int i = 0; i < numbOfTeamMembers(); i++) {
            WebElement name =  getTeamMember().get(i);
            names += name.getText();
        }
        return names;
    }
    
    //Logic for counting how many occurrences of character c are in given String s
    public int numOfOccurences(String s, char c) {
    	int counter = 0;
    	for (int i = 0; i < s.length(); i++) {
    		if (s.charAt(i) == c) {
				counter ++;
			}
		}
    	return counter;
	}
	
	// clicking on the Blog link
	public void clickBlogLink() {
		blogLinkLoc().click();
	}
	
	// inputting search term into search field and activating search with ENTER key
	public void inputSearchTerm(String searchTerm){
		searchBar().sendKeys(searchTerm);
		searchBar().sendKeys(Keys.ENTER);
	}
	
	// getting article list
	public List<WebElement> getArticleList() {
		return articleList();
	}
	
	// getting specific article from the list of articles
	public WebElement getSpecificArtisle(int i) {
		return getArticleList().get(i);
	}
	
	//getting date from the first article on the top
	public String getDate() {
    	WebElement d = getSpecificArtisle(0).findElement(By.cssSelector("time"));
    	String date = d.getText();
    	return date;
	}
}






