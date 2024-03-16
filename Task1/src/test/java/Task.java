import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Task {
    WebDriver driver;
    String item = "add-to-cart-sauce-labs-backpack";
    String cartName = "//*[@id=\"item_4_title_link\"]/div";
    String cartPrice = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div";

    @BeforeTest
    public void SetUp() {
        driver = new ChromeDriver(); //Invoke the browser
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void Cart(){
        driver.findElement(By.id(item)).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        WebElement verifyName = driver.findElement(By.xpath(cartName));
        String nameText = verifyName.getText();
        WebElement verifyPrice = driver.findElement(By.xpath(cartName));
        String priceText = verifyPrice.getText();

        if(check()){
            System.out.println("An item found");
        }else {
            System.out.println("No item found");
        }

        if(nameText.contains("Sauce Labs Backpack") || priceText.contains("29.99")){
            System.out.println("The Item is exist");
        }else {
            System.out.println("The Item is not exist");
        }
    }

    public boolean check() {
        List<WebElement> items = driver.findElements(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]"));
        return !items.isEmpty();
    }

    @AfterTest
    public void End(){
        driver.quit();
    }
}
