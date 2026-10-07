package Portal_Pages;

import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_OpportunitiesPage {
    public P04_OpportunitiesPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By CreateOpportunityButton = By.xpath("(//span[text()='إنشاء الفرصة'])[1]");
    private final By AcceptPolicyButton = By.xpath("//input[@id=\"guidelines_acknowledged\"]");
    private final By NextButton = By.xpath("//span[text()='التالي']");
    private By EventCardByName(String eventName) {
        return By.xpath("//h3[contains(normalize-space(),'" + eventName + "')]" + "/ancestor::div[.//input[@type='radio']][1]");
    }

    private final By TempWorkSection = By.xpath("//h1[contains(normalize-space(),'العمل المؤقت')]/ancestor::div[@role='button'][1]");
    private final By SellerOpportunity = By.xpath("//span[text()='بائع']");
    private final By OpportunityNameField = By.xpath("//input[@placeholder=\"اسم الفرصة\"]");
    private final By OpportunityDescField = By.xpath("//textarea[@placeholder=\"وصف الفرصة\"]");
    private final By SalaryFromField = By.xpath("//input[@id=\"opportunities.0.salary_from\"]");
    private final By SalaryToField = By.xpath("//input[@id=\"opportunities.0.salary_to\"]");
    private final By AddOpportunityButton = By.xpath("//span[text()='إضافة الفرصة']");
    private final By PublishOpportunitiesButton = By.xpath("//span[text()='نشر الفرص']");




    public P04_OpportunitiesPage CreateANewOpportunity (String NameOfOpportunity , String Desc, String From , String To) throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CreateOpportunityButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptPolicyButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,EventCardByName(DataUtiles.getJsonData("Data","EventName")));
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,TempWorkSection);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SellerOpportunity);
        Utility.SENDKEYS(driver,OpportunityNameField,NameOfOpportunity);
        Utility.SENDKEYS(driver,OpportunityDescField,Desc);
        Utility.SENDKEYS(driver,SalaryFromField,From);
        Utility.SENDKEYS(driver,SalaryToField,To);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddOpportunityButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,PublishOpportunitiesButton);


//        Utility.SHADOW_SEARCH_AND_SELECT_FIRST(driver,HostOfSearchField,SearchOfLocationField,Location);
//
//        Utility.SelectFirstEnableDayinCalender(driver);
//        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ToDateField);
//        Utility.SelectLastEnableDayinCalender(driver);

        return this;
    }

}
