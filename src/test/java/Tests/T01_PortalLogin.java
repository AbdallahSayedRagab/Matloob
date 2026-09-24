package Tests;

import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
import Portal_Pages.*;
import Utilities.DataUtiles;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactoryClass.SetupDriver;
import static DriverFactory.DriverFactoryClass.getdriver;

@Listeners({IInvociedMethodListener.class , ITestResultListener.class})
public class T01_PortalLogin {
    private WebDriver driver ;
    @BeforeMethod
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment", "Portal_URL"));
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
                .CreateANewEvent(DataUtiles.getJsonData("Data","EventName"),
                        DataUtiles.getJsonData("Data","Desc"),
                        DataUtiles.getJsonData("Data","Location"),
                        DataUtiles.getJsonData("Data","Goal"),
                        DataUtiles.getJsonData("Data","AcceptanceCriteria"));

    }


    @Test
    public void CreateNewFacility () throws InterruptedException {
        new P01_LoginPage(driver).Login_As_Individual(DataUtiles.getJsonData("Data","ValidLoginEmail")
                        ,DataUtiles.getJsonData("Data","Password")).Select_RegisterAFacilityPage()
                .RegisterANewFacility(DataUtiles.getUniqueJsonData("Data","FacilityName"),
                        DataUtiles.getUniqueJsonData("Data","Desc"),
                        DataUtiles.getUniqueJsonData("Data","RecordNumber"),
                        DataUtiles.getUniqueJsonData("Data","OfficeNumber"),
                        DataUtiles.getUniqueJsonData("Data","SerialNumber"),
                        DataUtiles.getJsonData("Data","Phone"),
                        DataUtiles.getJsonData("Data","Mail"));

    }


    @AfterMethod
    public void quit () {
//        driver.quit();
    }
}




