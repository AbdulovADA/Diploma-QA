package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class MainPage {
    private final SelenideElement payButton = $x("//*[@id='root']/div/button[1]");
    private final SelenideElement creditButton = $x("//*[@id='root']/div/button[2]");

    public void clickCreditButton() {
        creditButton.click();
    }

    public void clickPayButton() {
        payButton.click();
    }
}