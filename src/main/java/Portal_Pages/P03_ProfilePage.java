package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_ProfilePage {
    public P03_ProfilePage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By FirstChoiceForAnyList = By.xpath("(//li[@role=\"option\"])[1]");
    // زرار الحفظ في أي popup هو الزرار الأسود
    private final By SaveButtonInPopUp = By.xpath("//div[@role='dialog']//button[contains(@class,'bg-black')]");

    // بيانات عامة
    private final By GeneralDataEditButton = By.xpath("//section[@id='general_info']//button");
    private final By WebsiteField = By.name("website");
    private final By HostOfSearchField = By.cssSelector("gmp-place-autocomplete");
    private final By SearchOfLocationField = By.cssSelector("input[part='input']");
    private final By BuildingNumberField = By.name("building_number");
    private final By GeneralDataSaveButton = By.xpath("//section[@id='general_info']//button[contains(@class,'bg-black')]");

    // البيانات البنكية
    private final By AddBankDataButton = By.xpath("//section[@id='bank_account']//button");
    private final By BankFullNameField = By.cssSelector("#bank_account #name");
    private final By BankNameList = By.cssSelector("#bank_account [aria-haspopup=listbox]");
    private final By IBANField = By.id("iban");
    private final By BankDataSaveButton = By.xpath("//section[@id='bank_account']//button[contains(@class,'bg-black')]");

    // الخدمات والمنتجات
    private final By AddServiceButton = By.xpath("//section[@id='services_and_products']//button[@type='button']");
    // أول اختيار هو الخدمة والتاني المنتج
    private final By ServiceChoice = By.xpath("(//div[@role='dialog']//div[@role='radio'])[1]");
    private final By ServiceNameField = By.xpath("//div[@role='dialog']//input[@name='name']");
    private final By ServiceDescField = By.xpath("//div[@role='dialog']//textarea[@id='description']");

    // الخبرات السابقة
    // أول زرار في السكشن هو القلم (سنوات الخبرة) والتاني إضافة خبرة جديدة
    private final By AddNewExperienceButton = By.xpath("(//section[@id='experience']//button)[2]");
    private final By ExperienceEventNameField = By.xpath("//div[@role='dialog']//input[@id='name']");
    private final By ExperienceEventTypeList = By.xpath("//div[@role='dialog']//button[@aria-haspopup='listbox']");
    private final By FromDateField = By.id("from-date-trigger");
    private final By ToDateField = By.id("to-date-trigger");
    private final By TenthDayInCalender = By.xpath("(//button[@name=\"day\"])[10]");
    private final By ExperienceDescField = By.xpath("//div[@role='dialog']//textarea[@id='description']");
    private final By AddExperienceButtonInPopUp = By.xpath("//div[@role='dialog']//button[@type='submit']");
    private final By AddYearsOfExperienceButton = By.xpath("//section[@id='experience']//button");
    private final By YearsOfExperienceField = By.id("years_of_experience");
    // زرار الحفظ بيظهر قبل خانة سنوات الخبرة على طول
    private final By YearsOfExperienceSaveButton = By.xpath("//input[@id='years_of_experience']/preceding::button[1]");




    public P03_ProfilePage CompleteTheProfile (String TestData , String Website , String Location , String BuildingNumber ,
                                               String IBAN , String YearsOfExperience) throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,GeneralDataEditButton);
        Utility.SENDKEYS(driver,WebsiteField,Website);
        Utility.SHADOW_SEARCH_AND_SELECT_FIRST(driver,HostOfSearchField,SearchOfLocationField,Location);
        Utility.SENDKEYS(driver,BuildingNumberField,BuildingNumber);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,GeneralDataSaveButton);

        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddBankDataButton);
        Utility.SENDKEYS(driver,BankFullNameField,TestData);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,BankNameList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.SENDKEYS(driver,IBANField,IBAN);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,BankDataSaveButton);

        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddServiceButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ServiceChoice);
        Utility.SENDKEYS(driver,ServiceNameField,TestData);
        Utility.SENDKEYS(driver,ServiceDescField,TestData);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SaveButtonInPopUp);

        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddNewExperienceButton);
        Utility.SENDKEYS(driver,ExperienceEventNameField,TestData);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ExperienceEventTypeList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FromDateField);
        Utility.SelectFirstEnableDayinCalender(driver);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ToDateField);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,TenthDayInCalender);
        Utility.SENDKEYS(driver,ExperienceDescField,TestData);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddExperienceButtonInPopUp);

        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddYearsOfExperienceButton);
        Utility.CLEAR_AND_SENDKEYS(driver,YearsOfExperienceField,YearsOfExperience);
        Utility.CLICKONVISIBLEELEMENTS(driver,YearsOfExperienceSaveButton);

        return this;
    }

}
