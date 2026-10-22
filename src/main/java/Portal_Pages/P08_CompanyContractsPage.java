package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P08_CompanyContractsPage {
    public P08_CompanyContractsPage (WebDriver driver){this.driver = driver ;}
    private WebDriver driver;

    // ترتيب التابات: قيد الإجراء، نشطة، ...
    private final By ActiveOpportunitiesTab = By.xpath("(//button[@role='tab'])[2]");
    private final By SearchField = By.xpath("//input[@type='search']");
    // اسم الفرصة نفسه لينك بيفتح صفحة الفرصة
    private By ViewOpportunityButtonByName (String opportunityName) {
        return By.xpath("//a[.='" + opportunityName + "']");
    }
    // ترتيب التابات: شركات، أفراد
    private final By IndividualsTab = By.xpath("(//button[@role='tab'])[2]");
    // في كارت المتقدم: عرض الملف الشخصي وبعده تقديم عرض وظيفي
    private final By SendJobOfferButton = By.xpath("//div[@role='tabpanel']//button[preceding-sibling::button]");
    private final By DailySalaryField = By.xpath("//main//input[@type='number']");
    private final By LaborerCommitmentsField = By.id("laborer_commitments");
    private final By ProfessionList = By.xpath("//main//button[@aria-haspopup='listbox']");
    private final By FirstChoiceForAnyList = By.xpath("(//li[@role='option'])[1]");
    // صلاحية العرض لازم متتخطاش تاريخ نهاية العقد، والأيام اللي بعده بتبقى disabled فبنختار آخر يوم متاح
    private final By OfferValidityToDateField = By.id("offer_validity_to-date-trigger");
    private final By LastEnableDayInCalender =By.xpath("(//button[@name='day'][not(@disabled)])[last()]");
    private final By NextButton = By.xpath("//main//button[contains(@class,'bg-black')]");
    // في صفحة المراجعة: العودة للخلف وبعده تقديم عرض وظيفي
    private final By ConfirmSendJobOfferButton = By.xpath("//main//button[preceding-sibling::button]");
    // الزرار الأسود في popup التوطين (متابعة إنشاء العرض) وفي popup التأكيد (نعم)
    private final By ContinueCreatingOfferButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'bg-black')]");
    private final By YesButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'bg-black')]");


    public P08_CompanyContractsPage Select_ActiveOpportunitiesTab () {
        Utility.CLICK_TAB(driver,ActiveOpportunitiesTab);
        return this;
    }

    public P08_CompanyContractsPage SearchForOpportunity (String opportunityName) {
        Utility.SENDKEYS(driver,SearchField,opportunityName);
        return this;
    }

    public P08_CompanyContractsPage OpenTheOpportunity (String opportunityName) {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ViewOpportunityButtonByName(opportunityName));
        return this;
    }

    public P08_CompanyContractsPage Select_IndividualsTab () {
        Utility.CLICK_TAB(driver,IndividualsTab);
        return this;
    }

    public P08_CompanyContractsPage ClickOnSendJobOffer () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SendJobOfferButton);
        return this;
    }

    public P08_CompanyContractsPage FillJobOfferDetails (String dailySalary , String commitments) {
        Utility.CLEAR_AND_SENDKEYS(driver,DailySalaryField,dailySalary);
        Utility.SENDKEYS(driver,LaborerCommitmentsField,commitments);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ProfessionList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,OfferValidityToDateField);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,LastEnableDayInCalender);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        return this;
    }

    public P08_CompanyContractsPage ConfirmAndSendJobOffer () {
        // popup نسبة التوطين بيظهر بس لو النسبة أقل من الحد الأدنى
        Utility.PAUSE(driver,2000);
        if (Utility.COUNT(driver,ContinueCreatingOfferButtonInPopUp) > 0)
            Utility.WatingLoader_And_CLICKONELEMENTS(driver,ContinueCreatingOfferButtonInPopUp);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmSendJobOfferButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,YesButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }
}
