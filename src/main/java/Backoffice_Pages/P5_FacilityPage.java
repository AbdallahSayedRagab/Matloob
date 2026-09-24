package Backoffice_Pages;

import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P5_FacilityPage {
    public P5_FacilityPage(WebDriver driver) {this.driver = driver;}
    private WebDriver driver;
    private final By AcceptButton = By.xpath("//button[text()='موافقة']");
    private final By ConfirmAcceptButton = By.xpath("//button[text()=' موافقة ']");
    private final By RejectButton = By.xpath("//button[text()='رفض']");
    private final By RejectReasonTextArea = By.xpath("//textarea[@id=\"confirm-dialog-text\"]");
    private final By FollowToRejectButton = By.xpath("//button[text()=' متابعة ']");




    // الفانكشن الديناميكية بتبني اللوكيتور بس
    private By GetReviewLinkByFacilityName(String facilityName) {
        return By.xpath("//tr[td[contains(text(),'" + facilityName + "')]]//a[contains(@class,'btn-ghost')]");
    }

    // الميثود اللي بتعمل الكليك، وبتقرا الاسم من الجيسون لوحدها
    public void ReviewAndAcceptLastFacility() {
        String facilityName = DataUtiles.getJsonData("Data", "FacilityName"); // غيّر المفتاح لو مختلف عندك
        By reviewLink = GetReviewLinkByFacilityName(facilityName);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver, reviewLink);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver, AcceptButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmAcceptButton);


    }
    public void ReviewAndRejectLastFacility() {
        String facilityName = DataUtiles.getJsonData("Data", "FacilityName"); // غيّر المفتاح لو مختلف عندك
        By reviewLink = GetReviewLinkByFacilityName(facilityName);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver, reviewLink);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver, RejectButton);
        Utility.SENDKEYS(driver,RejectReasonTextArea,"Nothing");
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FollowToRejectButton);


    }
}