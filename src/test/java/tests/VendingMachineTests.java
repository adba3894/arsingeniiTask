package tests;

import enums.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.VendingMachineMainPage;

import java.util.concurrent.TimeUnit;

public class VendingMachineTests {
    private WebDriver driver;

    private VendingMachineMainPage vendingMachineMainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        vendingMachineMainPage = new VendingMachineMainPage(driver);
    }

    @Test // OK
    public void TC1() {
        vendingMachineMainPage.insertCoins(1, 0, 0, 0);
        vendingMachineMainPage.chooseProduct(Product.TWIX);
        Assert.assertEquals(vendingMachineMainPage.getTotalValue(), "0");
        Assert.assertEquals(vendingMachineMainPage.getResultValue(), "Twix has been bought. €3.00 returned.");
    }

    @Test // OK
    public void TC2() { // BUG (ID - 00001)
        vendingMachineMainPage.insertCoins(0, 1, 0, 0);
        vendingMachineMainPage.chooseProduct(Product.BROWNIE);
        Assert.assertEquals(vendingMachineMainPage.getTotalValue(), "0");
        Assert.assertEquals(vendingMachineMainPage.getResultValue(), "Twix has been bought.");
    }

    @Test
    public void TC3() { // BUG (ID - 00001)
        vendingMachineMainPage.insertCoins(0, 0, 0, 20);
        vendingMachineMainPage.chooseProduct(Product.TWIX);
        Assert.assertEquals(vendingMachineMainPage.getTotalValue(), "0");
        Assert.assertEquals(vendingMachineMainPage.getResultValue(), "Twix has been bought.");
    }

    @Test
    public void TC4() { // BUG (ID - 00002)
        vendingMachineMainPage.insertCoins(1, 1, 0, 0);
        vendingMachineMainPage.clickCancel();
        Assert.assertEquals(vendingMachineMainPage.getTotalValue(), "0");
        Assert.assertEquals(vendingMachineMainPage.getResultValue(), "Operation was canceled. €7.00 returned");
    }

    @Test
    public void TC5() { // BUG (ID - 00003)
        vendingMachineMainPage.insertCoins(0, 0, 1, 1);
        vendingMachineMainPage.chooseProduct(Product.CHOCOLATE);
        Assert.assertEquals(vendingMachineMainPage.getTotalValue(), "1.10");
        Assert.assertEquals(vendingMachineMainPage.getResultValue(), "Insert more €0.90 more.");
    }

    @Test
    public void TC6() {
        vendingMachineMainPage.chooseProduct(Product.TWIX);
        Assert.assertEquals(vendingMachineMainPage.getTotalValue(), "0.00");
        Assert.assertEquals(vendingMachineMainPage.getResultValue(), "Please insert the right amount of money");
    }

    @Test
    public void TC7() {
        vendingMachineMainPage.insertCoins(1, 2, 0, 3);
        vendingMachineMainPage.chooseProduct(Product.TWIX);
        Assert.assertEquals(vendingMachineMainPage.getTotalValue(), "0");
        Assert.assertEquals(vendingMachineMainPage.getResultValue(), "Twix has been bought. €7.30 returned.");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}