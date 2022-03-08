import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    public void shouldSendCardDeliveryForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").setValue("11.12.2023");
        $("[data-test-id=name] input").setValue("Галина Андреева");
        $("[data-test-id=phone] input").setValue("+79256341589");
        $(By.className("checkbox__box")).click();
        $(".button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
