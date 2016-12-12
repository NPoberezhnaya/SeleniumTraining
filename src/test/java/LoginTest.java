import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginTest extends TestBase {

    @Test
    public void testLoginAdmin(String user, String pass) throws Exception {
        openPage(baseUrl + "admin");
        LoginData newLoginData = new LoginData();
        newLoginData.loginName = user;
        newLoginData.pass = pass;
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
        testLoginAdmin("admin", "admin");
        sleep(2500);

        int count = findSizeByxPath(".//*[@id='app-']/a/span[2]");

        for (int i = 0; i < count; i++) {
            click(".//*[@id='app-']/a/span[2]", i);

            if (isElementPresent(By.tagName("h1"))) {  System.out.println("   Exists!  "+ driver.findElement(By.tagName("h1")).getText());}

            sleep(700);

            if (findSizeByxPath(".//*[@class='docs']//span") != 0) {
                int internalCount = findSizeByxPath(".//*[@class='docs']//span");

                for (int c = 0; c < internalCount; c++) {
                    click(".//*[@class='docs']//span", c);
                  if (isElementPresent(By.tagName("h1"))) { System.out.println("   Exists!  " + driver.findElement(By.tagName("h1")).getText());}
                    sleep(700);
                }

            }
        }
    }


    @Test
    public void testSortCountry() throws Exception {
        testLoginAdmin("admin", "admin");

        openPage("http://localhost/litecart/admin/?app=countries&doc=countries");
        sleep(800);
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
    public void testSortGeoZones() throws Exception {
        testLoginAdmin("admin", "admin");

        openPage("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        sleep(500);
        int count = findSizeByxPath(".//*[@id='content']/form/table/tbody//a");
        String xBase = ".//*[@id='content']/form/table/tbody/tr[%d]//a";
        ArrayList<String> geoZones = new ArrayList();
        ArrayList<String> geoZonesSort = new ArrayList();
        for (int i = 1; i < count; i++) {
            String locator = String.format(xBase, i + 1);
            String s = driver.findElement(By.xpath(locator)).getText();
            geoZones.add(s);
        }
        geoZonesSort.addAll(geoZones);
        boolean b = geoZones.contains(geoZonesSort);
        assertEquals(geoZones, geoZonesSort);
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
    public void testCreateNewUser1() throws Exception {
        openPage("http://localhost/litecart/");
       // sleep(250);


        String locatorNewUser = ".//*[@id='box-account-login']//a";

        //Click at Users button
        clickEl(locatorNewUser);
        String locatorInputTaxID = ".//*[@id='create-account']//input[contains (@name, 'tax_id')]";
        String locatorInputCompany = ".//*[@id='create-account']//input[contains (@name, 'company')]";
        String locatorInputFirstName = ".//*[@id='create-account']//input[contains (@name, 'firstname')]";
        String locatorInputLastName = ".//*[@id='create-account']//input[contains (@name, 'lastname')]";
        String locatorInputAddress1 = ".//*[@id='create-account']//input[contains (@name, 'address1')]";
        String locatorInputAddress2 = ".//*[@id='create-account']//input[contains (@name, 'address2')]";
        String locatorPostCode = ".//*[@id='create-account']//input[contains (@name, 'postcode')]";
        String locatorCity = ".//*[@id='create-account']//input[contains (@name, 'city')]";
        String locatorEmail = ".//*[@id='create-account']//input[contains (@name, 'email')]";
        String locatorPhone = ".//*[@id='create-account']//input[contains (@name, 'phone')]";
        String locatorPass = ".//*[@id='create-account']//td[1]/input[contains (@name, 'password')]";
        String locatorPassRepeat = ".//*[@id='create-account']//input[contains (@name, 'confirmed_password')]";
        String locatorNewLetter = ".//*[@id='create-account']//input[contains (@name, 'newsletter')]";
        String locatorCreateAcc = ".//*[@id='create-account']//button";





        int r = (int)(Math.random()*10+10) ;


        String inputTaxID = r + "";
        String inputCompany = "Company" + r;
        String firstName = "FirstName" + r;
        String inputLastName = "LastName" + r;
        String inputAddress1 = "Address1" + r;
        String inputAddress2 = "Address2" + r;
        String inputPostCode = "65000";
        String inputCity = "City" + r;
        String inputEmail = "email" + r + "@com";
        String inputPhone = "111 111 111 1";
        String inputPass = "Pass" + r;
        String inputPassRepeat =  "Pass" + r;


        sleep(500);

        findEl(locatorInputTaxID).sendKeys(inputTaxID);
        findEl(locatorInputCompany).sendKeys(inputCompany);
        findEl(locatorInputFirstName).sendKeys(firstName);
        findEl(locatorInputLastName).sendKeys(inputLastName);
        findEl(locatorInputAddress1).sendKeys(inputAddress1);
        findEl(locatorInputAddress2).sendKeys(inputAddress2);
        findEl(locatorPostCode).sendKeys(inputPostCode);
        findEl(locatorCity).sendKeys(inputCity);
        findEl(locatorEmail).sendKeys(inputEmail);

        findEl(locatorPhone).sendKeys(inputPhone);
        findEl(locatorPass).sendKeys(inputPass);
        findEl(locatorPassRepeat).sendKeys(inputPass);


        if ( !findEl(locatorNewLetter).isSelected() )
        {
            clickEl(locatorNewLetter);
        }

        //////
       // String locatorCountry = ".//*[@id='select2-country_code-jx-container']";
        String locatorCountry = ".//*[@name='country_code']";
        Select select = new Select(findEl(locatorCountry));

        select.selectByVisibleText("Ukraine");
        sleep(500);
        // select.getFirstSelectedOption().getText();
        ////////////

        clickEl(locatorCreateAcc);
        sleep(500);


        String locatorLogout = " .//*[@id='box-account']//a[contains(@href, 'logout')]";
        clickEl(locatorLogout);
        sleep(500);

        String locatorLoginEmail = " .//*[@id='box-account-login']//input[contains(@name, 'email')]";
        String locatorLoginPass = " .//*[@id='box-account-login']//input[contains(@name, 'password')]";
        String locatorLoginClickLoginButton = " .//*[@id='box-account-login']//button[contains (@name, 'login')]";

        findEl(locatorLoginEmail).sendKeys(inputEmail);
        findEl(locatorLoginPass).sendKeys(inputPass);
        sleep(500);
        clickEl(locatorLoginClickLoginButton);
        sleep(500);
        clickEl(locatorLogout);

    }




        @Test
    public void testCreateNewUser() throws Exception {
        testLoginAdmin("admin", "admin");
        String locatorUser = ".//*[@id='box-account-login']/div/form//td/a";
        String locatorNewUser = ".//*[@id='content']/div/a[contains(@href, 'user')]";
        //Click at Users button
        clickEl(locatorUser);
        sleep(500);

        //Click at Create User button
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

        //fill in user data
        int r = (int)(Math.random()*10+10) ;
        String inputUserName = "User" + r + "@.com";
        String inputUserPass = "Pass" + r;


        System.out.println(inputUserName);
        System.out.println(inputUserPass);
///////////////////
        Date curdate = new Date();
        String inputUserData;
        String inputUserExp;
        Date yourDate = new Date();
        Date newDate = new Date();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(yourDate);


        inputUserData = date;


        Calendar c=Calendar.getInstance();
        int year=c.get(c.YEAR);
        int month=c.get(c.MONTH)+1;
        int day=c.get(c.DAY_OF_MONTH);


        Calendar c1 = Calendar.getInstance();
        c1.setTime(yourDate);
        c1.add(Calendar.YEAR, 1);
        newDate = c1.getTime();
        inputUserExp = DATE_FORMAT.format(newDate);

////////////////////
        findEl(locatorInputUserName).sendKeys(inputUserName);
        sleep(1000);

        findEl(locatorInputUserPass).sendKeys(inputUserPass);
        sleep(1000);

        findEl(locatorInputUserPassRepeat).sendKeys(inputUserPass);
        sleep(10000);

        findEl(locatorInputUserBlockUntil).sendKeys(inputUserData);
        sleep(1000);

        findEl(locatorInputUserBlockExp).sendKeys(inputUserExp);
        sleep(1000);

        String locatorCheckBoxEnabled = ".//*[@id='content']/form/table/tbody//input[contains(@type, 'checkbox')]";
        clickEl(locatorCheckBoxEnabled);


        //save user data
        clickEl(locatorInputUserSaveButton);
        sleep(1000);


       //logout
       String locatorLogoutButton = ".//*[@id='sidebar']/div[contains(@class, 'header')]/a[contains(@href, 'logout')]/i";
        clickEl(locatorLogoutButton);
        sleep(500);
        System.out.println(inputUserName);
        System.out.println(inputUserPass);
        //login with new user
        testLoginAdmin(inputUserName, inputUserPass);
        sleep(500);

        //logout
        clickEl(locatorLogoutButton);
        sleep(500);

    }



    @Test
    public void testAddNewProduct() throws Exception {
        testLoginAdmin("admin", "admin");
        String locatorCatalog = ".//*['app-']/div[contains(@id, 'box-apps-menu-wrapper')]//li[contains(@id,'app-')]/a[contains(@href, 'catalog&doc=catalog')]";
        String locatorNewProduct = ".//*[@id='content']//a[contains(@href, 'edit_product')]";
        sleep(2500);

        //Click at Catalog button
        clickEl(locatorCatalog);
        sleep(2500);

        //Click at Create Product button
        clickEl(locatorNewProduct);
        sleep(1000);


        String locatorNameProduct = ".//*[@id='tab-general']/table//input[contains(@name, 'name')]";
        String locatorCodeProduct = ".//*[@id='tab-general']/table//input[contains(@name, 'code')]";
        String locatorCategory = ".//*[@id='tab-general']/table//input[contains(@data-name, 'Rubber Ducks')]";
        String locatorGroup =  ".//*[@id='tab-general']/table//input[contains(@value, '1-2')]";
        String locatorQuantity =  ".//*[@id='tab-general']//input[contains(@name, 'quantity')]";

        String locatorSelectDefaultCategory =  ".//*[@id='tab-general']//select[contains (@name, 'default_category_id')]";
        String locatorSelectQuantityUnit =  ".//*[@id='tab-general']//select[contains (@name, 'quantity_unit_id')]";
        String locatorSelectDeliveryStatus =  ".//*[@id='tab-general']//select[contains (@name, 'delivery_status_id')]";
        String locatorSelectSoldOutStatus =  ".//*[@id='tab-general']//select[contains (@name, 'sold_out_status_id')]";
        String locatorUploadImages =  ".//*[@id='tab-general']//input[contains(@name, 'new_images[]')]";
        String locatorData =  ".//*[@id='tab-general']//input[contains(@name, 'date_valid_from')]";
        String locatorDataValidTo =  ".//*[@id='tab-general']//input[contains(@name, 'date_valid_to')]";

    ////////////////////////////////////////
        ///General tab
        ///////////////////////////////////////
        int r = (int)(Math.random()*10+10) ;

        findEl(locatorNameProduct).sendKeys("NameNewProduct" + r);
        findEl(locatorCodeProduct).sendKeys("123" + r);
        sleep(1000);

        clickEl(locatorCategory);
        sleep(1000);

        /////////////////////
        Select select = new Select(findEl(locatorSelectDefaultCategory));
        select.selectByVisibleText("Rubber Ducks");
        sleep(1000);


        /////////////////////
        select = new Select(findEl(locatorSelectQuantityUnit));
        select.selectByVisibleText("pcs");
        sleep(1000);

        /////////////////////
        select = new Select(findEl(locatorSelectDeliveryStatus));
        select.selectByVisibleText("3-5 days");
        sleep(1000);
        //////////////

        /////////////////////
        select = new Select(findEl(locatorSelectSoldOutStatus));
        select.selectByVisibleText("Sold out");
        sleep(500);
        //////////////

        clickEl(locatorGroup);
        findEl(locatorQuantity).sendKeys(r + "");
        sleep(500);


        ////
        String filepath = "G:\\Idea\\file1.jpg";
        File file = new File(filepath);
        findEl(locatorUploadImages).sendKeys(file.getAbsolutePath());
        sleep(1500);
        ////////////////
        ///Date
        ////////////////

        Date curdate = new Date();
        String dateValidFrom;
        String dateValidTo;
        Date yourDate = new Date();
        Date newDate = new Date();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(yourDate);


        dateValidFrom = date;


        Calendar c=Calendar.getInstance();
        int year=c.get(c.YEAR);
        int month=c.get(c.MONTH)+1;
        int day=c.get(c.DAY_OF_MONTH);


        Calendar c1 = Calendar.getInstance();
        c1.setTime(yourDate);
        c1.add(Calendar.YEAR, 1);
        newDate = c1.getTime();
        dateValidTo = DATE_FORMAT.format(newDate);

        findEl(locatorData).sendKeys(dateValidFrom);
        findEl(locatorDataValidTo).sendKeys(dateValidTo);
        sleep(500);

        ////////////////////////////////////////////////////////////
        ////Information tab
        ////////////////////////////////////////////////////////////
        String locatorInformationTab =  ".//*[@id='content']//li/a[contains(@href, '#tab-information')]";
        String locatorSelectManuf =  ".//*[@id='tab-information']//select[contains(@name, 'manufacturer_id')]";
        String locatorSelectSuplier =  ".//*[@id='tab-information']//select[contains(@name, 'supplier_id')]";

        String locatorKeywords =  ".//*[@id='tab-information']//input[contains(@name, 'keywords')]";
        String locatorShortDesc =  ".//*[@id='tab-information']//input[contains(@name, 'short_description[en]')]";
        String locatorDesc =  ".//*[@id='tab-information']//div[contains(@dir, 'ltr')]";
        String locatorHeadTitle =  ".//*[@id='tab-information']//input[contains(@name, 'head_title[en]')]";
        String locatorMetaDescription =  ".//*[@id='tab-information']//input[contains(@name, 'meta_description[en]')]";


        clickEl(locatorInformationTab);

        select = new Select(findEl(locatorSelectManuf));
        select.selectByVisibleText("ACME Corp.");

        sleep(500);
        findEl(locatorKeywords).sendKeys("Keywords" + r);
        findEl(locatorShortDesc).sendKeys("Short Description" + r);
        findEl(locatorDesc).sendKeys("Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Description" + r);
        findEl(locatorHeadTitle).sendKeys("Head Title" + r);
        findEl(locatorMetaDescription).sendKeys("Meta" + r);
        ////////////////////////////////////////////////////////////
        ////Price tab
        ////////////////////////////////////////////////////////////

        String locatorPriceTab =  ".//*[@id='content']//li/a[contains(@href, '#tab-prices')]";
        String locatorPurchase = ".//*[@id='tab-prices']//input[contains (@name, 'purchase_price')]";
        String locatorSelectPurchase = ".//*[@id='tab-prices']//select[contains(@name, 'purchase_price_currency_code')]";
        String locatorSelectTax = ".//*[@id='tab-prices']//select[contains(@name, 'tax_class_id')]";


        String locatorPriceDoll = ".//*[@id='tab-prices']//span[contains(@class, 'input-wrapper')]/input[contains(@name, 'prices[USD]')]";
        String locatorPriceEur = ".//*[@id='tab-prices']//span[contains(@class, 'input-wrapper')]/input[contains(@name, 'prices[EUR]')]";

        String locatorPriceDollQuan = ".//*[@id='tab-prices']//input[contains(@name, 'gross_prices[USD]')]";
        String locatorPriceEurQuan = ".//*[@id='tab-prices']//input[contains(@name, 'gross_prices[EUR]')]";


        clickEl(locatorPriceTab);
        sleep(500);
        findEl(locatorPurchase).sendKeys(r + "");

        select = new Select(findEl(locatorSelectPurchase));
        select.selectByVisibleText("Euros");
        sleep(500);

        findEl(locatorPriceDoll).sendKeys(r + "");
        sleep(500);

        findEl(locatorPriceEur).sendKeys(r + "");
        sleep(500);

        findEl(locatorPriceDollQuan).sendKeys(r + "");
        sleep(500);

        findEl(locatorPriceEurQuan).sendKeys(r + "");
        sleep(500);

        String locatorSaveButton =  ".//*[@id='content']/form/p/span/button[contains(@name, 'save')]";
        clickEl(locatorSaveButton);
        sleep(4500);

    }


    @Test
    public void testBasketCheck() throws Exception {
        openPage("http://localhost/litecart/");

        ////user with login/pass  exists

        String locatorLoginEmail = " .//*[@id='box-account-login']//input[contains(@name, 'email')]";
        String locatorLoginPass = " .//*[@id='box-account-login']//input[contains(@name, 'password')]";
        String locatorLoginClickLoginButton = " .//*[@id='box-account-login']//button[contains (@name, 'login')]";
        int quantityInBasket = 0;

        findEl(locatorLoginEmail).sendKeys("q1111@com");
        findEl(locatorLoginPass).sendKeys("q1111");

        clickEl(locatorLoginClickLoginButton);


        // wait until header appears
        WebDriverWait wait = new WebDriverWait(driver, 30/*seconds*/);
        WebElement element = wait.until(presenceOfElementLocated(By.xpath(".//*[@id='box-category-tree']/h3")));



        String locatorProductNameOnMainPage = ".//*[@id='box-campaigns']//a[1]/div[contains(@class, 'name')]";
        clickEl(locatorProductNameOnMainPage);

        //3 products
        for (int i = 1; i < 5; i++) {
            String locatorProductName = ".//*[@id='box-product']//h1";
            element = wait.until(presenceOfElementLocated(By.xpath(locatorProductName)));

            String locatorSelectSize = ".//*[@id='box-product']//select";
            Select select = new Select(findEl(locatorSelectSize));
            select.selectByVisibleText("Small");

            String locatorProductPrice = ".//*[@id='box-product']//div[contains(@class, 'price')]/strong";
            String priceOnChildPage =   driver.findElement(By.xpath(locatorProductPrice)).getText();



            priceOnChildPage = priceOnChildPage.substring(1, priceOnChildPage.length());


            String locatorAddButton = ".//*[@id='box-product']//button";
            clickEl(locatorAddButton);
            quantityInBasket = quantityInBasket + 1;

            Integer price = 0;
            try {
                 price = Integer.parseInt(priceOnChildPage);

                 price = price * quantityInBasket;
               // System.out.println(price);
            }catch (NumberFormatException e) {
                System.err.println("Неверный формат строки!");
            }

            String basketText;
            basketText = quantityInBasket + " item(s) - $" + price  + "";
            System.out.println(basketText);

            wait.until(ExpectedConditions.textToBePresentInElement(findEl(".//*[@id='cart']/a[2]"), basketText));

        }



        //CheckOut
        String locatorCheckOut = ".//*[@id='cart']/a[3]";
        clickEl(locatorCheckOut);
        String locatorTable = ".//*[@id='box-checkout-summary']/h2";
        wait.until(presenceOfElementLocated(By.xpath(locatorTable)));

        //Update the Backet
        String locatorUpdateCount = ".//*[@id='box-checkout-cart']//input[contains(@name, 'quantity')]";
        String locatorUpdateButton = ".//*[@id='box-checkout-cart']//button[contains(@name, 'update_ca')]";
        String locatorBasketTable = ".//*[@id='order_confirmation-wrapper']/table/tbody/tr[2]/td[1]";


        for (int i = 4; i > 1; i--) {
            findEl(locatorUpdateCount).clear();
           // findEl(locatorUpdateCount).sendKeys("1");
            String s = Integer.toString(i - 1);
            findEl(locatorUpdateCount).sendKeys(s);
            clickEl(locatorUpdateButton);
            wait.until(ExpectedConditions.textToBePresentInElement(findEl(locatorBasketTable), s));

    }

        //last product
        findEl(locatorUpdateCount).clear();
        findEl(locatorUpdateCount).sendKeys("0");
        clickEl(locatorUpdateButton);


    }




}