package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

        public String meetingDay(int day) {
            return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

    @BeforeAll
    static void setUpAllAllure(){
        SelenideLogger.addListener("allure",new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    void setUpAll() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

        @Test
        void shouldBookCardWithDeliveryHappyPath() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(14)).shouldHave(Condition.text("Встреча успешно забронирована на " + meetingDay(3)));
        }

        @Test
        void shouldBookCardWithDeliveryDoubleNameHappyPath() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон-Боб");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(14)).shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + meetingDay(3)));

        }

        @Test
        void shouldBookCardWithDeliveryPhoneHappyPath() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон-Боб");
            form.$("[data-test-id='phone'] input").setValue("+89271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(14)).shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + meetingDay(3)));

        }

        @Test
        void shouldBookCardNoCityDeliveryV1() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Тольятти");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='city'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Доставка в выбранный город недоступна"));
        }

        @Test
        void shouldBookCardNoCityDeliveryV2() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Samara");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='city'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Доставка в выбранный город недоступна"));
        }

        @Test
        void shouldBookCardNoCityDeliveryV3() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("1234");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='city'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Доставка в выбранный город недоступна"));
        }

        @Test
        void shouldBookCardNoCityDeliveryV4() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("!!$");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='city'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Доставка в выбранный город недоступна"));
        }

        @Test
        void shouldBookCardDeliveryWithoutCity() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='city'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Поле обязательно для заполнения"));
        }

        @Test
        void shouldBookCardNoDateDeliveryV1() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(2));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='date'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Заказ на выбранную дату невозможен"));
        }

        @Test
        void shouldBookCardNoDateDeliveryV2() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(1));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='date'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Заказ на выбранную дату невозможен"));
        }

        @Test
        void shouldBookCardNoDateDeliveryV3() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(0));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='date'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Заказ на выбранную дату невозможен"));
        }

        @Test
        void shouldBookCardWithoutDateDelivery() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='date'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Неверно введена дата"));
        }

        @Test
        void shouldBookCardNoNameV1() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Snow Jon");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='name'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
        void shouldBookCardNoNameV2() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон!!#");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='name'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
        void shouldBookCardNoNameV3() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("1234");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='name'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
        void shouldBookCardWithoutName() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='name'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Поле обязательно для заполнения"));
        }

        @Test
        void shouldBookCardNoPhoneV1() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='phone'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void shouldBookCardNoPhoneV2() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+792711720218");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='phone'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void shouldBookCardNoPhoneV3() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+7927117202");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='phone'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void shouldBookCardNoPhoneV4() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("-79271172021");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='phone'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void shouldBookCardNoPhoneV5() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("Сноу Джон");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='phone'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void shouldBookCardWithoutPhone() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='agreement']").click();
            form.$(".button__content").click();
            form.$("[data-test-id='phone'] .input__sub").shouldBe(Condition.visible).shouldHave(Condition.text("Поле обязательно для заполнения"));
        }

        @Test
        void shouldBookCardWithoutAgreement() {
            SelenideElement form = $("[action='/']");
            form.$("[data-test-id='city'] input").setValue("Самара");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
            form.$("[data-test-id='date'] input").setValue(meetingDay(3));
            form.$("[data-test-id='name'] input").setValue("Сноу Джон");
            form.$("[data-test-id='phone'] input").setValue("+79271172021");
            form.$(".button__content").click();
            $("[data-test-id='agreement'].input_invalid").shouldBe(Condition.visible).shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
        }
    @Test
    void shouldBookCardWithDropdown() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Ка");
        $x("//span[text()='Казань']//parent::div//parent::div[@tabindex='0'] //span[text()='Казань']").click();
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(meetingDay(3));
        form.$("[data-test-id='name'] input").setValue("Сноу Джон");
        form.$("[data-test-id='phone'] input").setValue("+79271172021");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(14)).shouldHave(Condition.text("Встреча успешно забронирована на " + meetingDay(3)));
    }
    @Test
    void shouldBookCardWithDropdownV2() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Вл");
        $x("//span[text()='Владимир']//parent::div//parent::div[@tabindex='0'] //span[text()='Владимир']").click();
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(meetingDay(3));
        form.$("[data-test-id='name'] input").setValue("Сноу Джон");
        form.$("[data-test-id='phone'] input").setValue("+79271172021");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(14)).shouldHave(Condition.text("Встреча успешно забронирована на " + meetingDay(3)));
    }
}
