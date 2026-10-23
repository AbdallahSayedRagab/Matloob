package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_EventsPage {
    public P04_EventsPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    // أول زرار أسود في الصفحة
    private final By CreateEventButton = By.xpath("//main//button[contains(@class,'bg-black')]");
//    private final By TypeOfEvent = By.xpath("");
    private final By NameOfEventField = By.xpath("//input[@placeholder=\"ادخل اسم الفعالية\"]");
    private final By DescriptionOfEventField = By.xpath("//textarea[@placeholder=\"أكتب نبذة مختصرة عن الفعالية\"]");
    private final By NextButton = By.xpath("//span[text()='حفظ ومتابعة']");
    private final By SelectOnMapButton = By.xpath("//button[normalize-space()='حدد على الخريطة']");
    private final By HostOfSearchField = By.cssSelector("gmp-place-autocomplete");
    private final By SearchOfLocationField = By.cssSelector("input[part='input']");
    private final By FirstResultOfSearching = By.cssSelector("li[role=\"option\"]:first-child");
    private final By ConfirmLocationButton = By.xpath("//button[normalize-space()='تأكيد الموقع']");
    private final By FromDateField = By.xpath("//span[text()='اختر التاريخ من']");
    private final By ToDateField = By.xpath("//span[text()='اختر التاريخ إلى']");
    private final By AttendanceCounterList = By.xpath("//button[@aria-haspopup=\"listbox\"]");
    private final By FirstChoiceForAnyList = By.xpath("(//li[@role=\"option\"])[1]");
    private final By AttachmentField = By.xpath("(//input[@type=\"file\"])[1]");
    private final By GoalField = By.xpath("//input[@placeholder=\"ادخل المخرج\"]");
    private final By AcceptanceCriteria = By.xpath("//textarea[@placeholder=\"ادخل معايير القبول\"]");
    private final By SendToReviewButton = By.xpath("//span[text()='إرسال للمراجعة']");
    private final By SendToReviewButtonInPopUp = By.xpath("//button[normalize-space()='نشر الفعالية']");
    private final By AcceptPolicyButton = By.id("guidelines_acknowledged");
    private final By SuccessMessage = By.xpath("//h3[text()='تم إرسال الفعالية للمراجعة']");
    private final By LoadingCircle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    // ترتيب التابات: قيد الإجراء، نشطة، ...
    private final By ActiveEventsTab = By.xpath("(//button[@role='tab'])[2]");
    // اسم الفعالية نفسه لينك بيفتح صفحة الفعالية
    private By EventByName (String eventName) {
        return By.xpath("//div[@role='tabpanel'][not(contains(@class,'hidden'))]//a[.='" + eventName + "']");
    }
    // زرار الإغلاق الأحمر في صفحة الفعالية وفي الـ popup
    private final By CloseEventButton = By.xpath("//main//button[contains(@class,'btn-outline-danger')]");
    private final By ConfirmCloseEventButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'btn-outline-danger')]");






    public P02_Dashboard CreateANewEvent (String NameOfEvent , String Desc, String Location ,String Goal , String Acceptance) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CreateEventButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AcceptPolicyButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.SENDKEYS(driver,NameOfEventField,NameOfEvent);
        Utility.SENDKEYS(driver,DescriptionOfEventField,Desc);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SelectOnMapButton);
        Utility.SHADOW_SEARCH_AND_SELECT_FIRST(driver,HostOfSearchField,SearchOfLocationField,Location);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmLocationButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FromDateField);
        Utility.SelectFirstEnableDayinCalender(driver);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ToDateField);
        Utility.SelectLastEnableDayinCalender(driver);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AttendanceCounterList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.UPLOAD_FILE_AND_WAIT(driver,AttachmentField,"F:\\Testing\\MATLOOB\\Test_Photo.jpg");
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.SENDKEYS(driver,GoalField,Goal);
        Utility.SENDKEYS(driver,AcceptanceCriteria,Acceptance);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,NextButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SendToReviewButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SendToReviewButtonInPopUp);
        Utility.GETTEXT(driver,SuccessMessage,LoadingCircle);




        return new P02_Dashboard( driver);
    }

    public P04_EventsPage CloseTheEvent (String NameOfEvent) {
        Utility.CLICK_TAB(driver,ActiveEventsTab);
        // صفحة الفعالية لينكها فيه id الفعالية
        Utility.CLICK_AND_WAIT_URL(driver,EventByName(NameOfEvent),"/events/[0-9a-f-]{36}");
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CloseEventButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmCloseEventButtonInPopUp);
        Utility.GET_TOAST_TEXT(driver);
        return this;
    }


}
