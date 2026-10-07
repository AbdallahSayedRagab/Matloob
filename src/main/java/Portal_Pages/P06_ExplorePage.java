package Portal_Pages;

import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_ExplorePage {
    public P06_ExplorePage (WebDriver driver){this.driver = driver ;}
    private WebDriver driver;

    private By ApplyButtonByOpportunityName (String opportunityName) {
        return By.xpath("//h3[contains(normalize-space(.),'" + opportunityName + "')]"
                + "/ancestor::div[.//span[normalize-space()='ارسال طلب التقديم']][1]"
                + "//span[normalize-space()='ارسال طلب التقديم']");
    }

    private final By FirstApplyButton = By.xpath("//span[text()='ارسال طلب التقديم']");
    private final By LastApplyButton = By.xpath("//span[text()='إرسال طلب التقديم']");



    public P06_ExplorePage ApplyForTheLastIndividualOpportunity () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ApplyButtonByOpportunityName(DataUtiles.getJsonData("Data","OpportunityName")));
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstApplyButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,LastApplyButton);
        return this;
    }
}
