package Backoffice_Pages;

import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P6_OrganizersPage {
    public P6_OrganizersPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By SearchBar = By.xpath("//input[@type=\"search\"]");
    private final By SearchButton = By.xpath("//button[normalize-space()='بحث']");
    private final By SuspendButton = By.xpath("//button[normalize-space()='إيقاف']");
    private final By SuspendReasonTextArea = By.xpath("//textarea[@id=\"confirm-dialog-text\"]");
    private final By ConfirmSuspendButton = By.xpath("//div[@role='dialog']//button[normalize-space()='Continue']");

    private By ViewButtonByFacilityName(String facilityName) {
        return By.xpath("//tr[td[normalize-space()='" + facilityName + "']]//a[normalize-space()='عرض']");
    }


    public P6_OrganizersPage SearchAndSuspendLastFacility (String Reason) throws InterruptedException {
        String facilityName = DataUtiles.getJsonData("Data", "LastFacilityName");
        Utility.SENDKEYS(driver,SearchBar,facilityName);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SearchButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ViewButtonByFacilityName(facilityName));
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SuspendButton);
        Utility.SENDKEYS(driver,SuspendReasonTextArea,Reason);
        Utility.CLICKONVISIBLEELEMENTS(driver,ConfirmSuspendButton);
        return this;
    }
}
