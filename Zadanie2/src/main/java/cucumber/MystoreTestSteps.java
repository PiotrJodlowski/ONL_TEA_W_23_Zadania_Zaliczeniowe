package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;



public class MystoreTestSteps {

    private WebDriver driver;  //WAZNE!!! Musi być ustawiona zmienna poza metodami, żeby przekazywały sobie wartość
    private String orderReference;
    private String priceTotal;

        @Given("an open browser with Mystore Test Lab")
        public void openMyStore(){
            // Skonfiguruj sterownik przeglądarki
            System.setProperty("webdriver.chrome.driver",
                    "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://mystore-testlab.coderslab.pl/index.php");
        }

       @Then("SignIn icon is clicked")
        public void clickSignIn(){
                WebElement signInIcon = driver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a/span"));
                signInIcon.click();
        }

        @When("Login Page is loaded login to store")
        public void logIN() {

            WebElement email = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.name("email")));
            email.sendKeys("rogalski@gmail.com");

            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("Pass123");

            try {
                WebElement signIN = new WebDriverWait(driver, Duration.ofSeconds(30))
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='submit-login']")));
                signIN.click(); }
            catch (TimeoutException exception) {
                driver.get("https://mystore-testlab.coderslab.pl/index.php");
                clickSignIn();
                logIN();
            }


        }

        @When("Click Addresses")
        public void clickAddresses() {
            WebElement addressFooter = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Addresses')]")));
            addressFooter.click();
        }

        @When("No address message is displayed click Create new address")
        public void createAddress(){
            WebElement createAddress = driver.findElement(By.xpath("//span[contains(text(),'Create new address')]"));
            createAddress.click();
        }

        @Then("^Fill up New address form with (.*), (.*), (.*), (.*), (.*)")
        public void newAddress(String alias, String address, String city, String postal, String phone){
            WebElement element = driver.findElement(By.name("alias"));
            element.sendKeys(alias);
            WebElement element2 = driver.findElement(By.name("address1"));
            element2.sendKeys(address);
            WebElement element3 = driver.findElement(By.name("postcode"));
            element3.sendKeys(postal);
            WebElement element4 = driver.findElement(By.xpath("//select[@name='id_country']/*[@value='17']"));
            element4.click();
            WebElement element5 = driver.findElement(By.name("city"));
            element5.sendKeys(city);
            WebElement element6 = driver.findElement(By.name("phone"));
            element6.sendKeys(phone);
            WebElement element7 = driver.findElement(By.xpath("//section[@id='content']/div/div/form/footer/button"));
            element7.click();
        }

        @Then("^Verify address results with (.*), (.*), (.*), (.*), (.*)")
            public void verifyData(String alias, String address, String city, String postal, String phone){
            WebElement delButton = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//body/section[@id='wrapper']/div[1]/section[1]/div[1]/div[1]/section[2]/div[1]/div[1]/form[1]/div[1]/article[1]/footer[1]/a[2]")));

            List<WebElement> searchResults = driver.findElements(By.xpath(" //header/label[1]"));
            String actualText = searchResults.get(0).getText();
            Assertions.assertThat(actualText).isEqualTo(alias+"\n"+"Maciej Rogalski\n"+address+"\n"+city+"\n"+postal+"\n"+"United Kingdom\n"+phone);
        }

        @Then("^Go to clothes in store and buy Hummingbird Printed Sweater (.*) and (.*)")
            public void buyPrintedSweater(String Size, String Quantity){
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='category-3']/a")));
            element.click();

            WebElement element2 = driver.findElement(By.xpath("//*[@id='js-product-list']/div[1]/article[2]/div/div[1]/h2/a"));
            element2.click();
            WebElement element3 = driver.findElement(By.xpath ("//select[@id='group_1']/option[@title='"+Size+"']")); //wprowadzenie ziennej do xpatha
            element3.click();
            WebElement element4 = driver.findElement(By.name("qty"));
            element4.clear();
            element4.sendKeys(Quantity);
            WebElement element5 = driver.findElement(By.xpath("//*[@class='add']/button"));
            element5.click();

            WebElement element6 = new WebDriverWait(driver, Duration.ofSeconds(30))
                  .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='cart-content-btn']/a")));
            element6.click();
            WebElement element7 = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='text-sm-center']/a")));
            element7.click();
        }

        @Then ("Choose Shipping Method PrestaShop")
        public void chooseShippingMethod(){

            WebElement element2 = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.id("checkout-delivery-step")));
            element2.click();

//          WebElement element2 = new WebDriverWait(driver, Duration.ofSeconds(30))
//                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//body/section[@id='wrapper']/div[1]/section[1]/div[1]/div[1]/section[3]/h1[1]")));
//          element2.click();

            WebElement element = driver.findElement(By.id("delivery_option_1"));
            if (element.isSelected()){}   //sprawdzenie czy już jest zaznaczone
            else element.click();
        }

        @Then ("Choose Payment Pay by Check")
        public void choosePayment() throws IOException {
            WebElement element2 = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.id("checkout-payment-step")));
            element2.click();

            WebElement element = driver.findElement(By.id("payment-option-1"));
            if (element.isSelected()){}  //sprawdzenie czy już jest zaznaczone
            else element.click();

            WebElement element3 = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
            element3.click();

            WebElement element4 = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='payment-confirmation']/div[1]/button")));
            element4.click();

            // pobranie zmiennej orderReference z listy orderDetails
            List<WebElement> orderDetails = driver.findElements(By.xpath("//*[@id='order-details']/ul/li"));
            orderReference = orderDetails.get(0).getText();
            orderReference = orderReference.substring(17,26);  //wyodrębnienie numeru zamówienia

            // pobranie ceny do zmiennej priceTotal
            List <WebElement> price = driver.findElements(By.xpath("//*[@id='order-items']/div/table/tbody/tr[3]/td[2]"));
            priceTotal = price.get(0).getText();

            // strzał screenshota
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("f:/screenshot.png"));
         }

         @When ("Order is confirmed check order status")
         public void checkStatus(){
            WebElement ikonaKlienta = driver.findElement(By.xpath("//a[@title='View my customer account']"));
            ikonaKlienta.click();
            WebElement historyLink = driver.findElement(By.id("history-link"));
            historyLink.click();

            //pobranie stringa ze statusem zamówienia
             WebElement orderHistory = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr"));
             String fullString = orderHistory.getText(); //pełny string
             String orderString = (orderHistory.getText()).substring(0,9);  //string z kodem zamówienia. Pierwsze 10 znaków
             String statusString = (orderHistory.getText()).substring(21,fullString.length()); //string ze statusem od 21 znaku do końca

             //ASSERTIONS:
             Assertions.assertThat(orderReference).isEqualTo(orderString); //porównanie kodów zamówienia
            //porównanie ceny i statusu zamówienia
             Assertions.assertThat(statusString).isEqualTo(priceTotal+" Payments by check Awaiting check payment - Details Reorder"); // porównanie statusu i ceny
         }


          @And("close browser")
            public void closeBrowser() {
            driver.quit();
        }

    }

