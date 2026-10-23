package Backoffice_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P7_ContentEditRequestsPage {
    public P7_ContentEditRequestsPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By SearchBar = By.xpath("//input[@type='search']");
    // زرار مراجعة في نفس صف الفعالية أو الفرصة
    private By ReviewButtonByName (String name) {
        return By.xpath("//tr[td[contains(.,'" + name + "')]]//a[contains(@href,'/admin/content-change-requests/')]");
    }
    // أزرار المراجعة: اعتماد التعديل، رفض
    private final By ApproveEditButton = By.xpath("//*[contains(@class,'actions')]/button[1]");
    // في الـ popup: إلغاء وبعده اعتماد
    private final By ConfirmApproveEditButton = By.xpath("//div[@role='dialog']//button[last()]");


    public P7_ContentEditRequestsPage ReviewAndApproveLastEditRequest (String Name) throws InterruptedException {
        Utility.SENDKEYS(driver,SearchBar,Name);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ReviewButtonByName(Name));
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ApproveEditButton);
        Utility.CLICKONVISIBLEELEMENTS(driver,ConfirmApproveEditButton);
        return this;
    }
}
