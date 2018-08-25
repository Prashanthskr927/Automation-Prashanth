package Runner;

import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;

import java.io.File;


//@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/FeatureFiles"},
        glue={"StepDefenitions"},
        tags = {"@Numbers, @Names, @Departments, @MainDepts, @SocialProfile"},
        plugin = { "com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/Extent-Reports/ExtentReport.html"},
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {


        @AfterClass
        public static void writeExtentReport() {
                Reporter.loadXMLConfig(new File("src/test/java/runner/extent-config.xml"));

                Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
                Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
                Reporter.setSystemInfo("os", System.getProperty("os.name"));
                Reporter.setTestRunnerOutput("Branch metrics UI Automation Report");
        }
}
