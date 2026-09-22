package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    public P01_LoginPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;
    private final By LoginButton = By.xpath("//span[text()='تسجيل الدخول']");
    private final By ClickHereButton = By.xpath("//a[contains(@href,'/Account/LoginDb?userType=Governmental')]");
    private final By EmailField = By.id("email");
    private final By Password = By.id("password");
    private final By ConfirmLoginByNAFAZ = By.xpath("//button[@value='login']");
    private final By PersonalAccountCardAtLoginPage = By.xpath("//p[text()='الحساب الشخصي']/following::button[1]");
    private final By CompanyAccountCardAtLoginPage = By.xpath("//div[p[normalize-space()='منشأة شركة']]//button\n");

    private final By LoadingCircle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");



    public P02_Dashboard Login_As_Individual (String EMAIL , String password) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.CLICKONELEMENTS(driver,LoginButton);
        Utility.CLICKONELEMENTS(driver,ClickHereButton);
        Utility.SENDKEYS(driver,EmailField,EMAIL);
        Utility.SENDKEYS(driver,Password,password);
        Utility.CLICKONELEMENTS(driver,ConfirmLoginByNAFAZ);
        Utility.CLICKONELEMENTS(driver,PersonalAccountCardAtLoginPage);
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



}
