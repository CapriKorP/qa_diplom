package ru.korndev.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.korndev.data.DataService;
import ru.korndev.data.SQLService;
import ru.korndev.page.Page;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    private Page page;

    @BeforeEach
    void setUpPage() {
        page = new Page();
    }

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }
    static void sqlClean() throws SQLException {
        SQLService.clear();
    }

    @Test
    @DisplayName("Покупка активной картой")
    public void successfulPayApprovedCardPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.thisMonth(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.success();
    }


    @Test
    @DisplayName("Покупка  с заблокированной картой")
    public void unsuccessfulPayDeclinedCardPay() {
        page.payCard();
        page.Card(DataService.declinedCardNumber(), DataService.thisMonth(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.error();
    }


    @Test
    @DisplayName("Покупка тура со случайной карты, кроме 4444 4444 4444 4441 и 4444 4444 4444 4442")
    public void randomCardPay() {
        page.payCard();
        page.Card(DataService.randomCardNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.error();
    }


    @Test
    @DisplayName("Заполнение поле \"Номер карты\" одной цифрой")
    public void singleNumberPay() {
        page.payCard();
        page.Card(DataService.randomDigit(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Заполнение поле \"Номер карты\" пятнадцатью цифрами")
    public void fifteenNumberPay() {
        page.payCard();
        page.Card(DataService.fifteenCardNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Заполнение поле \"Номер карты\" семнадцатью цифрами")
    public void seventeenNumberPay() {
        page.payCard();
        page.Card(DataService.seventeenCardNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.error();
    }


    @Test
    @DisplayName("Заполнение поля \"Номер карты\" латиницей. ")
    public void lettersNumberPay() {
        page.payCard();
        page.Card(DataService.lettersNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }




    @Test
    @DisplayName("Заполнение поля \"Номер карты\" специальными символами.")
    public void symbolsNumberPay() {
        page.payCard();
        page.Card(DataService.getSymbolsNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Пустой номер карты")
    public void emptyNumberPay() {
        page.payCard();
        page.Card(DataService.emptySymbol(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }

    @Test
    @DisplayName("Поле \"Месяц\" заполнено значением предыдущего месяца этого года")
    public void lastMonthPay() {
        page.buyCredit();
        page.Card(DataService.approvedCardNumber(), DataService.lastMonth(), DataService.thisYear(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidDate();
    }
    @Test
    @DisplayName("Поле \"Месяц\" заполнено \"00\"")
    public void zeroMonthPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.zeroMonthorYear(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Поле \"Месяц\" заполнено \"13\"")
    public void thirteenMonthPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.thirteenMonth(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidDate();
    }


    @Test
    @DisplayName("Поле \"Месяц\" заполнено 2-я символоми латиницы")
    public void lettersMonthPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.twoLetters(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Поле \"Месяц\" заполнено одной цифрой")
    public void oneNumberMonthPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.randomDigit(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }

    @Test
    @DisplayName("Поле \"Месяц\" пустое")
    public void emptyMonthPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.emptySymbol(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Поле \"Год\" заполнено \"00\"")
    public void shouldErrorZeroYearPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.zeroMonthorYear(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.expiredCard();
    }


    @Test
    @DisplayName("Указан прошлый год")
    public void lastYearPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.pastYear(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.expiredCard();
    }

    @Test
    @DisplayName("Указан срок действия больше 5 лет")
    public void moreSixYearPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.moreFiveYear(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidDate();
    }


    @Test
    @DisplayName("Поле \"Год\" заполнено буквами")
    public void lettersYearPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.twoLetters(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Один цифра в поле \"Год\"")
    public void oneDigitYearPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.randomDigit(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Пустое поле  \"Год\"")
    public void emptyYearPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.emptySymbol(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Введен сециальный символ в поле \"Год\"")
    public void symbolsYearPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.symbolYear(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Заполнено только имя в поле \"Владелец карты\"")
    public void onlyNamePay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.onlyNameCardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Заполнено поле \"Владелец карты\" тремя словами")
    public void nameNameSurnamePay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.threeWordCardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Заполнено поле \"Владелец карты\" некорректными данными")
    public void unsupportedDataInCardHolderPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.negativeCardHolder(), DataService.cvv());
        page.buttonContinue();
        page.error();
    }


    @Test
    @DisplayName("Заполнено поле \"Владелец карты\" цифрами")
    public void digitCardHolderPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.numbersCardHolder(), DataService.cvv());
        page.buttonContinue();
        page.error();
    }


    @Test
    @DisplayName("Заполнено поле \"Владелец карты\" специальными символами")
    public void symbolsCardHolderPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.symbolCardHolder(), DataService.cvv());
        page.buttonContinue();
        page.error();
    }


    @Test
    @DisplayName("Пустое поле \"Владелец карты\"")
    public void emptyCardHolderPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.emptySymbol(), DataService.cvv());
        page.buttonContinue();
        page.required();
    }


    @Test
    @DisplayName("Превышен допустимый лимит символов в поле \"Владелец карты\"")
    public void overLimitCardholderPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.overLimitCardHolder(), DataService.cvv());
        page.buttonContinue();
        page.invalidFormat();
    }



    @Test
    @DisplayName("Один или два символа в поле \"CVV\"")
    public void oneOrTwoSymbolsCvvPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.oneOrTwoSymbolsCVV());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Буквы в поле \"CVV\"")
    public void letterCvvPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.lettersOnCVV());
        page.buttonContinue();
        page.invalidFormat();
    }


    @Test
    @DisplayName("Спецсимволы в поле \"CVV\"")
    public void symbolsCvvPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.symbolCVV());
        page.buttonContinue();
        page.invalidFormat();
    }
    
    @Test
    @DisplayName("Пусто в поле \"CVV\"")
    public void emptyCvvPay() {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.month(), DataService.year(), DataService.cardHolder(), DataService.emptySymbol());
        page.buttonContinue();
        page.required();
    }
    @Test
    @DisplayName("Покупка с активной карты, CVV - 000")
    void successfulCreditApprovedCardPayWith000CVV() {
        page.buyCredit();
        page.Card(DataService.approvedCardNumber(), DataService.thisMonth(), DataService.year(), DataService.cardHolder(), DataService.zeroCvv());
        page.buttonContinue();
        page.success();
    }
    @Test
    @DisplayName("Покупка с активной карты, регистрация записи в БД")
    void successfulPayApprovedCardPayWithDBCheck() throws SQLException, InterruptedException {
        page.payCard();
        page.Card(DataService.approvedCardNumber(), DataService.thisMonth(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        Thread.sleep(5000);
        assertEquals("APPROVED", SQLService.checkPaymentStatus());
    }


    @Test
    @DisplayName("Покупка с заблокированной карты, регистрация записи в БД")
    void unsuccessfulPayDeclinedCardPayWithDBCheck() throws SQLException, InterruptedException {
        page.payCard();
        page.Card(DataService.declinedCardNumber(), DataService.thisMonth(), DataService.year(), DataService.cardHolder(), DataService.cvv());
        page.buttonContinue();
        Thread.sleep(5000);
        assertEquals("DECLINED", SQLService.checkPaymentStatus());
    }
}