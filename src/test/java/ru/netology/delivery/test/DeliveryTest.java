package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var dateFormat = "dd.MM.yyyy";
        //var validUser = DataGenerator.Registration.generateUserWithCity("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting, dateFormat);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting, dateFormat);
        var validCity = DataGenerator.generateCity("ru");
        var validPhone = DataGenerator.generatePhone("ru");
        var validName = DataGenerator.generateName("ru");

        //Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] [placeholder='Город']").setValue(validCity);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL+"a");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(firstMeetingDate);
        $("[data-test-id='name'] [type=text]").setValue(validName);
        $("[data-test-id='phone'] [type=tel]").setValue(validPhone);
        $x("//*[contains(@class,'checkbox__text')]").click();
        $x("//*[contains(text(),'Запланировать')]").click();
        //$("[data-test-id='notification'] [class='notification__content']").should(Condition.visible, Duration.ofSeconds(15));
        //$("[data-test-id='notification'] [class='notification__content']").shouldHave(Condition.exactText("Встреча успешно забронирована на " + firstMeetingDate));
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL+"a");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(secondMeetingDate);
        $x("//*[contains(text(),'Запланировать')]").click();
        $x("//span[contains(@class,button__text) and contains(text(),'Перепланировать')]").click();
        $("[data-test-id='success-notification'] [class='notification__content']").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] [class='notification__content']").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));

        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе
    }
  /*printTestData(String.valueOf(validUser), firstMeetingDate, secondMeetingDate);
    private void printTestData(String validUser, String firstMeetingDate, String secondMeetingDate) {
         System.out.println(validUser + "\n" + firstMeetingDate + "\n" + secondMeetingDate);
    }*/
}
