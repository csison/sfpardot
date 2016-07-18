package Pages;

import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by nomerconrad on 7/17/2016.
 */
public class Login extends Base{

    @FindBy(id = "email_address")
    public WebElement emailTextBox;

    @FindBy(id = "password")
    public WebElement passwordTextBox;

    @FindBy(css = "#rememberMeBox > div > input")
    public WebElement loginButton;

    public Login(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void login (){
        driver.navigate().to(Constants.BASE_URL);
        waitForElementToBeVisible(emailTextBox);
        setValue(emailTextBox, Constants.USERNAME);
        waitForElementToBeVisible(passwordTextBox);
        setValue(passwordTextBox, Constants.PASSWORD);
        clickElement(loginButton);
    }
}
