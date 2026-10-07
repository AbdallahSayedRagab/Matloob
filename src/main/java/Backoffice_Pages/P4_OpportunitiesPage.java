package Backoffice_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P4_OpportunitiesPage {
    public P4_OpportunitiesPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By SearchBar = By.xpath("//input[@type=\"search\"]");
    private final By FirstReviewButton = By.xpath("(//a[text()=' مراجعة '])[1]");
    private final By AcceptAndPublishButton = By.xpath("//button[text()='موافقة ونشر']");
    private final By ConfirmAcceptButton = By.xpath("//button[text()=' موافقة ']");


    public P4_OpportunitiesPage ReviewAndAcceptLastOpportunity (String NameOfOpportunity) throws InterruptedException {
        Utility.SENDKEYS(driver,SearchBar,NameOfOpportunity);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstReviewButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptAndPublishButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmAcceptButton);
        return this;
    }

}
