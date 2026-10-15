package Portal_Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_ProfilePage {
    public P03_ProfilePage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By SaveButton = By.xpath("//button[normalize-space()='حفظ']");
    private final By FirstChoiceForAnyList = By.xpath("(//li[@role=\"option\"])[1]");

    // بيانات عامة
    private final By GeneralDataEditButton = By.xpath("//h2[text()='بيانات عامة']/parent::*//button");
    private final By WebsiteField = By.xpath("//input[@placeholder=\"الموقع الإلكتروني\"]");
    private final By HostOfSearchField = By.cssSelector("gmp-place-autocomplete");
    private final By SearchOfLocationField = By.cssSelector("input[part='input']");
    private final By BuildingNumberField = By.xpath("//input[@placeholder=\"رقم المبنى\"]");

    // البيانات البنكية
    private final By AddBankDataButton = By.xpath("//h2[text()='البيانات البنكية']/ancestor::div[1]/following-sibling::*//button[normalize-space()='إضافة البيانات']");
    private final By BankFullNameField = By.xpath("//input[@placeholder=\"الاسم الكامل\"]");
    private final By BankNameList = By.xpath("//span[text()='اسم البنك']");
    private final By IBANField = By.xpath("//input[@placeholder=\"رقم الآيبان\"]");

    // الخدمات والمنتجات
    private final By AddServiceButton = By.xpath("//h2[text()='الخدمات والمنتجات']/ancestor::div[1]/following-sibling::*//button[normalize-space()='إضافة']");
    private final By ServiceChoice = By.xpath("//div[@role='radio'][normalize-space()='الخدمة']");
    private final By ServiceNameField = By.xpath("//input[@placeholder=\"اسم الخدمة\"]");
    private final By ServiceDescField = By.xpath("//textarea[@placeholder=\"وصف الخدمة\"]");

    // الخبرات السابقة
    private final By AddNewExperienceButton = By.xpath("//button[normalize-space()='إضافة خبرة جديدة']");
    private final By ExperienceEventNameField = By.xpath("//div[@role='dialog']//input[@placeholder=\"اسم الفعالية\"]");
    private final By ExperienceEventTypeList = By.xpath("//div[@role='dialog']//span[text()='نوع الفعالية']");
    private final By FromDateField = By.xpath("//button[@id=\"from-date-trigger\"]");
    private final By ToDateField = By.xpath("//button[@id=\"to-date-trigger\"]");
    private final By TenthDayInCalender = By.xpath("(//button[@name=\"day\"])[10]");
    private final By ExperienceDescField = By.xpath("//div[@role='dialog']//textarea[@placeholder=\"الوصف\"]");
    private final By AddExperienceButtonInPopUp = By.xpath("//div[@role='dialog']//button[normalize-space()='إضافة']");
    private final By AddYearsOfExperienceButton = By.xpath("//h2[text()='الخبرات السابقة']/ancestor::div[1]/following-sibling::*//button[normalize-space()='إضافة البيانات']");
    private final By YearsOfExperienceField = By.xpath("//input[@id=\"years_of_experience\"]");




    public P03_ProfilePage CompleteTheProfile (String TestData , String Website , String Location , String BuildingNumber ,
                                               String IBAN , String YearsOfExperience) throws InterruptedException {
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,GeneralDataEditButton);
        Utility.SENDKEYS(driver,WebsiteField,Website);
        Utility.SHADOW_SEARCH_AND_SELECT_FIRST(driver,HostOfSearchField,SearchOfLocationField,Location);
        Utility.SENDKEYS(driver,BuildingNumberField,BuildingNumber);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SaveButton);

        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddBankDataButton);
        Utility.SENDKEYS(driver,BankFullNameField,TestData);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,BankNameList);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,FirstChoiceForAnyList);
        Utility.SENDKEYS(driver,IBANField,IBAN);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SaveButton);

        Utility.WatingLoader_And_CLICKONELEMENTS(driver,AddServiceButton);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,ServiceChoice);
        Utility.SENDKEYS(driver,ServiceNameField,TestData);
        Utility.SENDKEYS(driver,ServiceDescField,TestData);
        Utility.WatingLoader_And_CLICKONELEMENTS(driver,SaveButton);

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
        Utility.CLICKONVISIBLEELEMENTS(driver,SaveButton);

        return this;
    }

}
