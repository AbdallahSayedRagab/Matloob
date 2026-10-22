package Portal_Pages;

import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    public P01_LoginPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;
    private final By LoginButton = By.xpath("//header//button[not(@type)]");
    private final By ClickHereButton = By.xpath("//a[contains(@href,'Governmental')]");
    private final By EmailField = By.id("email");
    private final By Password = By.id("password");
    private final By ConfirmLoginByNAFAZ = By.xpath("//button[@value='login']");
    // الحساب الشخصي دايمًا أول كارت في الصفحة
    private final By PersonalAccountCardAtLoginPage = By.xpath("(//main//button)[1]");
    private final By CompanyAccountCardAtLoginPage = FacilityAccountCardByName("منشأة شركة");

    private final By LoadingCircle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    // اسم المنشأة جوه <p> والزرار في نفس الكارت (أقرب div فيه button)
    private By FacilityAccountCardByName(String facilityName) {
        return By.xpath("//p[.='" + facilityName + "']/ancestor::div[.//button][1]//button");
    }



    public P02_Dashboard Login_As_Individual (String EMAIL , String password) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,LoginButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ClickHereButton);
        Utility.SENDKEYS(driver,EmailField,EMAIL);
        Utility.SENDKEYS(driver,Password,password);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmLoginByNAFAZ);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,PersonalAccountCardAtLoginPage);
        return new P02_Dashboard( driver);
    }

    public P02_Dashboard Login_As_Company (String EMAIL , String password) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,LoginButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ClickHereButton);
        Utility.SENDKEYS(driver,EmailField,EMAIL);
        Utility.SENDKEYS(driver,Password,password);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmLoginByNAFAZ);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,CompanyAccountCardAtLoginPage);
        return new P02_Dashboard( driver);
    }

    public P02_Dashboard Login_As_LastFacility (String EMAIL , String password) throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,LoginButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ClickHereButton);
        Utility.SENDKEYS(driver,EmailField,EMAIL);
        Utility.SENDKEYS(driver,Password,password);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ConfirmLoginByNAFAZ);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FacilityAccountCardByName(DataUtiles.getJsonData("Data","LastFacilityName")));
        return new P02_Dashboard( driver);
    }



}
