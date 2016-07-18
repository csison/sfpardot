package Tests;

import Pages.Login;
import Pages.Pardot;
import Utilities.Constants;
import Utilities.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by nomerconrad on 7/17/2016.
 */
public class PardotTest {

    private WebDriver driver;
    private Login login;
    private Pardot pardot;
    String name = "";
    String first = "";
    String second ="";

    @BeforeClass
    public void setup() throws Exception{
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        login = new Login(driver);
        pardot = new Pardot(driver);
        verifyLogIn();
    }

    @AfterClass(alwaysRun = true)
    public void end(){
        driver.quit();
    }

    public void verifyLogIn() throws Exception{
        login.login();
        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
    }

    //step 3
    @Test(priority = 1)
    public void verifyDuplicatedList() throws Exception{
        this.name = "rand-" + Helper.createRandomName();;
        pardot.createList(this.name);
        pardot.navigateToListPage();
        pardot.createList(this.name);
        Assert.assertEquals(pardot.nameError.getText(), "Please input a unique value for this field" );
    }

    //steps 4-5
    @Test(priority = 2)
    public void verifyCreateNewListWithOriginalName() throws Exception{
        //edit the name of the first list created
        pardot.navigateToListPage();
        pardot.clickElement(driver.findElement(By.linkText(this.name)));
        this.first = driver.getCurrentUrl();
        pardot.editList("isnow-" + Helper.createRandomName());

        //create a new list with the original name
        pardot.navigateToListPage();
        pardot.createList(this.name);

        pardot.navigateToListPage();
        pardot.sleepWait(Constants.SLEEPMEDIUM);
        pardot.clickElement(driver.findElement(By.linkText(this.name)));
        this.second = driver.getCurrentUrl();

        System.out.println("first: " + first);
        System.out.println("second: " + second);
        Assert.assertNotEquals(first, second);
    }

    //steps 6-8
    @Test(priority = 3)
    public void verifyCreateAssignProspect(){
        pardot.createProspect(this.name);
        pardot.sleepWait(Constants.SLEEPMEDIUM);
        Assert.assertEquals(pardot.savedConfirmAlert.getText(), "Prospect saved successfully");
        pardot.assignProspectToList(this.name);
        pardot.sleepWait(Constants.SLEEPMEDIUM);
        Assert.assertEquals(pardot.prospectListAlert.getText(), "Prospect lists saved successfully");
    }

    @Test(priority = 4)
    public void verifyEmail(){
        pardot.createTextOnlyEmail(Helper.createRandomName());
        pardot.sleepWait(Constants.SLEEPMEDIUM);
        Assert.assertTrue(pardot.emailBlockedAlert.getText().contains("blocked"));

    }

    @Test(priority = 5)
    public void verifyLogout(){
        pardot.logout();
        pardot.sleepWait(Constants.SLEEPSHORT);
        Assert.assertEquals(driver.getCurrentUrl(), Constants.BASE_URL + "/");

    }

}
