package StepDefenitions;

import Base.BaseUtil;
import com.sun.javafx.PlatformUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Hook extends BaseUtil {

        private BaseUtil base;

        public Hook(BaseUtil base) {
            this.base = base;
        }


    @Before
    public void InitializeTest()
    {
        String osType ="";
        if (PlatformUtil.isWindows()){
            osType = "WINDOWS";
        } else if (PlatformUtil.isLinux()) {
            osType = "LINUX";
        } else if (PlatformUtil.isMac()) {
            osType = "MAC";
        }

        System.out.println("Opening the Browser: Chrome");

        if (osType == "WINDOWS") {
            System.setProperty("webdriver.chrome.driver","src/driver/chromedriver.exe");
            base.Driver = new ChromeDriver();
            base.Driver.manage().window().maximize();

        } else if (osType == "LINUX") {
            System.setProperty("webdriver.chrome.driver","src/driver/chromedriverLinux");
            base.Driver = new ChromeDriver();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            base.Driver = new ChromeDriver(chromeOptions);

        } else if (osType == "MAC") {
            System.setProperty("webdriver.chrome.driver","src/driver/chromedriverMac");
            base.Driver = new ChromeDriver();
            base.Driver.manage().window().fullscreen();
        }

    }

    @After
    public void TearDownTest(Scenario scenario)
    {
        if (scenario.isFailed())
        //Take Screenshot
        System.out.println(scenario.getName());
        System.out.println("Closing the Browser");
        base.Driver.close();
    }
}