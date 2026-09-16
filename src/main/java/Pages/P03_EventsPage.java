package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_EventsPage {
    public P03_EventsPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By TypeOfEvent = By.xpath("");
    private final By NameOfEventField = By.xpath("");




    public P02_Dashboard CreateANewEvent (String NameOfEvent , String password) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.CLICKONELEMENTS(driver,TypeOfEvent);
        Utility.SENDKEYS(driver,NameOfEventField,password);

        return new P02_Dashboard( driver);
    }

}
