package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement depositFirstButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement depositSecondButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        $("[data-test-id=dashboard]").shouldBe(Condition.visible);
    }

    public int getCardBalance(String last4CardNumber) {
        var text = cards.findBy(Condition.text(last4CardNumber)).getText();
        return extractBalance(text);
    }

    public DepositChangingPage depositCard(String targetCard) {
        cards.find(text(targetCard.substring(15, 19))).$("button").click();
        return new DepositChangingPage();
    }

    public void checkPage() {
        $("h1").shouldHave(Condition.exactText("Ваши карты"));
    }

    public void checkDepositCard(String last4CardNumber, int balanceExpected)
    {
        for (int i = 0; i < cards.size(); i++) {
            val text = cards.get(i).text();
            if (text.contains(last4CardNumber)) {
                cards.get(i).shouldHave(text(Integer.toString(balanceExpected)));
                return;
            }
        }
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
