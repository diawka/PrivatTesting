
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class PrivatPaymentTest {

    @Test
    public void testPrivatPayment() throws InterruptedException {
        String payerCard = "4552331448138217";
        String reciverCard = "4004159115449003";
        String paymentAmount = "123";
        String paymentCurrency = "USD";
        WebDriverManager.chromedriver().setup();

        Selenide.open("https://next.privat24.ua/money-transfer/card");

        Selenide.$(By.xpath("//input[@data-qa-node=\"numberdebitSource\"]")).sendKeys(payerCard);
        Selenide.$(By.xpath("//input[@data-qa-node=\"expiredebitSource\"]")).sendKeys("05/24");
        Selenide.$(By.xpath("//input[@data-qa-node=\"cvvdebitSource\"]")).sendKeys("926");

        Selenide.$(By.xpath("//input[@data-qa-node=\"numberreceiver\"]")).sendKeys(reciverCard);

        Selenide.$(By.xpath("//input[@data-qa-node=\"amount\"]")).sendKeys(paymentAmount);

        Selenide.$(By.xpath("//button[@data-qa-node=\"currency\"]")).click();
        Selenide.$(By.xpath("//button[@data-qa-value=\"USD\"]")).click();
        Thread.sleep(1000);

        Selenide.$(By.xpath("//button[@type=\"submit\"]")).click();
        String currentPayerCard = Selenide.$(By.xpath("//span[@data-qa-node=\"payer-card\"]"))
                .text().replaceAll("\\s", "");
        String currentReciverCard = Selenide.$(By.xpath("//span[@data-qa-node=\"receiver-card\"]"))
                .text().replaceAll("\\s", "");
        String currentPayerAmount = Selenide.$(By.xpath("//div[@data-qa-node=\"payer-amount\"]"))
                .text().replaceAll("\\s", "");
        String currentReciverAmount = Selenide.$(By.xpath("//div[@data-qa-node=\"receiver-amount\"]"))
                .text().replaceAll("\\s", "");

        Assert.assertTrue("Text not valid !", currentPayerCard.contains(payerCard));
        Assert.assertTrue("Text not valid !", currentReciverCard.contains(reciverCard));
        Assert.assertTrue("Text not valid !", currentPayerAmount.contains(paymentAmount + paymentCurrency));
        Assert.assertTrue("Text not valid !", currentReciverAmount.contains(paymentAmount + paymentCurrency));

    }
}
