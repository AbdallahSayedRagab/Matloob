package Backoffice_Pages;

import Portal_Pages.P03_EventsPage;
import Portal_Pages.P04_OpportunitiesPage;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P2_Dashboard {
    public P2_Dashboard (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By EventsPageButton = By.xpath("//a[@href=\"/admin/event-review\"]");
    private final By OpportunitiesPageButton = By.xpath("//a[@href=\"/admin/opportunity-review\"]");
    private final By ReviewRequestPageButton = By.xpath("//a[@href=\"/admin/review-queue\"]");



    public P3_EventsPage Select_EventsPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,EventsPageButton);
        return new P3_EventsPage(driver);
    }

    public P04_OpportunitiesPage Select_OpportunitiesPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,OpportunitiesPageButton);
        return new P04_OpportunitiesPage(driver);
    }

    public P5_FacilityPage Select_FacilityPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ReviewRequestPageButton);
        return new P5_FacilityPage(driver);
    }
}
