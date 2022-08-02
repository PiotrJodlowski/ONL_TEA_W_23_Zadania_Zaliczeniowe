package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MystoreTestSteps {

    private WebDriver driver;  //WAZNE!!! Musi być ustawiona zmienna poza metodami, żeby przekazywały sobie wartość

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
            WebElement element = driver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a/span"));
            element.click();
    }
        //OPCJA 1 Z NOWYM KLIENTEM. OPCJA NIE AKTYWNA WYKOMENTOWANA
//      @When("Log in Page is open Click 'No account? Create one here")
//        public void clickCreateAccount() {
//          WebElement element = driver.findElement(By.xpath("//*[@id='content']/div/a"));
//          element.click();
//      }
//
//      @When("Create account page is open fill up and click save")
//        public void FillForm() {
//        WebElement imie = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.name("firstname")));
//        imie.clear();
//        imie.sendKeys("Maciej");
//
//        WebElement nazwisko = driver.findElement(By.name("lastname"));
//        nazwisko.clear();
//        nazwisko.sendKeys("Rogalski");
//
//        WebElement plec = driver.findElement(By.xpath("//*[@id=\"customer-form\"]/section/div[1]/div[1]/label[1]"));
//        plec.click();
//
//        WebElement DOB = driver.findElement(By.name("birthday"));
//        DOB.clear();
//        DOB.sendKeys("05/22/2010");
//
//        WebElement email = driver.findElement(By.name("email"));
//        email.clear();
//        email.sendKeys(GenerateEmail.withTimestamp());
//
//        WebElement haslo = driver.findElement(By.name("password"));
//        haslo.clear();
//        haslo.sendKeys("Pass123");
//
//        WebElement saveCustomer = driver.findElement(By.xpath("//*[@id='customer-form']/footer/button"));
//        saveCustomer.click();
//
//      }

        @When("Login Page is loaded login to store")   //OPCJA 2 Z JUŻ UTWORZONYM KLIENTEM. AKTYWNA
        public void logIN() {

            WebElement email = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.elementToBeClickable(By.name("email")));
            email.sendKeys("rogalski@gmail.com");

            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("Pass123");
            WebElement signIN = driver.findElement(By.xpath("//button[@id='submit-login']"));
            signIN.click();
        }

        @When("Click Addresses")
        public void clickAddresses() {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='footer_account_list']/li[4]/a")));
            element.click();
            //
        }

        @When("No address message is displayed click Create new address")
        public void createAddress(){
            // WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20))
            //        .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/section/div/div/section/section/div[2]/a")));
            WebElement element = driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div[2]/a"));
            element.click();
        }

        @Then("^Fill up New address form with (.*), (.*), (.*), (.*), (.*)")
        public void newAddress(String alias, String address, String city, String postal, String phone){
            WebElement element6 = driver.findElement(By.name("alias"));
            element6.sendKeys(alias);
            WebElement element = driver.findElement(By.name("address1"));
            element.sendKeys(address);
            WebElement element2 = driver.findElement(By.name("postcode"));
            element2.sendKeys(postal);
            WebElement element3 = driver.findElement(By.xpath("//select[@name='id_country']/*[@value='17']"));
            element3.click();
            WebElement element4 = driver.findElement(By.name("city"));
            element4.sendKeys(city);
            WebElement element7 = driver.findElement(By.name("phone"));
            element7.sendKeys(phone);
            WebElement element5 = driver.findElement(By.xpath("//section[@id='content']/div/div/form/footer/button"));
            element5.click();
        }

        @When("^Address is created verify results with (.*), (.*), (.*), (.*), (.*)")
            public void verifyData(String alias, String address, String city, String postal, String phone){

            List<WebElement> searchResults = driver.findElements(By.cssSelector("address"));
            String actualText = searchResults.get(0).getText();
            Assertions.assertThat(actualText).isEqualTo("Maciej Rogalski\n"+address+"\n"+city+"\n"+postal+"\n"+"United Kingdom\n"+phone);
        }


//
//      @And("close browser")
//        public void closeBrowser() {
//            driver.quit();
//        }

    }

