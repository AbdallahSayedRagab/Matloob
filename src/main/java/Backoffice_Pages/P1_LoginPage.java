package Backoffice_Pages;

import Portal_Pages.P02_Dashboard;
import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class P1_LoginPage {
    public P1_LoginPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By UserNameField = By.cssSelector("#indiviuals [name=Username]");
    private final By LoginButton = By.cssSelector("#indiviuals button");
    private final By Password = By.id("password");
    private final By ConfirmLoginButton = By.cssSelector("button.btn-primary");


    private final By ConfirmLoginByNAFAZ = By.xpath("//button[@value='login']");
    private final By PersonalAccountCardAtLoginPage = By.xpath("//p[text()='الحساب الشخصي']/following::button[1]");
    private final By CompanyAccountCardAtLoginPage = By.xpath("//div[p[normalize-space()='منشأة شركة']]//button\n");

    private final By LoadingCircle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");



    public P2_Dashboard LoginInBackoffice (String EMAIL , String password) throws InterruptedException, IOException {
//        String EMAIL = DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password = DataUtils.getJsonData("Data","Password");
        Utility.SENDKEYS(driver,UserNameField,EMAIL);
        Utility.CLICKONELEMENTS(driver,LoginButton);
        Utility.SENDKEYS(driver,Password,password);
        Utility.CLICKONELEMENTS(driver,ConfirmLoginButton);
        Utility.CheckURLAndNavigateToTargetURL(driver, DataUtiles.getPropertyValue("environment", "Backoffice_URL"));
        return new P2_Dashboard( driver);
    }


}
