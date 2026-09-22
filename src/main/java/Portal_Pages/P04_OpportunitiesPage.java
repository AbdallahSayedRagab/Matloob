package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_OpportunitiesPage {
    public P04_OpportunitiesPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By EventTicket = By.xpath("");
    private final By NameOfOpportunityField = By.xpath("");

    public P02_Dashboard CreateANewOpportunity (String NameOfOpportunity , String password) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.CLICKONELEMENTS(driver,EventTicket);
        Utility.SENDKEYS(driver,NameOfOpportunityField,NameOfOpportunity);

        return new P02_Dashboard( driver);
    }

}
