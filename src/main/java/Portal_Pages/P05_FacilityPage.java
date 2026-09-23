package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_FacilityPage {
    public P05_FacilityPage (WebDriver driver){
        this.driver = driver ;
    }

    private WebDriver driver;

    private final By FacilityName = By.xpath("//input[@placeholder=\"اسم المنشأة\"]");
    private final By RecordNumber = By.xpath("//input[@placeholder=\"رقم السجل التجاري\"]");
    private final By WorkingOfficeNumber = By.xpath("//input[@placeholder=\"رقم مكتب العمل\"]");
    private final By SerialNumber = By.xpath("//input[@placeholder=\"الرقم التسلسلي\"]");
    private final By Description = By.xpath("//textarea[@placeholder=\"وصف مختصر\"]");
    private final By Number = By.xpath("//input[@placeholder=\"رقم الجوال\"]");
    private final By Email = By.xpath("//input[@placeholder=\"البريد الإلكتروني\"]");
    private final By AreaList = By.xpath("//span[text()='المنطقة']");
    private final By FirstChoiceForAnyList = By.xpath("(//li[@role=\"option\"])[1]");
    private final By CityList = By.xpath("//span[text()='المدينة']");
    private final By SaveAsADraftButton = By.xpath("//span[text()='حفظ كمسودة']");
    private final By FirstAttachment = By.xpath("(//input[@type=\"file\"])[1]");
    private final By SecondAttachment = By.xpath("(//input[@type=\"file\"])[2]");
    private final By SendARegistrationRequestButton = By.xpath("//span[text()='إرسال طلب التسجيل']");


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





        return new P02_Dashboard( driver);
    }





}
