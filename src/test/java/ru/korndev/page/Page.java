package ru.korndev.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Page {
    List<SelenideElement> input = $$(".input__control");
    SelenideElement cardNumber = input.get(0);
    SelenideElement month = input.get(1);
    SelenideElement year = input.get(2);
    SelenideElement cardOwner = input.get(3);
    SelenideElement cvv = input.get(4);

    public void payCard() {
        open("http://localhost:8080");
        $$(".button__content").find(exactText("Купить")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Оплата по карте")).shouldBe(visible);
    }

    public void buyCredit() {
        open("http://localhost:8080");
        $$(".button__content").find(exactText("Купить в кредит")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Кредит по данным карты")).shouldBe(visible);
    }

    public void success() {
        $$(".notification__title").find(exactText("Успешно")).shouldHave(visible, Duration.ofSeconds(15));
    }

    public void error() {
        $$(".notification__title").find(exactText("Ошибка")).shouldHave(visible, Duration.ofSeconds(15));
    }

    public void invalidFormat() {
        $$(".input__sub").find(exactText("Неверный формат")).shouldBe(visible);
    }

    public void invalidDate() {
        $$(".input__sub").find(exactText("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void expiredCard() {
        $$(".input__sub").find(exactText("Истёк срок действия карты")).shouldBe(visible);
    }

    public void required() {
        $$(".input__sub").find(exactText("Поле обязательно для заполнения")).shouldBe(visible);
    }


    public void Card(String number, String cardMonth, String cardYear, String owner, String CVV) {
        cardNumber.setValue(number);
        month.setValue(cardMonth);
        year.setValue(cardYear);
        cardOwner.setValue(owner);
        cvv.setValue(CVV);
    }

    public void buttonContinue() {
        $$(".button__content").find(exactText("Продолжить")).click();
    }

}