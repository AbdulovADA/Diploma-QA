package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.MainPage;
import page.OrderPage;

import static com.codeborne.selenide.Selenide.*;

public class OrderCreditPurchaseTest {

    private MainPage mainPage = new MainPage();
    private OrderPage orderPage = new OrderPage();



    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @AfterEach
    void clearDB() {
        DBHelper.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // Полные пути оплаты

    @Test
    @DisplayName("(1) Positive - CreditCard - Buying Tour Successful")
    void shouldSuccessfulBuyByCreditCard() {

        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }


    @Test
    @DisplayName("(2) Positive - CreditCard - Buying Tour Failed")
    void shouldFailBuyByCreditCard() {
        orderPage.completePayFrom(DataHelper.getDeclinedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.payDeclinedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }


    @Test
    @DisplayName("(3) Negative - CreditCard - Buying Tour CardField Invalid")
    void shouldFailBuyByCreditCardInvalidCardField() {
        orderPage.completePayFrom(DataHelper.getRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }


    // Поле "Номер карты"

    @Test
    @DisplayName("(4) Positive - CreditCard - Valid CardField 16 symbols")
    void shouldSuccessfulBuyByValidCreditCardFieldSixteenSymbols() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(5) Negative - CreditCard - CardField 15 symbols")
    void shouldFailBuyByInvalidCreditCardFieldFifteenSymbols() {
        orderPage.completePayFrom(DataHelper.getFifteenRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.numberFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(6) Negative - CreditCard - CardField One Symbol")
    void shouldFailBuyByInvalidCreditCardFieldOneSymbol() {
        orderPage.completePayFrom(DataHelper.getOneRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.numberFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(7) Negative - CreditCard - Empty CardField")
    void shouldFailBuyByInvalidCreditCardFieldEmpty() {
        orderPage.completePayFrom(DataHelper.getEmptyNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.numberFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(8) Negative - CreditCard - CardField All Symbols - Zeroes")
    void shouldFailBuyByInvalidAllZeroesCreditCardField() {
        orderPage.completePayFrom(DataHelper.getZeroNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.numberFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    // Поле "Месяц"

    @Test
    @DisplayName("(9) Positive - CreditCard - MonthField - 01")
    void shouldSuccessfulBuyByValidFirstMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getFirstMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(10) Positive - CreditCard - MonthField - 02")
    void shouldSuccessfulBuyByValidSecondMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getSecondMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(11) Positive - CreditCard - MonthField - 11")
    void shouldSuccessfulBuyByValidElevenMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getElevenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(12) Positive - CreditCard - MonthField - 12")
    void shouldSuccessfulBuyByValidTwelveMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getTwelveMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(13) Negative - CreditCard - MonthField - Empty")
    void shouldFailBuyByInvalidEmptyMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getEmptyMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.monthFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(14) Negative - CreditCard - MonthField - Zero")
    void shouldFailBuyByInvalidZeroMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getZeroMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.monthFieldPeriodError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(15) Negative - CreditCard - MonthField - 13")
    void shouldFailBuyByInvalidThirteenMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getThirteenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.monthFieldPeriodError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(16) Negative - CreditCard - MonthField - One Symbol")
    void shouldFailBuyByInvalidOneSymbolMonthMonthFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getOneSymbolMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.monthFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    //Поле "Год"

    @Test
    @DisplayName("(17) Positive - CreditCard - YearField - Current Year")
    void shouldSuccessfulBuyByValidCurrentYearYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(18) Positive - CreditCard - YearField - Current Year + 1")
    void shouldSuccessfulBuyByValidCurrentYearPlusOneYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(19) Positive - CreditCard - YearField - Current Year + 2")
    void shouldSuccessfulBuyByValidCurrentYearPlusTwoYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(2), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(20) Positive - CreditCard - YearField - Current Year + 7")
    void shouldSuccessfulBuyByValidCurrentYearPlusSevenYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(7), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(21) Positive - CreditCard - YearField - Current Year + 8")
    void shouldSuccessfulBuyByValidCurrentYearPlusEightYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(8), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(22) Negative - CreditCard - YearField - Current Year Empty")
    void shouldFailBuyByInvalidYearEmptyYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getEmptyYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.yearFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }


    @Test
    @DisplayName("(23) Negative - CreditCard - YearField - Current Year 1 Symbol")
    void shouldFailBuyByInvalidYearOneSymbolYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getOneSymbolYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.yearFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(24) Negative CreditCard - YearField - Current Year -1")
    void shouldFailBuyByValidCurrentYearMinusOneYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(-1), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.yearFieldMinusPeriodError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(25) PayCard - CreditCard - YearField - Current Year +9")
    void shouldFailBuyByValidCurrentYearPlusNineYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(9), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.yearFieldPlusPeriodError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(26) Negative - CreditCard - YearField - Zero")
    void shouldFailBuyByInvalidZeroYearFieldCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getZeroYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.yearFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    //Поле "Владелец"

    @Test
    @DisplayName("(27) Positive - CreditCard - HolderField - 3 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldThreeSymbolsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(28) Positive - CreditCard - HolderField - 4 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldFourSymbolsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(29) Positive - CreditCard - HolderField With SpaceBar")
    void shouldSuccessfulBuyByValidHolderFieldWithSpaceBarCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("??????? ???????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(30) Positive - CreditCard - HolderField With Dash")
    void shouldSuccessfulBuyByValidHolderFieldWithDashCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???????-???????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(31) Positive - CreditCard - HolderField - 19 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldNineteenSymbolsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???????????????????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(32) Positive - CreditCard - HolderField - 20 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldTwentySymbolsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("????????????????????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(33) Negative - CreditCard - HolderField - 2 Symbols")
    void shouldFailBuyByInvalidHolderFieldTwoSymbolsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("??"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.holderFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(34) Negative - CreditCard - HolderField Empty")
    void shouldFailBuyByInvalidHolderFieldEmptyCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEmptyHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.holderFieldEmptyError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(35) Negative - CreditCard - HolderField - Digits")
    void shouldFailBuyByInvalidHolderFieldDigitsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getDigitsHolder("########"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.holderFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(36) Negative - CreditCard - HolderField SpecialCharacters")
    void shouldFailBuyByInvalidHolderFieldSpecialCharactersCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpecialCharactersHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.holderFieldEmptyError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(37) Negative - CreditCard - HolderField Spacebars")
    void shouldFailBuyByInvalidHolderFieldSpacebarsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpacesHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.holderFieldEmptyError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(38) Negative - CreditCard - HolderField Cyrillic")
    void shouldFailBuyByInvalidHolderFieldCyrillicPayCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getCyrillicHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.holderFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }


    @Test
    @DisplayName("(39) Negative - CreditCard - HolderField - 21 Symbols")
    void shouldFailBuyByInvalidHolderFieldTwentyOneSymbolsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("?????????????????????"), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.holderFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    //Поле "CVС"

    @Test
    @DisplayName("(40) Positive - CreditCard - CVCField - 3 Symbols")
    void shouldSuccessfulBuyByValidCVCFieldThreeSymbolsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.acceptAssertion(),
                () -> orderPage.payApprovedStatusAssertion(),
                () -> orderPage.payAcceptCountAssertion(),
                () -> orderPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("(41) Negative - CreditCard - CVCField - 1 Symbol")
    void shouldFailBuyByInvalidCVCFieldOneSymbolCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getOneSymbolCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.CVCFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(42) Negative - CreditCard - CVCField - 2 Symbols")
    void shouldFailBuyByInvalidCVCFieldTwoSymbolsCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getTwoSymbolsCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.CVCFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(43) Negative - CreditCard - CVCField Empty")
    void shouldFailBuyByInvalidCVCFieldEmptyCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getEmptyCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.CVCFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("(44) Negative - CreditCard - CVCField Zero")
    void shouldFailBuyByInvalidCVCFieldZeroPayCreditCard() {
        orderPage.completePayFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getZeroSymbolsCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                () -> orderPage.denialAssertion(),
                () -> orderPage.CVCFieldFormatError(),
                () -> orderPage.payDenialCountAssertion(),
                () -> orderPage.orderDenialCountAssertion()
        );
    }
}