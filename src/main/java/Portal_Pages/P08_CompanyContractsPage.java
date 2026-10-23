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
    private final By SendJobOfferButton = By.xpath("//div[@role='tabpanel'][not(contains(@class,'hidden'))]//button[preceding-sibling::button]");
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

    // كروت صفحة العقود بالترتيب: شركات، أفراد، طلبات العقود
    private final By IndividualsContractsCard = By.xpath("(//div[contains(@class,'cursor-pointer')][.//h3])[2]");
    private final By ContractRequestsCard = By.xpath("(//div[contains(@class,'cursor-pointer')][.//h3])[3]");
    // السهم اللي جنب اسم الفرصة في كارت العقد بيفتح تفاصيل العقد
    private By ViewContractButtonByOpportunityName (String opportunityName) {
        return By.xpath("//div[@id='contracts_list']//p[contains(.,'" + opportunityName + "')]/following-sibling::button");
    }
    // إلغاء العقد زرار لينك أخضر جنب عنوان بيانات الجهات المتعاقدة
    private final By CancelContractButton = By.xpath("//main//button[contains(@class,'text-tertiaryGreen')]");
    private final By CancelReasonList = By.xpath("//div[@role='dialog']//button[@aria-haspopup='listbox']");
    private final By ConfirmCancelContractButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'bg-black')]");
    // تابات طلبات العقود: بانتظار الرد، التقييم، إلغاء العقد
    private final By RatingRequestsTab = By.xpath("(//button[@role='tab'])[2]");
    private By StartRatingButtonByOpportunityName (String opportunityName) {
        return By.xpath("//div[@role='tabpanel'][not(contains(@class,'hidden'))]"
                + "//p[contains(.,'" + opportunityName + "')]/ancestor::div[.//button][1]//button");
    }
    // popup التقييم: مجموعتين اختيارات (التجربة مع الطرف التاني، التجربة في المنصة) والتالت في كل واحدة محايد
    private final By NeutralRatingForExperience = By.xpath("(//div[@role='dialog']//div[contains(@class,'flex-wrap')])[1]/div[3]");
    private final By NeutralRatingForPlatform = By.xpath("(//div[@role='dialog']//div[contains(@class,'flex-wrap')])[2]/div[3]");
    // هل ستعمل معه مرة أخرى: أول اختيار نعم
    private final By WorkAgainYesChoice = By.xpath("(//div[@role='dialog']//input[@type='radio'])[1]");
    private final By RatingCommentField = By.id("comment");
    private final By RatingConfirmationCheckBox = By.id("confirmation_message");
    private final By SendRatingButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'bg-black')]");


    public P08_CompanyContractsPage Select_ActiveOpportunitiesTab () {
        Utility.CLICK_TAB(driver,ActiveOpportunitiesTab);
        return this;
    }

    public P08_CompanyContractsPage SearchForOpportunity (String opportunityName) {
        Utility.SENDKEYS(driver,SearchField,opportunityName);
        return this;
    }

    public P08_CompanyContractsPage OpenTheOpportunity (String opportunityName) {
        // صفحة الفرصة لينكها فيه id الفرصة
        Utility.CLICK_AND_WAIT_URL(driver,ViewOpportunityButtonByName(opportunityName),"/opportunities/[0-9a-f-]{36}");
        return this;
    }

    public P08_CompanyContractsPage Select_IndividualsTab () {
        Utility.CLICK_TAB(driver,IndividualsTab);
        return this;
    }

    public P08_CompanyContractsPage ClickOnSendJobOffer () {
        // الصفحة ممكن تتحدّث بعد ما نختار تاب الأفراد فترجع لتاب الشركات، فنختاره تاني لحد ما الزرار يظهر
        for (int i = 0; i < 3 && Utility.COUNT(driver,SendJobOfferButton) == 0; i++) {
            Utility.PAUSE(driver,3000);
            if (Utility.COUNT(driver,SendJobOfferButton) == 0) Utility.CLICK_TAB(driver,IndividualsTab);
        }
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

    public P08_CompanyContractsPage Select_IndividualsContracts () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,IndividualsContractsCard);
        return this;
    }

    public P08_CompanyContractsPage OpenTheContract (String opportunityName) {
        Utility.CLICK_AND_WAIT_URL(driver,ViewContractButtonByOpportunityName(opportunityName),"/offers/details/");
        return this;
    }

    public P08_CompanyContractsPage CancelTheContract () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CancelContractButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CancelReasonList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmCancelContractButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }

    public P08_CompanyContractsPage Select_RatingRequests () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ContractRequestsCard);
        Utility.CLICK_TAB(driver,RatingRequestsTab);
        return this;
    }

    public P08_CompanyContractsPage RateTheContract (String opportunityName , String comment) {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,StartRatingButtonByOpportunityName(opportunityName));
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NeutralRatingForExperience);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,WorkAgainYesChoice);
        Utility.SENDKEYS(driver,RatingCommentField,comment);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NeutralRatingForPlatform);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,RatingConfirmationCheckBox);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SendRatingButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }
}
