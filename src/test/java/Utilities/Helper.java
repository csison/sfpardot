package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * Created by nomerconrad on 7/17/2016.
 */
public class Helper {

    protected WebDriver driver;
    protected Helper(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public static String createRandomName(){
        final Random rand = new Random();
        StringBuilder builder = new StringBuilder();

        while(builder.toString().length() == 0){
            int length = 12;
            for(int i =0; i < length; i++)
                builder.append(Constants.ALPHABET.charAt(
                        rand.nextInt(Constants.ALPHABET.length())));
        }
        return builder.toString();
    }

    public void clickElement(WebElement element) {
        try {
            waitForElementToBeClickable(element);
            element.click();
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public void setValue(WebElement element, String value) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public void sleepWait(Long sleep){
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public void waitForElementToBeClickable(WebElement element){
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            sleepWait(Constants.SLEEPSHORT);
    }

    public void waitForElementToBeVisible(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
