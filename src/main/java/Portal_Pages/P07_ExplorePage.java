package Portal_Pages;

import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P07_ExplorePage {
    public P07_ExplorePage (WebDriver driver){this.driver = driver ;}
    private WebDriver driver;

    private By ApplyButtonByOpportunityName (String opportunityName) {
        return By.xpath("//h5[contains(normalize-space(.),'" + opportunityName + "')]"
                + "/ancestor::div[.//a[normalize-space()='قدم الآن']][1]"
                + "//a[normalize-space()='قدم الآن']");
    }

    private final By FirstApplyButton = By.xpath("//span[text()='ارسال طلب التقديم']");
    private final By LastApplyButton = By.xpath("//span[text()='إرسال طلب التقديم']");



    public P07_ExplorePage ApplyForTheLastIndividualOpportunity () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ApplyButtonByOpportunityName(DataUtiles.getJsonData("Data","LastOpportunityName")));
//        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstApplyButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,LastApplyButton);
        return this;
    }
}
