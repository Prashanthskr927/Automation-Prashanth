package PageObjects;

import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class BranchPage extends BaseUtil {

    public BranchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static final String BranchLogo = "logo";

    public static final String TeamLink = "/html/body/div/footer/div/div/div[2]/div[1]/div[2]/ul/li[1]/ul/li[2]/a"; 

    public static final String AllTeams = "(\".//*[@class='info-block']//h2\"";



    @FindBy(how = How.ID, using = BranchLogo) public WebElement branchLogo;

    @FindBy(how = How.XPATH, using = TeamLink) public WebElement teamLink;

    @FindBy(how = How.XPATH, using = AllTeams) public WebElement allTeams;


    public void TeamLink(){
        teamLink.click();
    }


}
