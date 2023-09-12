package pages;

import enums.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendingMachineMainPage {
    private static String PAGE_URL = "file:///C:/Users/adoma_ayibhsk/OneDrive/Desktop/vending%20machine/index.html";
    private WebDriver driver;
    @FindBy(xpath = "//button[contains(@id, 'Twix')]")
    private WebElement twixProductButton;
    @FindBy(xpath = "//button[contains(@id, 'Chocolate')]")
    private WebElement chocolateProductButton;
    @FindBy(xpath = "//button[contains(@id, 'Brownie')]")
    private WebElement brownieProductButton;
    @FindBy(xpath = "//input[contains(@name, '5')]")
    private WebElement fiveEuroCoinTextField;
    @FindBy(xpath = "//input[contains(@name, '2')]")
    private WebElement twoEuroCoinTextField;
    @FindBy(xpath = "//input[contains(@name, '1')]")
    private WebElement oneEuroCoinTextField;
    @FindBy(xpath = "//input[contains(@name, '0.1')]")
    private WebElement tenCentCoinTextField;
    @FindBy(xpath = "//div[contains(@class, 'amount')]//h3//span[contains(@id,'Total')]")
    private WebElement totalAmountLabel;

    @FindBy(xpath = "//div[contains(@class, 'amount')]//h4[contains(@id,'message')]")
    private WebElement resultLabel;

    @FindBy(xpath = "//button[contains(@id, 'cancel')]")
    private WebElement cancelButton;

    //Constructor
    public VendingMachineMainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void insertCoins(int quantityOfFiveEuroCoin, int quantityOfTwoEuroCoin, int quantityOfOneEuroCoin, int quantityOfTenCentCoin) {
        fiveEuroCoinTextField.sendKeys(Integer.toString(quantityOfFiveEuroCoin));
        twoEuroCoinTextField.sendKeys(Integer.toString(quantityOfTwoEuroCoin));
        oneEuroCoinTextField.sendKeys(Integer.toString(quantityOfOneEuroCoin));
        tenCentCoinTextField.sendKeys(Integer.toString(quantityOfTenCentCoin));
    }

    public void chooseProduct(Product product) {
        switch (product) {
            case TWIX:
                twixProductButton.click();
                break;
            case CHOCOLATE:
                chocolateProductButton.click();
                break;
            case BROWNIE:
                brownieProductButton.click();
                break;
        }
    }

    public String getTotalValue() {
        return totalAmountLabel.getText();
    }

    public String getResultValue() {
        return resultLabel.getText();
    }

    public void clickCancel() {
        cancelButton.click();
    }
}
