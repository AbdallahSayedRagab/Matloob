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
    private final By FirstReviewButton = By.xpath("//a[contains(@href,'/admin/opportunity-review/')]");
    // أزرار المراجعة: موافقة ونشر، رفض، إعادة للتعديل
    private final By AcceptAndPublishButton = By.xpath("//*[contains(@class,'card actions')]/button[1]");
    // في الـ popup: إلغاء وبعده التأكيد
    private final By ConfirmAcceptButton = By.xpath("//div[@role='dialog']//button[last()]");
    // زرار الرفض هو الزرار الأحمر في أزرار المراجعة
    private final By RejectButton = By.xpath("//*[contains(@class,'card actions')]/button[contains(@class,'btn-danger')]");
    private final By RejectReasonTextArea = By.id("confirm-dialog-text");
    // في الـ popup: إلغاء ومتابعة (الأحمر)
    private final By FollowToRejectButton = By.xpath("//div[@role='dialog']//button[contains(@class,'btn-danger')]");


    public P4_OpportunitiesPage ReviewAndAcceptLastOpportunity (String NameOfOpportunity) throws InterruptedException {
        Utility.SENDKEYS(driver,SearchBar,NameOfOpportunity);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstReviewButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptAndPublishButton);
        Utility.CLICKONVISIBLEELEMENTS(driver,ConfirmAcceptButton);
        return this;
    }

    public P4_OpportunitiesPage ReviewAndRejectLastOpportunity (String NameOfOpportunity , String Reason) throws InterruptedException {
        Utility.SENDKEYS(driver,SearchBar,NameOfOpportunity);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstReviewButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,RejectButton);
        Utility.SENDKEYS(driver,RejectReasonTextArea,Reason);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FollowToRejectButton);
        return this;
    }

}
