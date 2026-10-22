package Backoffice_Pages;

import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P5_FacilityPage {
    public P5_FacilityPage(WebDriver driver) {this.driver = driver;}
    private WebDriver driver;
    // أزرار المراجعة: موافقة، رفض
    private final By AcceptButton = By.xpath("//*[contains(@class,'card actions')]/button[1]");
    // في الـ popup: إلغاء وبعده التأكيد
    private final By ConfirmAcceptButton = By.xpath("//div[@role='dialog']//button[last()]");
    private final By RejectButton = By.xpath("//*[contains(@class,'card actions')]/button[2]");
    private final By RejectReasonTextArea = By.id("confirm-dialog-text");
    private final By FollowToRejectButton = By.xpath("//div[@role='dialog']//button[last()]");




    // الفانكشن الديناميكية بتبني اللوكيتور بس
    private By GetReviewLinkByFacilityName(String facilityName) {
        return By.xpath("//tr[td[contains(text(),'" + facilityName + "')]]//a[contains(@class,'btn-ghost')]");
    }

    // الميثود اللي بتعمل الكليك، وبتقرا الاسم من الجيسون لوحدها
    public void ReviewAndAcceptLastFacility() {
        String facilityName = DataUtiles.getJsonData("Data", "LastFacilityName"); // الاسم الفريد اللي اتسجل في CreateNewFacility
        By reviewLink = GetReviewLinkByFacilityName(facilityName);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver, reviewLink);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver, AcceptButton);
        Utility.CLICKONVISIBLEELEMENTS(driver,ConfirmAcceptButton);


    }
    public void ReviewAndRejectLastFacility() {
        String facilityName = DataUtiles.getJsonData("Data", "LastFacilityName"); // الاسم الفريد اللي اتسجل في CreateNewFacility
        By reviewLink = GetReviewLinkByFacilityName(facilityName);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver, reviewLink);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver, RejectButton);
        Utility.SENDKEYS(driver,RejectReasonTextArea,"Nothing");
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FollowToRejectButton);


    }
}