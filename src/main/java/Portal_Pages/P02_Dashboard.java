package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_Dashboard {
    public P02_Dashboard (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By EventsPageButton = By.xpath("//a[@href='/dashboard/events']");
    private final By OpportunitiesPageButton = By.xpath("//a[@href='/dashboard/opportunities']");
    private final By ExplorePage = By.xpath("//a[@href=\"/individual/explore\"]");
    private final By AccountMenu = By.xpath("(//header//button[@aria-haspopup='menu'])[last()]");
    // تسجيل منشأة تالت اختيار في القايمة (الملف الشخصي، منشآتي، تسجيل منشأة)
    private final By RegisterAFacilityChoicePage_AtMenu = By.xpath("(//div[@role='menuitem'])[3]");
    // آخر زرار من غير type في الصفحة (قبله أزرار البيانات الناقصة لو موجودة)
    private final By CompleteProfileButton = By.xpath("(//main//button[not(@type)])[last()]");
    private final By IndividualContractsPage = By.xpath("//a[@href='/individual/contracts']");


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
        Utility.OPEN_MENU_AND_CLICK(driver,AccountMenu,RegisterAFacilityChoicePage_AtMenu);
        return new P06_FacilityPage(driver);
    }

    public P07_ExplorePage Select_ExplorePage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,ExplorePage);
        return new P07_ExplorePage(driver);
    }

    public P08_CompanyContractsPage Select_CompanyContractsPage () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,OpportunitiesPageButton);
        return new P08_CompanyContractsPage(driver);
    }

    public P09_IndividualContractsPage Select_IndividualContractsPage () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,IndividualContractsPage);
        return new P09_IndividualContractsPage(driver);
    }



}
