package StepDefenitions;

import Base.BaseUtil;
import PageObjects.TeamPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.util.*;

public class HelperClass extends BaseUtil{

    private BaseUtil base;

    public HelperClass(BaseUtil base2) {
        this.base = base2;
    }


    //Variables
    LinkedHashMap<String, String> map_allTab = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String> map_OtherTabs = new LinkedHashMap<String, String>();

    LinkedHashMap<String, String> list_departmentTrue = new LinkedHashMap<String, String>();
    LinkedHashMap<String,String> list_departmentFalse = new LinkedHashMap<String, String>();

    List<String> header_all = new ArrayList<String>();
    List<String> header_main = new ArrayList<String>();

    List<WebElement> socialMedia_all = null;
    List<WebElement> socialMedia_division = null;

    ArrayList<String> list_names = new ArrayList<String>();

    int socialMedia_divCount = 0;


    // Function used to obtain the values from "ALL" tab
    public void ObtainValuesFromAllTab() {

        List<WebElement> allTabNames = base.Driver.findElements(By.cssSelector(TeamPage.elements_allTabName));
        List<WebElement> allTabDivision = base.Driver.findElements(By.cssSelector(TeamPage.elements_allTabDivision));

        createMap(map_allTab,allTabNames,allTabDivision);
        socialMedia_all = base.Driver.findElements(By.cssSelector(TeamPage.elements_allSocialProfile));

    }

    // Function used to obtain the values from all Other tabs
    public void ObtainValuesFromIndividualTab() {

        repositionPage();

        //#################### DATA TAB ####################
        // click data tab
        base.Driver.findElement(By.cssSelector(TeamPage.dataTab)).click();
        sleep();

        List<WebElement> dataTabNames = base.Driver.findElements(By.cssSelector(TeamPage.elements_dataTabName));
        List<WebElement> dataTabDivision = base.Driver.findElements(By.cssSelector(TeamPage.elements_dataTabDivision));

        createMap(map_OtherTabs,dataTabNames,dataTabDivision);

        socialMedia_division = base.Driver.findElements(By.cssSelector(TeamPage.elements_dataSocialProfile));
        socialMedia_divCount = socialMedia_divCount + socialMedia_division.size();


        //#################### Engineering TAB ####################
        // click engineering tab
        base.Driver.findElement(By.cssSelector(TeamPage.engineeringTab)).click();
        sleep();

        List<WebElement> enggTabNames = base.Driver.findElements(By.cssSelector(TeamPage.elements_enggTabName));
        List<WebElement> enggTabDivision = base.Driver.findElements(By.cssSelector(TeamPage.elements_enggTabDivision));

        createMap(map_OtherTabs,enggTabNames,enggTabDivision);


        socialMedia_division = base.Driver.findElements(By.cssSelector(TeamPage.elements_enggSocialProfile));
        socialMedia_divCount = socialMedia_divCount + socialMedia_division.size();


        //#################### Marketing TAB ####################
        // click marketing tab
        base.Driver.findElement(By.cssSelector(TeamPage.marketingTab)).click();
        sleep();

        List<WebElement> marketingTabNames = base.Driver.findElements(By.cssSelector(TeamPage.elements_marTabName));
        List<WebElement> marketingTabDivision = base.Driver.findElements(By.cssSelector(TeamPage.elements_marTabDivision));

        createMap(map_OtherTabs,marketingTabNames,marketingTabDivision);

        socialMedia_division = base.Driver.findElements(By.cssSelector(TeamPage.elements_marSocialProfile));
        socialMedia_divCount = socialMedia_divCount + socialMedia_division.size();


        //#################### Operations TAB ####################
        // click operations tab
        base.Driver.findElement(By.cssSelector(TeamPage.operationsTab)).click();
        sleep();

        List<WebElement> operationsTabNames = base.Driver.findElements(By.cssSelector(TeamPage.elements_opsTabName));
        List<WebElement> operationsTabDivision = base.Driver.findElements(By.cssSelector(TeamPage.elements_opsTabDivision));

        createMap(map_OtherTabs,operationsTabNames,operationsTabDivision);

        socialMedia_division = base.Driver.findElements(By.cssSelector(TeamPage.elements_opsSocialProfile));
        socialMedia_divCount = socialMedia_divCount + socialMedia_division.size();


        //#################### Partner-Growth TAB ####################
        // click partner-growth tab
        base.Driver.findElement(By.cssSelector(TeamPage.partnerGrowthTab)).click();
        sleep();

        List<WebElement> partnerGrowthTabNames = base.Driver.findElements(By.cssSelector(TeamPage.elements_partnGrowTabName));
        List<WebElement> partnerGrowthTabDivision = base.Driver.findElements(By.cssSelector(TeamPage.elements_partnGrowTabDivision));

        createMap(map_OtherTabs,partnerGrowthTabNames,partnerGrowthTabDivision);

        socialMedia_division = base.Driver.findElements(By.cssSelector(TeamPage.elements_partGrowSocialProfile));
        socialMedia_divCount = socialMedia_divCount + socialMedia_division.size();


        //#################### Product TAB ####################
        // click product tab
        base.Driver.findElement(By.cssSelector(TeamPage.productTab)).click();
        sleep();

        List<WebElement> productTabNames = base.Driver.findElements(By.cssSelector(TeamPage.elements_productTabName));
        List<WebElement> productTabDivision = base.Driver.findElements(By.cssSelector(TeamPage.elements_productTabDivision));

        createMap(map_OtherTabs,productTabNames,productTabDivision);

        socialMedia_division = base.Driver.findElements(By.cssSelector(TeamPage.elements_productSocialProfile));
        socialMedia_divCount = socialMedia_divCount + socialMedia_division.size();


        //#################### Recruiting TAB ####################
        // click recruiting tab
        base.Driver.findElement(By.cssSelector(TeamPage.recruitingTab)).click();
        sleep();

        List<WebElement> recruitingTabNames = base.Driver.findElements(By.cssSelector(TeamPage.elements_recruitingTabName));
        List<WebElement> recruitingTabDivision = base.Driver.findElements(By.cssSelector(TeamPage.elements_recruitingTabDivision));

        createMap(map_OtherTabs,recruitingTabNames,recruitingTabDivision);

        socialMedia_division = base.Driver.findElements(By.cssSelector(TeamPage.elements_recruitingSocialProfile));
        socialMedia_divCount = socialMedia_divCount + socialMedia_division.size();

    }

