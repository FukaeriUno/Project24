import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    String planningDate = generateDate(4);

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldSendCardDeliveryForm() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(String.valueOf(planningDate));
        $("[data-test-id=name] input").setValue("Галина Андреева");
        $("[data-test-id=phone] input").setValue("+79256341589");
        $(By.className("checkbox__box")).click();
        $(".button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }
}
