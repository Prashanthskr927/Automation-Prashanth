package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {

    public GoogleSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "lst-ib")
    public WebElement searchText;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "mobile linking platform")
    public WebElement linkText;

    public void Search(String string){
        searchText.sendKeys(string);
        searchText.sendKeys(Keys.RETURN);
    }

    public void LinkText(){
        linkText.click();
    }
}
