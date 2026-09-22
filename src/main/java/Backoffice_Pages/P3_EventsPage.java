package Backoffice_Pages;

import Portal_Pages.P03_EventsPage;
import Portal_Pages.P04_OpportunitiesPage;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P3_EventsPage {
    public P3_EventsPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By SearchBar = By.xpath("//input[@type=\"search\"]");
    private final By FirstReviewButton = By.xpath("(//a[text()=' مراجعة '])[1]");
    private final By AcceptAndPublishButton = By.xpath("//button[text()=' موافقة ونشر ']");
    private final By ConfirmAcceptButton = By.xpath("//button[text()=' موافقة ']");


    public P3_EventsPage ReviewAndAcceptLastEvent (String NameOfEvent) throws InterruptedException {
        Utility.SENDKEYS(driver,SearchBar,NameOfEvent);
        Utility.CLICKONELEMENTS(driver,FirstReviewButton);
        Utility.CLICKONELEMENTS(driver,AcceptAndPublishButton);
        Utility.CLICKONELEMENTS(driver,ConfirmAcceptButton);
        return this;
    }

    public P3_EventsPage Select_OpportunitiesPage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,AcceptAndPublishButton);
        return this;
    }
}
