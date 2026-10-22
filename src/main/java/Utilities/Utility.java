package Utilities;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import Utilities.Scrolling;


import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Utility {
//    public static void CLICKONELEMENTS(WebDriver driver, By Locator ) throws InterruptedException {
//        Waits.WaitForElementClickable(driver,Locator,10);
//        driver.findElement(Locator).click();
//
//    }

    private static final By Loader = By.cssSelector("div.h-screen > svg, div.brand-splash, svg.absolute.inset-0");

    // التأخير بعد كل خطوة بالملّي ثانية، بيتقرا من أمر التشغيل الأول ولو مش موجود من ملف الانفيرومنت
    private static final long StepDelay = LoadStepDelay();

    private static long LoadStepDelay() {
        try {
            String value = System.getProperty("STEP_DELAY");
            if (value == null) value = DataUtiles.getPropertyValue("environment", "STEP_DELAY");
            return value == null ? 0 : Long.parseLong(value.trim());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    // يستنى بعد الخطوة عشان تقدر تتابع التيست بعينك (صفر = من غير تأخير)
    private static void WaitStepDelay(WebDriver driver) {
        if (StepDelay > 0) PAUSE(driver, StepDelay);
    }


    @Step("Clicking On :{Locator}")
    public static void CLICKONELEMENTS(WebDriver driver, By Locator) throws InterruptedException {
        Scrolling.ScrollToElement(driver, Locator);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(Locator));
        driver.findElement(Locator).click();
        WaitStepDelay(driver);
        LogsUtiles.info("Clicked on " + Locator.toString());

    }

    public static void WatingLoadingCircle_And_CLICKONELEMENTS(WebDriver driver, By Locator, By LoadingCircle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(Locator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingCircle));
        Scrolling.ScrollToElement(driver, Locator);
        driver.findElement(Locator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingCircle));
        LogsUtiles.info("Clicked on " + Locator.toString());
    }

    public static void WatingLoader_And_CLICKONELEMENTS(WebDriver driver, By Locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WaitLoaderGone(driver);
        wait.until(ExpectedConditions.elementToBeClickable(Locator));

        // scroll لنص الشاشة عشان أي sticky header/footer ميغطيش العنصر
        // (بيدور على العنصر من جديد لو الصفحة عملت re-render وبقى stale)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> ((JavascriptExecutor) d).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest'}); return true;",
                        d.findElement(Locator)));

        waitUntilStable(driver, Locator);

        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .ignoring(ElementClickInterceptedException.class, StaleElementReferenceException.class)
                    .until(d -> {
                        d.findElement(Locator).click();
                        return true;
                    });
        } catch (TimeoutException e) {
            LogsUtiles.info("Normal click failed, using JS click on " + Locator);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", driver.findElement(Locator));
        }

        WaitLoaderGone(driver);
        WaitStepDelay(driver);
        LogsUtiles.info("Clicked on " + Locator);
    }

    // يستنى رسالة النجاح تظهر (عشان البراوزر ميتقفلش قبل ما الطلب يوصل)
    public static void WAIT_FOR_TEXT(WebDriver driver, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(text()),'" + text + "')]")));
        LogsUtiles.info("Text appeared: " + text);
    }

    // يستنى أي رسالة (toast) تظهر ويرجع النص بتاعها
    public static String GET_TOAST_TEXT(WebDriver driver) {
        String text = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(visibilityOfElementLocated(By.cssSelector("[data-sonner-toast]"))).getText();
        LogsUtiles.info("Toast message: " + text);
        return text;
    }

    // يدوس على القائمة ولو العنصر اللي جواها مظهرش يدوس تاني (لو الصفحة لسه بتحمل وقفلت القائمة)
    public static void OPEN_MENU_AND_CLICK(WebDriver driver, By MenuLocator, By ItemLocator) {
        for (int i = 0; i < 3; i++) {
            WatingLoader_And_CLICKONELEMENTS(driver, MenuLocator);
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(visibilityOfElementLocated(ItemLocator));
                break;
            } catch (TimeoutException e) {
                LogsUtiles.info("Menu item not visible, reopening " + MenuLocator);
            }
        }
        WatingLoader_And_CLICKONELEMENTS(driver, ItemLocator);
    }

    // يدوس على التاب ولو متفعلش (الصفحة لسه بتحمل ومسكتش الكليك) يدوس تاني لحد ما aria-selected تبقى true
    public static void CLICK_TAB(WebDriver driver, By TabLocator) {
        for (int i = 0; i < 3; i++) {
            WatingLoader_And_CLICKONELEMENTS(driver, TabLocator);
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.attributeToBe(TabLocator, "aria-selected", "true"));
                return;
            } catch (TimeoutException e) {
                LogsUtiles.info("Tab not selected, clicking again " + TabLocator);
            }
        }
        throw new TimeoutException("Tab was not selected after 3 clicks: " + TabLocator);
    }

    // اللودر بيختفي بـ fade، فلو الـ animation وقفت قبل الآخر (البراوزر مش في الواجهة) بنعتبره اختفى لو شفاف
    private static void WaitLoaderGone(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> d.findElements(Loader).stream().noneMatch(e -> e.isDisplayed()
                        && Double.parseDouble(e.getCssValue("opacity")) > 0.1));
    }

    // يستنى العنصر يبطل حركة (نفس المكان مرتين ورا بعض)
    private static void waitUntilStable(WebDriver driver, By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(200))
                    .ignoring(StaleElementReferenceException.class)
                    .until(new ExpectedCondition<Boolean>() {
                        private Point last;

                        @Override
                        public Boolean apply(WebDriver d) {
                            Point now = d.findElement(locator).getLocation();
                            boolean stable = now.equals(last);
                            last = now;
                            return stable;
                        }
                    });
        } catch (TimeoutException ignored) {
            // لو فيه animation مستمرة منوقفش الاختبار
        }
    }

    public static void WatingLoadingCircle_And_CLICKON_WebElement(
            WebDriver driver, WebElement element, By loadingCircle) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingCircle));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void CLICKONVISIBLEELEMENTS(WebDriver driver, By Locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(Locator));
        driver.findElement(Locator).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(Locator));
        WaitStepDelay(driver);
    }

    //    @Step("Adding Data On: {Locator}")
    public static void SENDKEYS(WebDriver driver, By Locator, String text) {
        WaitLoaderGone(driver);
        // لو الصفحة اتحمّلت تاني والعنصر ضاع بندور عليه من جديد ونكتب
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    WebElement element = elementToBeClickable(Locator).apply(d);
                    if (element == null) return false;
                    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
                    element.sendKeys(text);
                    return true;
                });
        WaitStepDelay(driver);
        LogsUtiles.info(" Data sent to  " + Locator.toString() + " is " + text);

    }

    // زي SENDKEYS بس بيمسح القيمة القديمة الأول (للحقول اللي جاية بقيمة جاهزة)
    public static void CLEAR_AND_SENDKEYS(WebDriver driver, By Locator, String text) {
        Scrolling.ScrollToElement(driver, Locator);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(elementToBeClickable(Locator));
        driver.findElement(Locator).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        driver.findElement(Locator).sendKeys(text);
        WaitStepDelay(driver);
        LogsUtiles.info(" Data sent to  " + Locator.toString() + " is " + text);

    }

    public static void WatingAndSENDKEYS(WebDriver driver, By Locator, By LoadingCircle, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(visibilityOfElementLocated(Locator));
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(LoadingCircle));
        Scrolling.ScrollToElement(driver, Locator);
        driver.findElement(Locator).sendKeys(text);
        LogsUtiles.info(" Data sent to  " + Locator.toString() + " is " + text);

    }

    public static void SELECT(WebDriver driver, By locator, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(elementToBeClickable(locator));
        Scrolling.ScrollToElement(driver, locator);
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    public static String GETTEXT(WebDriver driver, By Locator, By LoadingCircle) {
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(elementToBeClickable(Locator));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(LoadingCircle));
        String Text = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(Locator)).getText();
        LogsUtiles.info(" Data of " + Locator.toString() + " is " + Text);
        return Text;
    }

    public static void HOVER(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).moveToElement(element).perform();
    }

    public static void SCREENSHOT(WebDriver driver, String ImageName) throws IOException {
        String Path = System.getProperty("user.dir") + "/src/main/java/Shots/";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File target = new File(Path + ImageName + ".png");
        FileUtils.copyFile(src, target);
    }

    public static int COUNT(WebDriver driver, By locator) {
        List<WebElement> products = driver.findElements(locator);
        return products.size();
    }

    public static void WaitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));


    }

    public static void SelectFirstEnableDayinCalender(WebDriver driver) throws InterruptedException {
        By locator = By.xpath("(//button[@name=\"day\"])[1]");
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(visibilityOfElementLocated(locator));
        Scrolling.ScrollToElement(driver, locator);
        CLICKONELEMENTS(driver, locator);
    }

    public static void SelectLastEnableDayinCalender(WebDriver driver) throws InterruptedException {
        By locator = By.xpath("(//button[@name=\"day\"])[30]");
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(visibilityOfElementLocated(locator));
        Scrolling.ScrollToElement(driver, locator);
        CLICKONELEMENTS(driver, locator);
    }


    public static void SHADOW_SEARCH_AND_SELECT_FIRST(WebDriver driver, By hostLocator, By innerCss, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WaitLoaderGone(driver);

        // 1) كليك على الحقل
        WebElement input = wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(hostLocator));
        try {
            input = input.getShadowRoot().findElement(innerCss);
        } catch (NoSuchShadowRootException e) {
            // الـ shadow root بقى closed، الكليك على الـ host نفسه بيعمل فوكس على الـ input اللي جواه
        }
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);
        input.click();

        pause(driver, 1000);   // استنى الـ dialog يفتح

        // 2) اكتب في العنصر اللي عليه الفوكس (مش في العنصر القديم)
        new Actions(driver).sendKeys(text).perform();

        pause(driver, 1500);   // استنى الاقتراحات تظهر

        // 3) اختار أول اقتراح
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        WaitStepDelay(driver);

        LogsUtiles.info("Searched and selected first suggestion for: " + text);
    }

    private static void pause(WebDriver driver, long millis) {
        ((JavascriptExecutor) driver).executeAsyncScript(
                "var cb = arguments[arguments.length - 1]; setTimeout(cb, arguments[0]);", millis);
    }

    public static void CheckURLAndNavigateToTargetURL(WebDriver driver, String targetUrl) {

        // 1) استنى إن اللوجين خلص فعلاً (اختار واحدة من التلاتة دي حسب حالتك)
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> !d.getCurrentUrl().contains("10.100.6.4"));   // أو أي جزء مميز في لينك صفحة اللوجين
        // 2) روح للينك المطلوب بنفسك
        driver.get(targetUrl);
        // 3) استنى الصفحة تحمل خالص
        Utility.WaitForPageLoad(driver);
    }

    public static void PAUSE(WebDriver driver, long milliseconds) {
        ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1]; setTimeout(callback, arguments[0]);",
                milliseconds);
    }

    public static void UPLOAD_FILE_AND_WAIT(WebDriver driver, By fileInputLocator, String filePath) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // انتظار عام قبل ما نبدأ (بدل Thread.sleep)
        PAUSE(driver, 2000);   // غيّر الرقم حسب الوقت اللي محتاجه

        // 0) استنى اللودر (لو شغال من عملية سابقة زي الحفظ) يختفي الأول
        WaitLoaderGone(driver);

        // 1) تأكد إن الـ input موجود وبقى جاهز فعلاً
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInputLocator));

        // 2) ابعت المسار (لو الصفحة اتحمّلت تاني بعد الحفظ والعنصر ضاع بندور عليه من جديد)
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    d.findElement(fileInputLocator).sendKeys(filePath);
                    return true;
                });
        LogsUtiles.info("File path sent: " + filePath);

        // 3) لو ظهر لودر بعد الرفع، استناه يختفي
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(Loader));
            WaitLoaderGone(driver);
        } catch (TimeoutException ignored) {
        }

        // 4) استنى الصفحة تخلص أي reload
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        WaitStepDelay(driver);
        LogsUtiles.info("File uploaded and confirmed: " + filePath);
    }
}
