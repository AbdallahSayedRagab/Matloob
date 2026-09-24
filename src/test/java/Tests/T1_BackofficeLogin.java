package Tests;

import Backoffice_Pages.P1_LoginPage;
import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
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
    public class T1_BackofficeLogin {
        private WebDriver driver ;
        @BeforeMethod
        public void setupdriver () throws IOException {
            SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
            driver = DriverFactoryClass.getdriver();
            getdriver().get(DataUtiles.getPropertyValue("environment", "Backoffice_URL"));
//       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }


        @Test
        public void Login_As_Admin () throws InterruptedException, IOException {
            new P1_LoginPage(driver).LoginInBackoffice(DataUtiles.getJsonData("Data","AD_UserName")
                    ,DataUtiles.getJsonData("Data","AD_Password"));

        }

        @Test
        public void AcceptAndPublishLastEvent () throws InterruptedException, IOException {
            new P1_LoginPage(driver).LoginInBackoffice(DataUtiles.getJsonData("Data","AD_UserName")
                            ,DataUtiles.getJsonData("Data","AD_Password"))
                    .Select_EventsPage().ReviewAndAcceptLastEvent(DataUtiles.getJsonData("Data","EventName"));

        }

        @Test
        public void ReviewAndAcceptFacility () throws InterruptedException, IOException {
            new P1_LoginPage(driver).LoginInBackoffice(DataUtiles.getJsonData("Data","AD_UserName")
                            ,DataUtiles.getJsonData("Data","AD_Password"))
                    .Select_FacilityPage().ReviewAndAcceptLastFacility();

        }

        @Test
        public void ReviewAndRejectFacility () throws InterruptedException, IOException {
            new P1_LoginPage(driver).LoginInBackoffice(DataUtiles.getJsonData("Data","AD_UserName")
                            ,DataUtiles.getJsonData("Data","AD_Password"))
                    .Select_FacilityPage().ReviewAndRejectLastFacility();

        }






        @AfterMethod
        public void quit () {
//        driver.quit();
        }
}

