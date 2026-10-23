package Backoffice_Pages;

import Portal_Pages.P04_EventsPage;
import Portal_Pages.P05_OpportunitiesPage;
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
    private final By OrganizersPageButton = By.xpath("//a[@href=\"/admin/organizers\"]");
    private final By ContentEditRequestsPageButton = By.xpath("//a[@href='/admin/content-change-requests']");



    public P3_EventsPage Select_EventsPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,EventsPageButton);
        return new P3_EventsPage(driver);
    }

    public P4_OpportunitiesPage Select_OpportunitiesPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,OpportunitiesPageButton);
        return new P4_OpportunitiesPage(driver);
    }

    public P5_FacilityPage Select_FacilityPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ReviewRequestPageButton);
        return new P5_FacilityPage(driver);
    }

    public P6_OrganizersPage Select_OrganizersPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,OrganizersPageButton);
        return new P6_OrganizersPage(driver);
    }

    public P7_ContentEditRequestsPage Select_ContentEditRequestsPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ContentEditRequestsPageButton);
        return new P7_ContentEditRequestsPage(driver);
    }
}
