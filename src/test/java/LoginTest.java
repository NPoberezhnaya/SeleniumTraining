import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertEquals;

public class LoginTest extends TestBase {

    @Test
    public void testLoginAdmin() throws Exception {
        openPage(baseUrl + "/admin");
        LoginData newLoginData = new LoginData();
        newLoginData.loginName = "admin";
        newLoginData.pass = "admin";
        fillLoginForm(newLoginData);
        clickLogin();


    }


    @Test
    public void testStickerOneExists() throws Exception {

        openPage(baseUrl);
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


        int count = findSizeByxPath(".//*[@id='app-']/a/span[2]");

        for (int i = 0; i < count; i++) {
            click(".//*[@id='app-']/a/span[2]", i);

            if (isElementPresent(By.tagName("h1"))) {  System.out.println("   Exists!  "+ driver.findElement(By.tagName("h1")).getText());}

            sleep(400);

            if (findSizeByxPath(".//*[@class='docs']//span") != 0) {
                int internalCount = findSizeByxPath(".//*[@class='docs']//span");

                for (int c = 0; c < internalCount; c++) {
                    click(".//*[@class='docs']//span", c);
                  if (isElementPresent(By.tagName("h1"))) { System.out.println("   Exists!  " + driver.findElement(By.tagName("h1")).getText());}
                    sleep(400);
                }

            }
        }
    }


    @Test
    public void testSortCountry() throws Exception {
        testLoginAdmin();

        openPage("http://localhost/litecart/admin/?app=countries&doc=countries");
        sleep(500);
     int count = findSizeByxPath(".//*[@id='content']/form/table/tbody//td[5]/a");
        String xBase = ".//*[@id='content']/form/table/tbody/tr[%d]/td[5]/a";
        ArrayList<String> countryNames = new ArrayList();
        ArrayList<String> countryNamesSort = new ArrayList();
      for (int i = 1; i < count; i++) {
           String locator = String.format(xBase, i + 1);
           String s = driver.findElement(By.xpath(locator)).getText();
           countryNames.add(s);
      }
        countryNamesSort.addAll(countryNames);
        boolean b = countryNames.contains(countryNamesSort);
        assertEquals(countryNames, countryNamesSort);
     }


    @Test
    public void testOpenPageCorrectly() throws Exception {

        openPage("http://localhost/litecart/");
        sleep(500);

        String locatorProductOnMainPage = ".//*[@id='box-campaigns']//div[contains(@class, 'content')]//a[contains(@class, 'link')]";
        String locatorProductNameOnMainPage = ".//*[@id='box-campaigns']//a[1]/div[contains(@class, 'name')]";
        String locatorProductPriceOnMainPage = ".//*[@id='box-campaigns']/div//a[1]//div[contains(@class, 'price')]/strong";


        String locatorProductName = ".//*[@id='box-product']//h1";
        String locatorProductPrice = ".//*[@id='box-product']//div[contains(@class, 'price')]/strong";

        String nameOnMainPage = driver.findElement(By.xpath(locatorProductNameOnMainPage)).getText();
        String priceOnMainPage = driver.findElement(By.xpath(locatorProductPriceOnMainPage)).getText();

        clickEl(locatorProductOnMainPage);
        sleep(500);

        String nameOnChildPage = driver.findElement(By.xpath(locatorProductName)).getText();
        String priceOnChildPage = driver.findElement(By.xpath(locatorProductPrice)).getText();


        assertEquals(nameOnMainPage , nameOnMainPage);
        assertEquals(priceOnMainPage , priceOnChildPage);
    }


    @Test
    public void testCreateNewUser() throws Exception {
        testLoginAdmin();
        String locatorUser = ".//*['app-']/div[contains(@id, 'box-apps-menu-wrapper')]//a[contains(@href, 'users')]";
        String locatorNewUser = ".//*[@id='content']/div/a[contains(@href, 'user')]";

        clickEl(locatorUser);
        sleep(500);

        clickEl(locatorNewUser);
        sleep(500);

        String locatorInputUserName = ".//*[@id='content']/form/table/tbody//input[contains(@name, 'username')]";
        String locatorInputUserPass = ".//*[@id='content']/form/table/tbody//td[1]/input[contains(@name, 'password')]";
        String locatorInputUserPassRepeat = ".//*[@id='content']/form/table/tbody//td[2]/input[contains(@name, 'password')]";
        String locatorInputUserBlockUntil = ".//*[@id='content']/form/table/tbody//input[contains(@name, 'date_blocked')]";
        String locatorInputUserBlockExp = ".//*[@id='content']/form/table/tbody//input[contains(@name, 'date_expires')]";
        String locatorInputUserSaveButton = ".//*[@id='content']/form/p/span/button[contains(@type, 'submit')]";


        String locatorLoginUser = ".//*[@id='box-account-login']/div//input[contains(@name, 'email')]";
        String locatorLoginUserPass = ".//*[@id='box-account-login']/div//input[contains(@name, 'password')]";
        String locatorLoginUserButton = ".//*[@id='box-account-login']/div//span/button[contains(@name, 'login')]";

        //Random r = new Random(System.currentTimeMillis());
        Random r = new Random(800);
        String inputUserName = "User" + r;
        String inputUserPass = "Pass" + r;

        findEl(locatorInputUserName).sendKeys(inputUserName);
        sleep(1000);

        findEl(locatorInputUserPass).sendKeys(inputUserPass);
        sleep(1000);

        findEl(locatorInputUserPassRepeat).sendKeys(inputUserPass);
        sleep(1000);

        clickEl(locatorInputUserSaveButton);
        sleep(1000);

        Date curdate = new Date();
        System.out.println(curdate.toString());



        openPage("http://localhost/litecart/");
        sleep(500);
        findEl(locatorLoginUser).sendKeys(inputUserName);
        sleep(1000);

        findEl(locatorLoginUserPass).sendKeys(inputUserPass);
        sleep(1000);


        clickEl(locatorLoginUserButton);

       // assertEquals(countryNames, countryNamesSort);
    }



}