    // Helper function that creates a LinkedHashMap with a key value pair for employees/department
    public void createMap (LinkedHashMap<String, String> map, List<WebElement> names, List<WebElement> departments) {

        LinkedHashMap<String,String> pmap = map;
        List<WebElement> listNames = names;
        List<WebElement> listDept = departments;
        String name = "";
        String dept = "";

        for (int i = 0; i < listNames.size(); i++) {


            name = listNames.get(i).getText();
            dept = listDept.get(i).getText();

            pmap.put(name, dept);

        }

    }

    //Performs the comparision between All tab and Other tabs
    public void verifyDataBetweenTabs () {


        list_names.clear();
        list_departmentTrue.clear();
        list_departmentFalse.clear();

        for (Map.Entry<String, String> entry : map_allTab.entrySet()) {

            String key = entry.getKey();
            String pair = entry.getValue();
            String value = "";


            if (map_OtherTabs.containsKey(key)){

                value = map_OtherTabs.get(key);

                if (!value.equals(pair)){

                    list_departmentFalse.put(key,value);
                    list_departmentTrue.put(key,pair);

                }
            } else {

                list_names.add(key);

            }

        }

    }

    //Returns the total number of employees in ALL tab
    public int sumOfAllTab () {

        int sumAllTab = map_allTab.size();
        return sumAllTab;
    }

    //Returns the total number of employees in Other tabs
    public int sumOfOtherTabs () {

        int sumOtherTabs = map_OtherTabs.size();
        return sumOtherTabs;
    }

    //Returns the list of missing names that is not present in the Other tabs (vs All tab)
    public ArrayList<String> compareNamesBetweenTabs () {

     return list_names;
    }

    //Returns the bad list of employee/department if department is mismatched between All tab and Other tabs
    public LinkedHashMap<String,String> compareDepartmentBetweenTabs_false () {

        return list_departmentFalse;
    }


    //Returns the good list of employee/department if department is mismatched between All tab and Other tabs
    public LinkedHashMap<String,String> compareDepartmentBetweenTabs_true () {

        return list_departmentTrue;
    }

    //Returns the count for total number of employees having social media profile in All tab
    public int socialMedia_allTab () {

        int size = socialMedia_all.size();
        return size;
    }

    //Returns the count for total number of employees having social media profile in Other tabs
    public int socialMedia_otherTab () {

        return socialMedia_divCount;
    }

    //Returns the list of departments that are present in All tab and not present in the main category tab
    public List<String> unusedDepartment () {

        //Click All tab
        base.Driver.findElement(By.cssSelector(TeamPage.allTab)).click();

        List<WebElement> titleMain = base.Driver.findElements(By.cssSelector(TeamPage.mainCategories));
        List<WebElement> titleAllTab = base.Driver.findElements(By.cssSelector(TeamPage.elements_allTabDivision));

        Set<String> set_removeDuplicates = new HashSet();

        for (int i = 0; i < titleAllTab.size(); i++) {
            String names = titleAllTab.get(i).getText().toUpperCase();
            header_all.add(names);
        }

        for (int i = 0; i < titleMain.size(); i++) {
            String names = titleMain.get(i).getText();
            header_main.add(names);
        }

        //Remove duplicate values
        set_removeDuplicates.addAll(header_all);
        header_all.clear();
        header_all.addAll(set_removeDuplicates);

        List<String> title_allTab = new ArrayList<String>(header_all);
        List<String> title_Main = new ArrayList<String>(header_main);

        title_allTab.removeAll(header_main);

        return title_allTab;
    }


    // Scroll to WebElement
    public void repositionPage(){

        WebElement element = base.Driver.findElement(By.cssSelector(TeamPage.dataTab));
        Actions actions = new Actions(base.Driver);
        actions.moveToElement(element);
        actions.perform();

        sleep();

    }

    // Sleep function
    public void sleep(){

        long time = TeamPage.sleep1;

        try {
            Thread.sleep(time);

        } catch (InterruptedException ex) {

            System.out.println("Error! :");
        }

    }


}
