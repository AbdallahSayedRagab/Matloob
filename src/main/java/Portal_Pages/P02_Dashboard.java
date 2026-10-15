package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_Dashboard {
    public P02_Dashboard (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By EventsPageButton = By.xpath("//span[text()='الفعاليات']");
    private final By OpportunitiesPageButton = By.xpath("//span[text()='الفرص']");
    private final By ExplorePage = By.xpath("//a[@href=\"/individual/explore\"]");
    private final By AccountMenu = By.xpath("(//button[@aria-haspopup=\"menu\"])[2]");
    private final By RegisterAFacilityChoicePage_AtMenu = By.xpath("//div[text()='تسجيل منشأة']");
    private final By CompleteProfileButton = By.xpath("//button[normalize-space()='إكمال الملف الشخصي']");


    public P03_ProfilePage Select_CompleteProfilePage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CompleteProfileButton);
        return new P03_ProfilePage(driver);
    }

    public P04_EventsPage Select_EventsPage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,EventsPageButton);
        return new P04_EventsPage(driver);
    }

    public P05_OpportunitiesPage Select_OpportunitiesPage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,OpportunitiesPageButton);
        return new P05_OpportunitiesPage(driver);
    }

    public P06_FacilityPage Select_RegisterAFacilityPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AccountMenu);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,RegisterAFacilityChoicePage_AtMenu);
        return new P06_FacilityPage(driver);
    }

    public P07_ExplorePage Select_ExplorePage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,ExplorePage);
        return new P07_ExplorePage(driver);
    }



}
