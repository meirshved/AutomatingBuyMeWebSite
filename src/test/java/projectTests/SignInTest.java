package projectTests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import commonMethods.Extras;
import commonMethods.ProjectConfigData;
import commonMethods.ScreenShot;
import pageObjects.SignIn;
import org.junit.runners.MethodSorters;
import org.junit.*;
import testsBases.SignIn_SignUp_TestBase;
import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInTest extends SignIn_SignUp_TestBase {

    private ScreenShot screenShot = new ScreenShot(singletonDriver.driver);
    private ProjectConfigData configData = new ProjectConfigData();
    private static SignIn signIn = new SignIn();
    private static ExtentTest testReportForSignIn;
    private static Extras extras = new Extras(singletonDriver.driver);

    private String url = "https://buyme.co.il/";

    @Test
    public void test_0_SetUpExtentTest() {
        testReportForSignIn = singletonReport.extentReport
                .createTest("SingIn existing user", "open BuyMeLandingPage, signIn existing user ");
    }

    @Test
    public void test_1_OpenBuyMeLandingPage() throws IOException {

        userAction.navigateToWebPage(configData.getBuyMeLandingPageUrl());

        if (url.equals(singletonDriver.driver.getCurrentUrl())) {
            testReportForSignIn.log(Status.PASS, "BuyMe landing page is oppened");
            screenShot.setScreenShotToReportDetails("BuyMe landing page", testReportForSignIn);
        } else {
            testReportForSignIn.log(Status.FAIL, "BuyMe landing page is not oppened");
            screenShot.setScreenShotToReportDetails("BuyMe landing page", testReportForSignIn);
        }
    }

    @Test
    public void test_2_ClickSignInSignUp() throws IOException {
        try {
            userAction.clickElement(signIn.signUpSignInButton);
            isClicked = true;
        } catch (Exception e) {
            testReportForSignIn.log(Status.ERROR, "SignInSignUp button was not clicked");
            testReportForSignIn.log(Status.INFO, e.getMessage());
        } finally {
            if (isClicked)
                testReportForSignIn.log(Status.PASS, "SignInSignUp button was clicked");
                screenShot.setScreenShotToReportDetails("SignIn Modal", testReportForSignIn);
        }
    }

    @Test
    public void test_3_SignInUser() throws IOException {
        SignInUser();
        testReportForSignIn.log(Status.FAIL, "missing user email and password");
        screenShot.setScreenShotToReportDetails("error messages",testReportForSignIn);
    }

    @Test
    public void test_4_assertEmailErrorMessage() {
        extras.setListOfElements(extras.emptyEmailAndPasswordWarning);
        extras.asserExtrasMethod(extras.ERRORMESSAGE,extras.webElements.get(0).getText());
    }

    @Test
    public void test_5_assertPasswordErrorMessage() {
        extras.setListOfElements(extras.emptyEmailAndPasswordWarning);
        extras.asserExtrasMethod(extras.ERRORMESSAGE, extras.webElements.get(1).getText());
    }

    @Test
    public void test_6_EnterEmail() {
        try {
            userAction.userInput(signIn.emailAdressElement, configData.getUserEmail());
            isClicked = true;
        } catch (Exception e) {
            testReportForSignIn.log(Status.ERROR, "email input failed");
            testReportForSignIn.log(Status.INFO, e.getMessage());
        } finally {
            if (isClicked)
                testReportForSignIn.log(Status.PASS, "email input is passed");
        }
    }

    @Test
    public void test_7_EnterPassword() throws IOException {

        try {
            userAction.userInput(signIn.passwordElement, configData.getUserPassword());
            isClicked = true;
        } catch (Exception e) {
            testReportForSignIn.log(Status.ERROR, "password input failed");
            testReportForSignIn.log(Status.INFO, e.getMessage());
        } finally {
            if (isClicked)
                testReportForSignIn.log(Status.PASS, "password input is passed");
                screenShot.setScreenShotToReportDetails("user info", testReportForSignIn);
        }
    }

    @Test
    public void test_8_signInUser(){
        SignInUser();
    }

    //check if it is good implementation and cleane code
    private void SignInUser(){
        try {
            userAction.clickElement(signIn.signInUserButton);
            isClicked = true;
        } catch (Exception e) {
            testReportForSignIn.log(Status.FAIL, "not succeeded to click button to signIn user");
            testReportForSignIn.log(Status.INFO, e.getMessage());
        } finally {
            if (isClicked)
                testReportForSignIn.log(Status.PASS, "signIn button is pressed");
        }
    }
}