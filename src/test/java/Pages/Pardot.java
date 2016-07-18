package Pages;

import Utilities.Constants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by nomerconrad on 7/17/2016.
 */
public class Pardot extends Base{

    @FindBy(id = "listxistx_link_create")
    public WebElement addListButton;

    @FindBy(css = "#name")
    public WebElement listNameTextBox;

    @FindBy(css = "#save_information")
    public WebElement createListButton;

    @FindBy(css = "#li_form_update > div:nth-child(1)" )
    public WebElement alertText;

    @FindBy(id = "error_for_name")
    public WebElement nameError;

    @FindBy(css = "#content > h1")
    public WebElement listName;

    @FindBy(linkText = "Edit")
    public WebElement editLink;

    @FindBy(css = "#name")
    public WebElement editNameTextBox;

    @FindBy(css = "#save_information")
    public WebElement editListButton;

    @FindBy(id = "pr_link_create")
    public WebElement addProspectButton;

    @FindBy(id = "email")
    public WebElement emailTextBox;

    @FindBy(id = "campaign_id")
    public WebElement campaignDropDown;

    @FindBy(id = "profile_id")
    public WebElement profileDropDown;

    @FindBy(id = "score")
    public WebElement scoreTextBox;

    @FindBy(name= "commit")
    public WebElement createProspectButton;

    @FindBy(css = "#center-stage > div.navbar > div > ul > li:nth-child(2) > a")
    public WebElement listTab;

    @FindBy(css = "div > b")
    public WebElement listCombo;

    @FindBy(css = "div.chzn-search > input[type=\"text\"]")
    public WebElement listComboTextBox;

    @FindBy(css = "#center-stage > form > div.form-actions > input.btn.btn-primary")
    public WebElement saveListButton;

    @FindBy(css ="#center-stage > div.alert.alert-info")
    public WebElement savedConfirmAlert;

    @FindBy(xpath = "//div[@id='center-stage']/div")
    public WebElement prospectListAlert;

    @FindBy(css = "#name")
    public WebElement marketingEmailName;

    @FindBy(id ="email_type_text_only")
    public WebElement textOnlyRadio;

    @FindBy(id ="save_information")
    public WebElement saveMarketingEmail;

    @FindBy(css = "#information_form > div:nth-child(3) > div > div > button")
    public WebElement folderButton;

    @FindBy(xpath = "//*[@id=\"ember1252\"]/i")
    public WebElement folderChoice;

    @FindBy(id = "select-asset")
    public WebElement folderSubmit;

    @FindBy(css = "#information_form > div.control-group.required.campaign_errors > div > div > button")
    public WebElement campaignButton;

    @FindBy(css= "h4.pull-left > i.icon-bullhorn")
    public WebElement campaignChoice;

    @FindBy(id = "select-asset")
    public WebElement campaignSubmit;

    @FindBy(css ="#template_select_list > div.content > ul > li:nth-child(1) > img")
    public WebElement templateChoice;

    @FindBy(css = "#template_confirm")
    public WebElement templateSubmit;

    @FindBy(css = "#save_footer")
    public WebElement saveEmailContent;

    @FindBy(id = "flow_sending")
    public WebElement sendEmail;

    @FindBy(css = "#schedule_sidebar > div.alert.alert-error")
    public WebElement emailBlockedAlert;

    public Pardot(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void createList(String name){
        navigateToListPage();
        clickElement(addListButton);
        setValue(listNameTextBox, name);
        clickElement(createListButton);
    }

    public void editList(String updated){
        clickElement(editLink);
        setValue(editNameTextBox, updated);
        clickElement(editListButton);
    }

    public void createProspect(String name){
        navigateToProspectsPage();
        clickElement(addProspectButton);
        setValue(emailTextBox, name + "@prospect.com");
        Select dropdownselection1 = new Select(campaignDropDown);
        dropdownselection1.selectByVisibleText("Adil Yellow Jackets");
        Select dropdownselection2 = new Select(profileDropDown);
        dropdownselection2.selectByVisibleText("Adil Yellow Jackets 1");

        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", scoreTextBox);
        setValue(scoreTextBox, "90");
        System.out.println(scoreTextBox.getText());
        sleepWait(Constants.SLEEPSHORT);
        waitForElementToBeVisible(createProspectButton);
        clickElement(createProspectButton);
    }

    public void assignProspectToList(String name){
        clickElement(listTab);
        sleepWait(Constants.SLEEPSHORT);
        clickElement(listCombo);
        sleepWait(Constants.SLEEPSHORT);
        waitForElementToBeVisible(listComboTextBox);
        listComboTextBox.clear();
        setValue(listComboTextBox, name);
        sleepWait(Constants.SLEEPSHORT);
        listComboTextBox.sendKeys(Keys.ENTER);
        clickElement(saveListButton);
    }

    public void createTextOnlyEmail(String name){
        navigateToEmail();
        setValue(marketingEmailName, name);
        sleepWait(Constants.SLEEPSHORT);
        clickElement(folderButton);
        clickElement(folderChoice);
        clickElement(folderSubmit);
        sleepWait(Constants.SLEEPSHORT);
        clickElement(campaignButton);
        sleepWait(Constants.SLEEPSHORT);
        clickElement(campaignChoice);
        clickElement(campaignSubmit);
        sleepWait(Constants.SLEEPSHORT);
        clickElement(textOnlyRadio);
        clickElement(saveMarketingEmail);
        sleepWait(Constants.SLEEPSHORT);
        clickElement(templateChoice);
        clickElement(templateSubmit);
        sleepWait(Constants.SLEEPSHORT);
        clickElement(saveEmailContent);
        clickElement(sendEmail);
        sleepWait(Constants.SLEEPSHORT);
    }

    public void logout(){driver.navigate().to(Constants.BASE_URL +"/user/logout");}
    public void navigateToListPage(){driver.navigate().to(Constants.BASE_URL + "/list");}
    public void navigateToProspectsPage(){driver.navigate().to(Constants.BASE_URL + "/prospect");}
    public void navigateToEmail(){driver.navigate().to(Constants.BASE_URL + "/email/draft/edit");}


}
