import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertEquals;

public class LoginTest extends TestBase {

    @Test
    public void testLoginAdmin() throws Exception {
        openMainPage();
        LoginData newLoginData = new LoginData();
        newLoginData.loginName = "admin";
        newLoginData.pass = "admin";
        fillLoginForm(newLoginData);
        clickLogin();


    }


    @Test
    public void testStickerOneExists() throws Exception {

        openShopPage();
        int count = findSizeByCss(".product.column.shadow.hover-light");

        String xBase = "(//li[contains(@class,'product')])[%d]//div[contains(@class, 'sticker')]";
       for (int i = 0; i < count; i++) {
          String locator = String.format(xBase, i + 1);
           int stickerCount = findSizeByxPath(locator);
           assertEquals(stickerCount, 1);

       }
    }


    @Test
    public void testHeader() throws Exception {

        testLoginAdmin();
        int count = findSizeByCss("ul#box-apps-menu li#app-");
        System.out.println("   !!!!!" + count);
        for (int i = 0; i < count; i++) {
            click("ul#box-apps-menu li#app-", i);
           if (isElementPresent(By.tagName("h1"))) {  System.out.println("   Exist!  "+ driver.findElement(By.tagName("h1")).getText());}
            sleep(400);

            if (findSizeByCss("ul#box-apps-menu li.selected ul li") != 0) {
                int internalCount = findSizeByCss("ul#box-apps-menu li.selected ul li");
                System.out.println("   !!!!!int" + internalCount);
                for (int c = 0; c < internalCount; c++) {
                    click("ul#box-apps-menu li.selected ul li", c);
                  if (isElementPresent(By.tagName("h1"))) {  System.out.println(driver.findElement(By.tagName("h1")).getText());}
                    sleep(400);
                }

            }
        }
    }



}