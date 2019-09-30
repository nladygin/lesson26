import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CalculatorTest {

    private AndroidDriver<MobileElement> driver;


    @Before
    public void before() throws MalformedURLException {
        File apk = new File("C:\\Users\\ladn\\Downloads\\calc.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
            capabilities.setCapability(MobileCapabilityType.APP, apk.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
            capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @After
    public void after(){
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void plusTest(){

        driver
                .findElementByAndroidUIAutomator("new UiSelector().text(\"5\")")
                .click();
        driver
                .findElementByAndroidUIAutomator("new UiSelector().text(\"+\")")
                .click();
        driver
                .findElementByAndroidUIAutomator("new UiSelector().text(\"3\")")
                .click();
        driver
                .findElementByAndroidUIAutomator("new UiSelector().text(\"=\")")
                .click();

        WebElement result = driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").instance(0)");

        assertThat("8", equalTo(result.getText()));
    }


    @Test
    public void minusTest(){
        driver
                .findElementById("button5")
                .click();
        driver
                .findElementById("buttonMinus")
                .click();
        driver
                .findElementById("button3")
                .click();
        driver
                .findElementById("buttonEqual")
                .click();

        WebElement result = driver.findElementById("textView");

        assertThat("2", equalTo(result.getText()));
    }

}
