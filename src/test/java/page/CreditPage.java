package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DBHelper;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class CreditPage {

    private final MainPage mainPage = new MainPage();


    private final SelenideElement continueButton = $(".form-field button");
    private final SelenideElement titleCard = $x("//*[@id='root']/div/h3");


    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardMonth = $("[placeholder='08']");
    private final SelenideElement cardYear = $("[placeholder='22']");
    private final SelenideElement cardHolder = $x("//*[.='Владелец'] //input");
    private final SelenideElement cardCVC = $("[placeholder='999']");


    private final SelenideElement notificationTitleAccept = $(".notification_status_ok");
    private final SelenideElement notificationContentAccept = $(".notification_status_ok");

    private final SelenideElement notificationTitleDenial = $(".notification_status_error");
    private final SelenideElement notificationContentDenial = $(".notification_status_error");


    private final SelenideElement numberFieldError = $x("//span[contains(text(),'Номер карты')]").parent().$(".input__sub");
    private final SelenideElement monthFieldError = $x("//span[contains(text(),'Месяц')]").parent().$(".input__sub");
    private final SelenideElement yearFieldError = $x("//span[contains(text(),'Год')]").parent().$(".input__sub");
    private final SelenideElement holderFieldError = $x("//span[contains(text(),'Владелец')]").parent().$(".input__sub");
    private final SelenideElement cvcFieldError = $x("//span[contains(text(),'CVC/CVV')]").parent().$(".input__sub");


    public void completeCreditFrom(String number, String month, String year, String holder, String cvc) {
        mainPage.clickCreditButton();
        titleCard.shouldBe(Condition.text("Кредит по данным карты"));
        cardNumber.setValue(number);
        cardMonth.setValue(month);
        cardYear.setValue(year);
        cardHolder.setValue(holder);
        cardCVC.setValue(cvc);
    }

    public void continueClick() {
        continueButton.click();
    }

    public void acceptAssertion() {
        notificationTitleAccept.shouldBe(Condition.text("Успешно"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        notificationContentAccept.shouldBe(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void denialAssertion() {
        notificationTitleDenial.shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        notificationContentDenial.shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void numberFieldFormatError() {
        numberFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void monthFieldFormatError() {
        monthFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void monthFieldPeriodError() {
        monthFieldError.shouldBe(Condition.text("Неверно указан срок действия карты"), Condition.visible);
    }

    public void yearFieldFormatError() {
        yearFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void yearFieldMinusPeriodError() {
        yearFieldError.shouldBe(Condition.text("Истёк срок действия карты"), Condition.visible);
    }

    public void yearFieldPlusPeriodError() {
        yearFieldError.shouldBe(Condition.text("Неверно указан срок действия карты"), Condition.visible);
    }

    public void holderFieldEmptyError() {
        holderFieldError.shouldBe(Condition.text("Поле обязательно для заполнения"), Condition.visible);
    }

    public void holderFieldFormatError() {
        holderFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void cvcFieldFormatError() {
        cvcFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void creditApprovedStatusAssertion() {
        String statusExpected = "APPROVED";
        String statusActual = DBHelper.getCreditStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditDeclinedStatusAssertion() {
        String statusExpected = "DECLINED";
        String statusActual = DBHelper.getCreditStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DBHelper.getCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void creditDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DBHelper.getCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DBHelper.getOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DBHelper.getOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }
}

