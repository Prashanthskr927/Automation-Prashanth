package StepDefenitions;

import Base.BaseUtil;
import PageObjects.BranchPage;
import PageObjects.GoogleSearchPage;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchSteps extends BaseUtil {

    private BaseUtil base;
    public HelperClass helper;

    private String GoogleHomepage = "https://www.google.com";
    private String BranchHomePage = "https://branch.io/";

    public int total_AllTab =0;
    public int total_OtherTab =0;
    public int difference =0;

    public ArrayList<String> nameOfEmployees = new ArrayList<String>();
    public LinkedHashMap<String,String>  deptOfEmployees_invalid  = new LinkedHashMap<String, String>();
    public LinkedHashMap<String,String>  deptOfEmployees_valid  = new LinkedHashMap<String, String>();

    public int numberOfEmp =0;
    public int numberOfDept =0;

    public int numberOfSocial_all =0;
    public int numberOfSocial_other =0;


    public SearchSteps(BaseUtil base) {
        this.base = base;
        helper = new HelperClass(this.base);

    }


    @Given("^I navigate to Google website$")
    public void iNavigateToGoogleWebsite()
    {
        base.Driver.get(GoogleHomepage);
        Reporter.addStepLog("Google Page loaded Successfully");
    }


    @And("^I search and navigate \"([^\"]*)\" website$")
    public void iSearchAndNavigateWebsite(String string) throws Throwable {
        GoogleSearchPage GoogleSearch = new GoogleSearchPage(base.Driver);
        GoogleSearch.Search(string);
        GoogleSearch.LinkText();
        helper.sleep();
        Reporter.addStepLog("Branch Metrics website found Successfully");
    }

    @And("^I assert Branch website$")
    public void iAssertBranchWebsite(){
        String URL = base.Driver.getCurrentUrl();
        Assert.assertEquals(URL, BranchHomePage);

        Reporter.addStepLog("Verified Branch Website");
    }


    @When("^I scroll down to the footer section$")
    public void iScrollDownToTheFooterSection() throws Throwable {

        JavascriptExecutor js = ((JavascriptExecutor) base.Driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        helper.sleep();
    }

    @Then("^I find and click on teams link$")
    public void iFindAndClickOnTeamsLink() throws Throwable {

        BranchPage branchPage = new BranchPage(base.Driver);
        branchPage.TeamLink();
    }



    @When("^I count the sum of employees in 'All' tab$")
    public void iCountTheSumOfEmployeesInAllTab() throws Throwable {

        helper.sleep();

        JavascriptExecutor js = (JavascriptExecutor) base.Driver;
        js.executeScript("javascript:window.scrollBy(250,400)");

        helper.ObtainValuesFromAllTab();
        total_AllTab =helper.sumOfAllTab();
        Reporter.addStepLog("Sum of employees in 'All' tab :"+total_AllTab);

    }

    @And("^I count the sum of employees in other tabs$")
    public void iCountTheSumOfEmployeesInOtherTabs() throws Throwable {

        helper.ObtainValuesFromIndividualTab();
        total_OtherTab = helper.sumOfOtherTabs();
        Reporter.addStepLog("Sum of employees in 'Other' tabs :"+total_OtherTab);

    }

    @Then("^I Verify number of employees match between All tab and sum of other tabs$")
    public void iVerifyNumberOfEmployeesMatchBetweenAllTabAndSumOfOtherTabs() throws Throwable {

        difference = total_AllTab -total_OtherTab;
        Reporter.addStepLog("Number of employees missing in other tabs :" + difference);
        Assert.assertEquals(total_AllTab, total_OtherTab);

    }



    @And("^I Verify employee names match between All tab and other tabs$")
    public void iVerifyEmployeeNamesMatchBetweenAllTabAndOtherTabs() throws Throwable {

        helper.verifyDataBetweenTabs();
        nameOfEmployees = helper.compareNamesBetweenTabs();
        numberOfEmp = nameOfEmployees.size();

        Reporter.addStepLog("List of employee(s) missing in other tabs :"+numberOfEmp);

        for (int i=0; i<nameOfEmployees.size(); i++){
            Reporter.addStepLog(nameOfEmployees.get(i));
        }

        Assert.assertEquals(0, numberOfEmp);

    }


    @And("^I Verify employee departments are listed correctly between All tab and Department tabs$")
    public void iVerifyEmployeeDepartmentsAreListedCorrectlyBetweenAllTabAndDepartmentTabs() throws Throwable {

        helper.verifyDataBetweenTabs();
        deptOfEmployees_invalid = helper.compareDepartmentBetweenTabs_false();
        deptOfEmployees_valid = helper.compareDepartmentBetweenTabs_true();
        numberOfDept = deptOfEmployees_valid.size();

        Reporter.addStepLog("List of employee(s) department mis-match in other tabs :"+numberOfDept);
        Reporter.addStepLog("In-Valid Data :"+numberOfDept);
        for (Map.Entry<String, String> entry : deptOfEmployees_invalid.entrySet()) {

            Reporter.addStepLog(entry.getKey()+","+entry.getValue());
        }
        Reporter.addStepLog("Valid Data :"+numberOfDept);
        for (Map.Entry<String, String> entry : deptOfEmployees_valid.entrySet()) {

            Reporter.addStepLog(entry.getKey()+","+entry.getValue());
        }

        Assert.assertEquals(0, numberOfDept);
    }


    @And("^I Verify employee with social profiles listed between All tab and other tabs$")
    public void iVerifyEmployeeWithSocialProfilesListedBetweenAllTabAndOtherTabs() throws Throwable {

        numberOfSocial_all = helper.socialMedia_allTab();
        numberOfSocial_other = helper.socialMedia_otherTab();

        Reporter.addStepLog("Number of employees in All tab listed with Social Media :"+numberOfSocial_all);
        Reporter.addStepLog("Number of employees in Other tabs listed with Social Media :"+numberOfSocial_other);

        Assert.assertEquals(numberOfSocial_all, numberOfSocial_other);


    }


    @And("^I Verify employee departments are listed correctly between All tab and Department list tabs$")
    public void iVerifyEmployeeDepartmentsAreListedCorrectlyBetweenAllTabAndDepartmentListTabs() throws Throwable {


        List<String> listOfDept;
        int sizeOfDept =0;
        listOfDept = helper.unusedDepartment();
        sizeOfDept = listOfDept.size();

        Reporter.addStepLog("List of missing departments :"+sizeOfDept);

        for (int i=0; i<listOfDept.size(); i++){
            Reporter.addStepLog(listOfDept.get(i));
        }

        Assert.assertEquals(0, sizeOfDept);

    }



}



