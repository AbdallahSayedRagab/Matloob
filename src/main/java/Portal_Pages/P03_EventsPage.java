package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_EventsPage {
    public P03_EventsPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By CreateEventButton = By.xpath("(//span[text()='إنشاء فعالية'])[1]");
//    private final By TypeOfEvent = By.xpath("");
    private final By NameOfEventField = By.xpath("//input[@placeholder=\"اسم الفعالية\"]");
    private final By DescriptionOfEventField = By.xpath("//textarea[@placeholder=\"وصف الفعالية\"]");
    private final By NextButton = By.xpath("//span[text()='التالي']");
    private final By HostOfSearchField = By.cssSelector("gmp-place-autocomplete");
    private final By SearchOfLocationField = By.cssSelector("input[part='input']");
    private final By FirstResultOfSearching = By.cssSelector("li[role=\"option\"]:first-child");
    private final By FromDateField = By.xpath("//span[text()='اختر التاريخ من']");
    private final By ToDateField = By.xpath("//span[text()='اختر التاريخ إلى']");
    private final By AttendanceCounterCheckBox = By.xpath("(//input[@type=\"radio\"])[1]");
    private final By GoalField = By.xpath("//input[@placeholder=\"المخرج...\"]");
    private final By AcceptanceCriteria = By.xpath("//textarea[@placeholder=\"معايير القبول\"]");
    private final By SendToReviewButton = By.xpath("//span[text()='إرسال للمراجعة']");
    private final By SendToReviewButtonInPopUp = By.xpath("(//span[text()='إرسال للمراجعة'])[2]");







    public P02_Dashboard CreateANewEvent (String NameOfEvent , String Desc, String Location ,String Goal , String Acceptance) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.CLICKONELEMENTS(driver,CreateEventButton);
        Utility.SENDKEYS(driver,NameOfEventField,NameOfEvent);
        Utility.SENDKEYS(driver,DescriptionOfEventField,Desc);
        Utility.CLICKONELEMENTS(driver,NextButton);
        Utility.SHADOW_SEARCH_AND_SELECT_FIRST(driver,HostOfSearchField,SearchOfLocationField,Location);
        Utility.CLICKONELEMENTS(driver,FromDateField);
        Utility.SelectFirstEnableDayinCalender(driver);
        Utility.CLICKONELEMENTS(driver,ToDateField);
        Utility.SelectLastEnableDayinCalender(driver);
        Utility.CLICKONELEMENTS(driver,AttendanceCounterCheckBox);
        Utility.CLICKONELEMENTS(driver,NextButton);
        Utility.SENDKEYS(driver,GoalField,Goal);
        Utility.SENDKEYS(driver,AcceptanceCriteria,Acceptance);
        Utility.CLICKONELEMENTS(driver,NextButton);
        Utility.CLICKONELEMENTS(driver,NextButton);
        Utility.CLICKONELEMENTS(driver,SendToReviewButton);
        Utility.CLICKONELEMENTS(driver,SendToReviewButtonInPopUp);




        return new P02_Dashboard( driver);
    }


}
