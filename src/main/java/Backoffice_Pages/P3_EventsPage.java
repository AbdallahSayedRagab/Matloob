package Backoffice_Pages;

import Portal_Pages.P04_EventsPage;
import Portal_Pages.P05_OpportunitiesPage;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P3_EventsPage {
    public P3_EventsPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By SearchBar = By.xpath("//input[@type=\"search\"]");
    private final By FirstReviewButton = By.xpath("//a[contains(@href,'/admin/event-review/')]");
    // أزرار المراجعة: موافقة ونشر، رفض، إعادة للتعديل
    private final By AcceptAndPublishButton = By.xpath("//*[contains(@class,'card actions')]/button[1]");
    // في الـ popup: إلغاء وبعده التأكيد
    private final By ConfirmAcceptButton = By.xpath("//div[@role='dialog']//button[last()]");


    public P3_EventsPage ReviewAndAcceptLastEvent (String NameOfEvent) throws InterruptedException {
        Utility.SENDKEYS(driver,SearchBar,NameOfEvent);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstReviewButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptAndPublishButton);
        Utility.CLICKONVISIBLEELEMENTS(driver,ConfirmAcceptButton);
        return this;
    }

    public P3_EventsPage Select_OpportunitiesPage () throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptAndPublishButton);
        return this;
    }
}
