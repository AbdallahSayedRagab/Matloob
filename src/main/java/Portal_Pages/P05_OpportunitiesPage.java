package Portal_Pages;

import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OpportunitiesPage {
    public P05_OpportunitiesPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    // أول زرار أسود في الصفحة
    private final By CreateOpportunityButton = By.xpath("//main//button[contains(@class,'bg-black')]");
    private final By AcceptPolicyButton = By.id("guidelines_acknowledged");
    private final By NextButton = By.xpath("//span[text()='حفظ ومتابعة']");
    private By EventCardByName(String eventName) {
        return By.xpath("//span[contains(normalize-space(),'" + eventName + "')]" + "/ancestor::button[@role='radio'][1]");
    }

    private final By TempWorkSection = By.xpath("//span[text()='العمل المؤقت']/ancestor::button[1]");
    private final By SellerOpportunity = By.xpath("//button[normalize-space()='بائع']");
    private final By OpportunityNameField = By.xpath("//input[@placeholder=\"ادخل اسم الفرصة\"]");
    private final By OpportunityDescField = By.xpath("//textarea[@placeholder=\"أكتب نبذة مختصرة عن الفرصة\"]");
    private final By SalaryFromField = By.xpath("//input[@id=\"opportunities.0.salary_from\"]");
    private final By SalaryToField = By.xpath("//input[@id=\"opportunities.0.salary_to\"]");
    private final By AddOpportunityButton = By.xpath("//button[normalize-space()='إضافة الفرصة']");
    private final By PublishOpportunitiesButton = By.xpath("//span[text()='نشر الفرص']");
    private final By PublishOpportunityButtonInPopUp = By.xpath("//button[normalize-space()='نشر الفرصة']");
    private final By MyOpportunitiesTitle = By.xpath("//h1[text()='فرصي']");
    private final By LoadingCircle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    // ترتيب التابات: قيد الإجراء، نشطة، ...
    private final By ActiveOpportunitiesTab = By.xpath("(//button[@role='tab'])[2]");
    // اسم الفرصة نفسه لينك بيفتح صفحة الفرصة
    private By OpportunityByName (String opportunityName) {
        return By.xpath("//div[@role='tabpanel'][not(contains(@class,'hidden'))]//a[.='" + opportunityName + "']");
    }
    // زرار الإغلاق الأحمر في صفحة الفرصة وفي الـ popup
    private final By CloseOpportunityButton = By.xpath("//main//button[contains(@class,'btn-outline-danger')]");
    private final By ConfirmCloseOpportunityButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'btn-outline-danger')]");




    public P05_OpportunitiesPage CreateANewOpportunity (String NameOfOpportunity , String Desc, String From , String To) throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CreateOpportunityButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptPolicyButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,EventCardByName(DataUtiles.getJsonData("Data","LastEventName")));
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,TempWorkSection);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SellerOpportunity);
        Utility.SENDKEYS(driver,OpportunityNameField,NameOfOpportunity);
        Utility.SENDKEYS(driver,OpportunityDescField,Desc);
        Utility.SENDKEYS(driver,SalaryFromField,From);
        Utility.SENDKEYS(driver,SalaryToField,To);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddOpportunityButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,PublishOpportunitiesButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,PublishOpportunityButtonInPopUp);
        Utility.GETTEXT(driver,MyOpportunitiesTitle,LoadingCircle);


//        Utility.SHADOW_SEARCH_AND_SELECT_FIRST(driver,HostOfSearchField,SearchOfLocationField,Location);
//
//        Utility.SelectFirstEnableDayinCalender(driver);
//        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ToDateField);
//        Utility.SelectLastEnableDayinCalender(driver);

        return this;
    }

    public P05_OpportunitiesPage CloseTheOpportunity (String NameOfOpportunity) {
        Utility.CLICK_TAB(driver,ActiveOpportunitiesTab);
        // صفحة الفرصة لينكها فيه id الفرصة
        Utility.CLICK_AND_WAIT_URL(driver,OpportunityByName(NameOfOpportunity),"/opportunities/[0-9a-f-]{36}");
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CloseOpportunityButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmCloseOpportunityButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }

}
