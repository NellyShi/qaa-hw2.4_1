package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DepositChangingPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DepositChangingPage() {
        $("h1").shouldBe(Condition.visible).shouldHave(Condition.exactText("Пополнение карты"));
    }

    public void transfer(String sourceCard, int amount) {
        amountField.setValue(Integer.toString(amount));
        fromField.setValue(sourceCard);
        transferButton.click();
    }
}
