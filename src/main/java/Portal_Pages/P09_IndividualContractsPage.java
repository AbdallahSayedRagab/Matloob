package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P09_IndividualContractsPage {
    public P09_IndividualContractsPage (WebDriver driver){this.driver = driver ;}
    private WebDriver driver;

    // ترتيب التابات: ساري، بانتظار الرد، ...
    private final By WaitingForReplyTab = By.xpath("(//button[@role='tab'])[2]");
    private final By SearchField = By.name("opportunity_name");
    private By ViewContractButtonByOpportunityName (String opportunityName) {
        return By.xpath("//div[@role='tabpanel'][not(contains(@class,'hidden'))]"
                + "//p[contains(normalize-space(),'" + opportunityName + "')]/following-sibling::button");
    }
    private final By AcceptOfferButton = By.xpath("(//button[normalize-space()='قبول العرض'])[1]");
    private final By AcceptOfferButtonInPopUp = By.xpath("//div[@role='dialog']//button[normalize-space()='قبول العرض']");


    public P09_IndividualContractsPage Select_WaitingForReplyTab () {
        Utility.CLICK_TAB(driver,WaitingForReplyTab);
        return this;
    }

    public P09_IndividualContractsPage SearchForOpportunity (String opportunityName) {
        Utility.SENDKEYS(driver,SearchField,opportunityName);
        // البحث بيتحدّث في اللينك بعد لحظة وبيعيد تحميل الصفحة فيرجّع التاب للأول، فنستناه يخلص
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("opportunity_name"));
        return this;
    }

    public P09_IndividualContractsPage OpenTheContract (String opportunityName) {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ViewContractButtonByOpportunityName(opportunityName));
        return this;
    }

    public P09_IndividualContractsPage AcceptTheOffer () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptOfferButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptOfferButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }
}
