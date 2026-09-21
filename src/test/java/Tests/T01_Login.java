package Tests;

import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
import Pages.*;
import Utilities.DataUtiles;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactoryClass.SetupDriver;
import static DriverFactory.DriverFactoryClass.getdriver;

@Listeners({IInvociedMethodListener.class , ITestResultListener.class})
public class T01_Login {
    private WebDriver driver ;
    @BeforeMethod
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment","BASE_URL"));
//       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }


    @Test
    public void Individual_Login () throws InterruptedException {
        new P01_LoginPage(driver).Login_As_Individual(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));

    }


    @Test
    public void InvalidLogin() throws InterruptedException {
        new P01_LoginPage(driver).Login_As_Individual(DataUtiles.getJsonData("Data","InValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
    }

    @Test
    public void Company_Login () throws InterruptedException {
        new P01_LoginPage(driver).Login_As_Company(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));

    }

    @Test
    public void CreateNewEvent () throws InterruptedException {
        new P01_LoginPage(driver).Login_As_Company(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).Select_EventsPage()
                .CreateANewEvent("فعالية مسيو مجاهد","وصف كده ع الماشي",
                        "الرياض","مخرج مهم جدااا","اهو اي حاجه نمشي بيها الدنيا ونشغل الشغل ");

    }



    @AfterMethod
    public void quit () {
//        driver.quit();
    }
}




