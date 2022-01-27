
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class PrivatPaymentTest {

    @Test
    public void testPrivatPayment() {
        String payerCard = "4552 3314 4813 8217";
        String reciverCard = "4004 1591 1544 9003";
        String paymentAmount = "123";
        WebDriverManager.chromedriver().setup();

        Selenide.open("https://next.privat24.ua/money-transfer/card");

        Selenide.$(By.xpath("//input[@data-qa-node=\"numberdebitSource\"]")).sendKeys(payerCard);
        Selenide.$(By.xpath("//input[@data-qa-node=\"expiredebitSource\"]")).sendKeys("05/24");
        Selenide.$(By.xpath("//input[@data-qa-node=\"cvvdebitSource\"]")).sendKeys("926");

        Selenide.$(By.xpath("//input[@data-qa-node=\"numberreceiver\"]")).sendKeys(reciverCard);

        Selenide.$(By.xpath("//input[@data-qa-node=\"amount\"]")).sendKeys(paymentAmount);

        Selenide.$(By.xpath("//button[@data-qa-node=\"currency\"]")).click();
        Selenide.$(By.xpath("//button[@data-qa-value=\"USD\"]")).click();

        Selenide.$(By.xpath("//button[@type=\"submit\"]")).click();

        Selenide.$(By.xpath("//span[@data-qa-node=\"payer-card\"]"))
                .shouldHave(Condition.text(payerCard));
        Selenide.$(By.xpath("//span[@data-qa-node=\"receiver-card\"]"))
                .shouldHave(Condition.text(reciverCard));
        Selenide.$(By.xpath("//div[@data-qa-node=\"payer-amount\"]"))
                .shouldHave(Condition.text(paymentAmount));
        Selenide.$(By.xpath("//div[@data-qa-node=\"receiver-amount\"]"))
                .shouldHave(Condition.text(paymentAmount));
    }
}
