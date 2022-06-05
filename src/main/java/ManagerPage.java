import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ManagerPage extends BasePage {

    public ManagerPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }
    public void managerLogIn() {
        try {
            WebElement managerLogInButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button"));
            managerLogInButton.click();
            Thread.sleep(1000);
            WebElement customerButtons = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[1]"));
            Assert.assertNotNull(customerButtons);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void createCustomer() {
        try {
            WebElement customerButtons = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[1]"));
            customerButtons.click();
            List<WebElement> inputFields = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("input")));
            WebElement firstName = inputFields.get(0);
            firstName.sendKeys("Nina");
            Thread.sleep(1000);
            WebElement lastName = inputFields.get(1);
            lastName.sendKeys("Milosevic");
            Thread.sleep(1000);
            WebElement postCode = inputFields.get(2);
            postCode.sendKeys("23000");
            Thread.sleep(1000);
            WebElement submitCustomerButton = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button")));
            submitCustomerButton.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(driver.switchTo().alert());
        driver.switchTo().alert().accept();
    }
    public void createCustomerAccount() {
        try {
            WebElement customerButtons =  driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[2]"));
            customerButtons.click();
            Select userSelect = new Select(driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userSelect"))));
            userSelect.selectByVisibleText("Nina Milosevic");
            Thread.sleep(1000);
            Select currencySelect = new Select(driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currency"))));
            currencySelect.selectByVisibleText("Dollar");
            Thread.sleep(1000);
            WebElement processButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button"));
            processButton.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(driver.switchTo().alert());
        driver.switchTo().alert().accept();
    }
}
