package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P09_IndividualContractsPage {
    public P09_IndividualContractsPage (WebDriver driver){this.driver = driver ;}
    private WebDriver driver;

    // ترتيب التابات: ساري، بانتظار الرد، ...
    private final By WaitingForReplyTab = By.xpath("(//button[@role='tab'])[2]");
    // تاب طلب إلغاء بيظهر بس لما يكون فيه طلب، وبيبقى قبل آخر تاب (ملغى)
    private final By CancelRequestsTab = By.xpath("(//button[@role='tab'])[last()-1]");
    private final By SearchField = By.name("opportunity_name");
    private By ViewContractButtonByOpportunityName (String opportunityName) {
        return By.xpath("//div[@role='tabpanel'][not(contains(@class,'hidden'))]"
                + "//p[contains(normalize-space(),'" + opportunityName + "')]/following-sibling::button");
    }
    private final By AcceptOfferButton = By.xpath("(//button[normalize-space()='قبول العرض'])[1]");
    private final By AcceptOfferButtonInPopUp = By.xpath("//div[@role='dialog']//button[normalize-space()='قبول العرض']");
    // رفض العرض هو الزرار الأبيض اللي جنب زرار قبول العرض (الـ gradient)
    private final By RejectOfferButton = By.xpath("//main//button[contains(@class,'bg-gradient')]/following-sibling::button");
    private final By RejectReasonList = By.xpath("//div[@role='dialog']//button[@aria-haspopup='listbox']");
    private final By FirstChoiceForAnyList = By.xpath("(//li[@role='option'])[1]");
    private final By ConfirmRejectOfferButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'bg-black')]");
    // قبول طلب الإلغاء هو الزرار الـ gradient في صفحة العقد
    private final By AcceptCancelRequestButton = By.xpath("//main//button[contains(@class,'bg-gradient')]");
    private final By ConfirmCancelContractButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'bg-black')]");
    // الزرار الأبيض في كارت جميع العقود بيفتح كل العقود اللي بانتظار التقييم
    private final By ViewAllUnevaluatedContractsButton = By.xpath("//main//button[contains(@class,'btn-white')]");
    private By StartRatingButtonByOpportunityName (String opportunityName) {
        return By.xpath("//p[contains(.,'" + opportunityName + "')]/ancestor::div[.//button][1]//button");
    }
    // popup التقييم: مجموعتين اختيارات (التجربة مع الطرف التاني، التجربة في المنصة) والتالت في كل واحدة محايد
    private final By NeutralRatingForExperience = By.xpath("(//div[@role='dialog']//div[contains(@class,'flex-wrap')])[1]/div[3]");
    private final By NeutralRatingForPlatform = By.xpath("(//div[@role='dialog']//div[contains(@class,'flex-wrap')])[2]/div[3]");
    // هل ستعمل معه مرة أخرى: أول اختيار نعم
    private final By WorkAgainYesChoice = By.xpath("(//div[@role='dialog']//input[@type='radio'])[1]");
    private final By RatingCommentField = By.id("comment");
    private final By RatingConfirmationCheckBox = By.id("confirmation_message");
    private final By SendRatingButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'bg-black')]");


    public P09_IndividualContractsPage Select_WaitingForReplyTab () {
        Utility.CLICK_TAB(driver,WaitingForReplyTab);
        return this;
    }

    public P09_IndividualContractsPage Select_CancelRequestsTab () {
        Utility.CLICK_TAB(driver,CancelRequestsTab);
        return this;
    }

    public P09_IndividualContractsPage SearchForOpportunity (String opportunityName) {
        Utility.SENDKEYS(driver,SearchField,opportunityName);
        // البحث بيتحدّث في اللينك بعد لحظة وبيعيد تحميل الصفحة فيرجّع التاب للأول، فنستناه يخلص
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("opportunity_name"));
        return this;
    }

    public P09_IndividualContractsPage OpenTheContract (String opportunityName) {
        // صفحة العقد لينكها فيه id العقد
        Utility.CLICK_AND_WAIT_URL(driver,ViewContractButtonByOpportunityName(opportunityName),"/contracts/[0-9a-f-]{36}");
        return this;
    }

    public P09_IndividualContractsPage AcceptTheOffer () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptOfferButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptOfferButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }

    public P09_IndividualContractsPage RejectTheOffer () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,RejectOfferButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,RejectReasonList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmRejectOfferButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }

    public P09_IndividualContractsPage AcceptTheCancelRequest () {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptCancelRequestButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmCancelContractButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }

    public P09_IndividualContractsPage Select_AllUnevaluatedContracts () {
        Utility.CLICK_AND_WAIT_URL(driver,ViewAllUnevaluatedContractsButton,"/unevaluated");
        return this;
    }

    public P09_IndividualContractsPage RateTheContract (String opportunityName , String comment) {
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
