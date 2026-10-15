package Tests;

import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
import Portal_Pages.*;
import Utilities.DataUtiles;
import Utilities.JsonUtil;
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
        String EventName = DataUtiles.getJsonDataWithTime("Data","EventName");
        JsonUtil.writeValue("LastEventName",EventName);
        new P01_LoginPage(driver).Login_As_LastFacility(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).Select_EventsPage()
                .CreateANewEvent(EventName,
                        DataUtiles.getJsonData("Data","Desc"),
                        DataUtiles.getJsonData("Data","Location"),
                        DataUtiles.getJsonData("Data","Goal"),
                        DataUtiles.getJsonData("Data","AcceptanceCriteria"));

    }

    @Test
    public void CreateNewOpportunity () throws InterruptedException {
        String OpportunityName = DataUtiles.getJsonDataWithTime("Data","OpportunityName");
        JsonUtil.writeValue("LastOpportunityName",OpportunityName);
        new P01_LoginPage(driver).Login_As_LastFacility(DataUtiles.getJsonData("Data","ValidLoginEmail")
                        ,DataUtiles.getJsonData("Data","Password")).Select_OpportunitiesPage()
                .CreateANewOpportunity(OpportunityName,
                        DataUtiles.getJsonData("Data","Desc"),
                        DataUtiles.getJsonData("Data","SalaryFrom"),
                        DataUtiles.getJsonData("Data","SalaryTo"));

    }


    @Test
    public void ApplyToTheLastOpportunity () throws InterruptedException {
        new P01_LoginPage(driver).Login_As_Individual(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"))
                .Select_ExplorePage().ApplyForTheLastIndividualOpportunity();

    }


    @Test
    public void CreateNewFacility () throws InterruptedException {
        String FacilityName = DataUtiles.getJsonDataWithDayAndTime("Data","FacilityName");
        JsonUtil.writeValue("LastFacilityName",FacilityName);
        new P01_LoginPage(driver).Login_As_Individual(DataUtiles.getJsonData("Data","ValidLoginEmail")
                        ,DataUtiles.getJsonData("Data","Password")).Select_RegisterAFacilityPage()
                .RegisterANewFacility(FacilityName,
                        DataUtiles.getJsonDataWithDayAndTime("Data","Desc"),
                        DataUtiles.getJsonDataWithDayAndTime("Data","RecordNumber"),
                        DataUtiles.getJsonDataWithDayAndTime("Data","OfficeNumber"),
                        DataUtiles.getJsonDataWithDayAndTime("Data","SerialNumber"),
                        DataUtiles.getJsonData("Data","Phone"),
                        DataUtiles.getJsonData("Data","Mail"));

    }


    @Test
    public void CompleteTheProfileOfLastFacility () throws InterruptedException {
        new P01_LoginPage(driver).Login_As_LastFacility(DataUtiles.getJsonData("Data","ValidLoginEmail")
                        ,DataUtiles.getJsonData("Data","Password")).Select_CompleteProfilePage()
                .CompleteTheProfile(DataUtiles.getJsonData("Data","TestData"),
                        DataUtiles.getJsonData("Data","Website"),
                        DataUtiles.getJsonData("Data","Location"),
                        DataUtiles.getJsonData("Data","BuildingNumber"),
                        DataUtiles.getJsonData("Data","IBAN"),
                        DataUtiles.getJsonData("Data","YearsOfExperience"));

    }


    @AfterMethod
    public void quit () {
//        driver.quit();
    }
}




