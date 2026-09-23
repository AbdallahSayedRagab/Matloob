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
    private final By AccountMenu = By.xpath("//button[@id=\"radix-:r5:\"]");
    private final By RegisterAFacilityChoicePage_AtMenu = By.xpath("//div[text()='تسجيل منشأة']");


    public P03_EventsPage Select_EventsPage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,EventsPageButton);
        return new P03_EventsPage(driver);
    }

    public P04_OpportunitiesPage Select_OpportunitiesPage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,OpportunitiesPageButton);
        return new P04_OpportunitiesPage(driver);
    }

    public P05_FacilityPage Select_RegisterAFacilityPage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,AccountMenu);
        Utility.CLICKONELEMENTS(driver,RegisterAFacilityChoicePage_AtMenu);
        return new P05_FacilityPage(driver);
    }



}
