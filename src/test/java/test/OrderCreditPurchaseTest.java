package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.CreditPage;

import static com.codeborne.selenide.Selenide.open;

public class OrderCreditPurchaseTest {

    private final CreditPage creditPage = new CreditPage();

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
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }


    @Test
    @DisplayName("(2) Positive - CreditCard - Buying Tour Failed")
    void shouldFailBuyByCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getDeclinedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::creditDeclinedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }


    @Test
    @DisplayName("(3) Negative - CreditCard - Buying Tour CardField Invalid")
    void shouldFailBuyByCreditCardInvalidCardField() {
        creditPage.completeCreditFrom(DataHelper.getRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }


    // Поле "Номер карты"

    @Test
    @DisplayName("(4) Positive - CreditCard - Valid CardField 16 symbols")
    void shouldSuccessfulBuyByValidCreditCardFieldSixteenSymbols() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(5) Negative - CreditCard - CardField 15 symbols")
    void shouldFailBuyByInvalidCreditCardFieldFifteenSymbols() {
        creditPage.completeCreditFrom(DataHelper.getFifteenRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::numberFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(6) Negative - CreditCard - CardField One Symbol")
    void shouldFailBuyByInvalidCreditCardFieldOneSymbol() {
        creditPage.completeCreditFrom(DataHelper.getOneRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::numberFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(7) Negative - CreditCard - Empty CardField")
    void shouldFailBuyByInvalidCreditCardFieldEmpty() {
        creditPage.completeCreditFrom(DataHelper.getEmptyNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::numberFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(8) Negative - CreditCard - CardField All Symbols - Zeroes")
    void shouldFailBuyByInvalidAllZeroesCreditCardField() {
        creditPage.completeCreditFrom(DataHelper.getZeroNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::numberFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    // Поле "Месяц"

    @Test
    @DisplayName("(9) Positive - CreditCard - MonthField - 01")
    void shouldSuccessfulBuyByValidFirstMonthMonthFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getFirstMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(10) Positive - CreditCard - MonthField - 02")
    void shouldSuccessfulBuyByValidSecondMonthMonthFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getSecondMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(11) Positive - CreditCard - MonthField - 11")
    void shouldSuccessfulBuyByValidElevenMonthMonthFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getElevenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(12) Positive - CreditCard - MonthField - 12")
    void shouldSuccessfulBuyByValidTwelveMonthMonthFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getTwelveMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(13) Negative - CreditCard - MonthField - Empty")
    void shouldFailBuyByInvalidEmptyMonthMonthFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getEmptyMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::monthFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(14) Negative - CreditCard - MonthField - Zero")
    void shouldFailBuyByInvalidZeroMonthMonthFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getZeroMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::monthFieldPeriodError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(15) Negative - CreditCard - MonthField - 13")
    void shouldFailBuyByInvalidThirteenMonthMonthFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getThirteenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::monthFieldPeriodError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(16) Negative - CreditCard - MonthField - One Symbol")
    void shouldFailBuyByInvalidOneSymbolMonthMonthFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getOneSymbolMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::monthFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    //Поле "Год"

    @Test
    @DisplayName("(17) Positive - CreditCard - YearField - Current Year")
    void shouldSuccessfulBuyByValidCurrentYearYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(18) Positive - CreditCard - YearField - Current Year + 1")
    void shouldSuccessfulBuyByValidCurrentYearPlusOneYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(19) Positive - CreditCard - YearField - Current Year + 2")
    void shouldSuccessfulBuyByValidCurrentYearPlusTwoYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(2), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(20) Positive - CreditCard - YearField - Current Year + 7")
    void shouldSuccessfulBuyByValidCurrentYearPlusSevenYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(7), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(21) Positive - CreditCard - YearField - Current Year + 8")
    void shouldSuccessfulBuyByValidCurrentYearPlusEightYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(8), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(22) Negative - CreditCard - YearField - Current Year Empty")
    void shouldFailBuyByInvalidYearEmptyYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getEmptyYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::yearFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }


    @Test
    @DisplayName("(23) Negative - CreditCard - YearField - Current Year 1 Symbol")
    void shouldFailBuyByInvalidYearOneSymbolYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getOneSymbolYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::yearFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(24) Negative CreditCard - YearField - Current Year -1")
    void shouldFailBuyByValidCurrentYearMinusOneYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(-1), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::yearFieldMinusPeriodError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(25) PayCard - CreditCard - YearField - Current Year +9")
    void shouldFailBuyByValidCurrentYearPlusNineYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(9), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::yearFieldPlusPeriodError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(26) Negative - CreditCard - YearField - Zero")
    void shouldFailBuyByInvalidZeroYearFieldCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getZeroYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::yearFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    //Поле "Владелец"

    @Test
    @DisplayName("(27) Positive - CreditCard - HolderField - 3 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldThreeSymbolsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(28) Positive - CreditCard - HolderField - 4 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldFourSymbolsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("????"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(29) Positive - CreditCard - HolderField With SpaceBar")
    void shouldSuccessfulBuyByValidHolderFieldWithSpaceBarCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("??????? ???????"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(30) Positive - CreditCard - HolderField With Dash")
    void shouldSuccessfulBuyByValidHolderFieldWithDashCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???????-???????"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(31) Positive - CreditCard - HolderField - 19 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldNineteenSymbolsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???????????????????"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(32) Positive - CreditCard - HolderField - 20 Symbols")
    void shouldSuccessfulBuyByValidHolderFieldTwentySymbolsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("????????????????????"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(33) Negative - CreditCard - HolderField - 2 Symbols")
    void shouldFailBuyByInvalidHolderFieldTwoSymbolsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("??"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::holderFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(34) Negative - CreditCard - HolderField Empty")
    void shouldFailBuyByInvalidHolderFieldEmptyCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEmptyHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::holderFieldEmptyError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(35) Negative - CreditCard - HolderField - Digits")
    void shouldFailBuyByInvalidHolderFieldDigitsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getDigitsHolder("########"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::holderFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(36) Negative - CreditCard - HolderField SpecialCharacters")
    void shouldFailBuyByInvalidHolderFieldSpecialCharactersCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpecialCharactersHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::holderFieldEmptyError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(37) Negative - CreditCard - HolderField Spacebars")
    void shouldFailBuyByInvalidHolderFieldSpacebarsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpacesHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::holderFieldEmptyError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(38) Negative - CreditCard - HolderField Cyrillic")
    void shouldFailBuyByInvalidHolderFieldCyrillicPayCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getCyrillicHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::holderFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }


    @Test
    @DisplayName("(39) Negative - CreditCard - HolderField - 21 Symbols")
    void shouldFailBuyByInvalidHolderFieldTwentyOneSymbolsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("?????????????????????"), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::holderFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    //Поле "CVС"

    @Test
    @DisplayName("(40) Positive - CreditCard - CVCField - 3 Symbols")
    void shouldSuccessfulBuyByValidCVCFieldThreeSymbolsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                creditPage::creditApprovedStatusAssertion,
                creditPage::creditAcceptCountAssertion,
                creditPage::orderAcceptCountAssertion
        );
    }

    @Test
    @DisplayName("(41) Negative - CreditCard - CVCField - 1 Symbol")
    void shouldFailBuyByInvalidCVCFieldOneSymbolCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getOneSymbolCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::cvcFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(42) Negative - CreditCard - CVCField - 2 Symbols")
    void shouldFailBuyByInvalidCVCFieldTwoSymbolsCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getTwoSymbolsCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::cvcFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(43) Negative - CreditCard - CVCField Empty")
    void shouldFailBuyByInvalidCVCFieldEmptyCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getEmptyCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::cvcFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }

    @Test
    @DisplayName("(44) Negative - CreditCard - CVCField Zero")
    void shouldFailBuyByInvalidCVCFieldZeroPayCreditCard() {
        creditPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getZeroSymbolsCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                creditPage::cvcFieldFormatError,
                creditPage::creditDenialCountAssertion,
                creditPage::orderDenialCountAssertion
        );
    }
}