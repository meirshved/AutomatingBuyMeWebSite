package projectTests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import commonMethods.ScreenShot;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pageObjects.GiftCardsScreen;
import testsBases.CommonTestBase;
import java.io.IOException;

/**
 * QA Automation tests for GiftCards Screen
 *
 * @author Mayer Shved
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GiftCardsScreenTest extends CommonTestBase {

    //class fields
    private ScreenShot screenShot = new ScreenShot(singletonDriver.driver);
    private GiftCardsScreen giftCardsScreen = new GiftCardsScreen();
    private static ExtentTest testReportForGiftCardsScreen;

    /**
     * sets ExtentTest object for SignUp test extent report
     * @result Object of ExtentTest type must be created
     * for SignUp report
     */
    @Test
    public void test_01_SetUpExtentTest(){
        testReportForGiftCardsScreen = userAction
                .createExtentTest
                ("Home Screen Reports",
                "Open BuyMe landing page, singUp new user");
    }

    /**
    * sets ExtentTest object for Extras assignments reports
    * @result Object of ExtentTest type must be created
    * for Extras tests reports
    */
    @Test
    public void test_02_ExtrasReports()  {
        extras.setExtrasTestReports("Extras for Gift Cards Screen", "Extras Gift Cards Screen assignment");
    }

    /**
     * scroll down and take screenshot / Extras assignments
     * @result scroll to the bottom of the page
     * @result screenshot on the bottom of the page
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void test_03_scrollDownExtras() throws InterruptedException, IOException {
        Thread.sleep(1000);
        try {
            userAction.scrollPage(0, 3000);
            isClicked = true;
        }catch (Exception e){
            extras.getTestReportsForExtras().log(Status.ERROR, "page was not scrolled down");
            extras.getTestReportsForExtras().log(Status.INFO, e.getMessage());
        }finally {
            if (isClicked)
                screenShot.setScreenShotToReportDetails("scroll to bottom of the Home Screen", extras.getTestReportsForExtras());
        }
    }

    /**
     * scroll up and take screenshot / Extras assignments
     * @result scroll to the up of the page
     * @result screenshot on the up of the page
     * @throws IOException
     */
    @Test
    public void test_04_scrolUpExtras() throws IOException {
        try {
            userAction.scrollPage(0, -5000);
            isClicked = true;
        } catch (Exception e) {
            extras.getTestReportsForExtras().log(Status.ERROR, "page was not scrolled up");
            extras.getTestReportsForExtras().log(Status.INFO, e.getMessage());
        } finally {
            if (isClicked)
                screenShot.setScreenShotToReportDetails("scroll up the Home Screen", extras.getTestReportsForExtras());
        }
    }

    @Test
    public void test_05_clickGiftCardCompany() throws IOException {

        screenShot.setScreenShotToReportDetails("Gift cards screen",testReportForGiftCardsScreen);

        try{
            userAction.clickElement(giftCardsScreen.giftCardCompany);
            isClicked = true;
        }catch (Exception e){
            testReportForGiftCardsScreen.log(Status.ERROR, "gift card company was not clicked");
            testReportForGiftCardsScreen.log(Status.INFO, e.getMessage());
        }finally {
            if(isClicked)
                testReportForGiftCardsScreen.log(Status.PASS, "gift card company was clicked");
        }
    }
}
