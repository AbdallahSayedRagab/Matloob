package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_FacilityPage {
    public P06_FacilityPage (WebDriver driver){
        this.driver = driver ;
    }

    private WebDriver driver;

    private final By FacilityName = By.name("name");
    private final By RecordNumber = By.name("commercialRegistrationNumber");
    private final By WorkingOfficeNumber = By.name("laborOfficeId");
    private final By SerialNumber = By.name("sequenceNumber");
    private final By Description = By.id("description");
    private final By Number = By.name("phone");
    private final By Email = By.name("email");
    private final By AreaList = By.xpath("(//button[@aria-haspopup='listbox'])[1]");
    private final By FirstChoiceForAnyList = By.xpath("(//li[@role=\"option\"])[1]");
    private final By CityList = By.xpath("(//button[@aria-haspopup='listbox'])[2]");
    // قبل الحفظ كمسودة بيبقى آخر زرار في الفورم
    private final By SaveAsADraftButton = By.xpath("(//form//button)[last()]");
    private final By FirstAttachment = By.xpath("(//input[@type=\"file\"])[1]");
    private final By SecondAttachment = By.xpath("(//input[@type=\"file\"])[2]");
    // بعد الحفظ كمسودة بيظهر جنب زرار المسودة، فبيبقى آخر زرار وقبله زرار
    private final By SendARegistrationRequestButton = By.xpath("(//form//button)[last()][preceding-sibling::button]");


    public P02_Dashboard RegisterANewFacility (String facilityName , String Desc, String RecordNum ,String OfficeNum , String Serial
    , String Num , String Mail ) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.SENDKEYS(driver,FacilityName,facilityName);
        Utility.SENDKEYS(driver,RecordNumber,RecordNum);
        Utility.SENDKEYS(driver,WorkingOfficeNumber,OfficeNum);
        Utility.SENDKEYS(driver,SerialNumber,Serial);
        Utility.SENDKEYS(driver,Description,Desc);
        Utility.SENDKEYS(driver,Number,Num);
        Utility.SENDKEYS(driver,Email,Mail);

        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AreaList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CityList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SaveAsADraftButton);

        Utility.UPLOAD_FILE_AND_WAIT(driver,FirstAttachment,"F:\\Testing\\MATLOOB\\Test_Photo.jpg");
        Utility.UPLOAD_FILE_AND_WAIT(driver,SecondAttachment,"F:\\Testing\\MATLOOB\\Test_Photo.jpg");

        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SendARegistrationRequestButton);
        Utility.WAIT_FOR_TEXT(driver,"تم إرسال طلب التسجيل بنجاح");





        return new P02_Dashboard( driver);
    }





}
