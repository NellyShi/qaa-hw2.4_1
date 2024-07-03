package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


public class AccountRefillTest {
    @Test
    void shouldDepositFirstCard() {
       open("http://localhost:9999");
       var loginPage = new LoginPage();
       var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
       var dashboard = verificationPage.validVerify(DataHelper.getVerificationCode());

        var operation = DataHelper.getOperations()[0];

        int sourceCardBalance = dashboard.getCardBalance(DataHelper.getLastCardNumbers(operation.getSourceCard()));

        int targetCardBalance = dashboard.getCardBalance(DataHelper.getLastCardNumbers(operation.getTargetCard()));

        var depositChangingPage = dashboard.depositCard(operation.getTargetCard());

        depositChangingPage.transfer(operation.getSourceCard(), operation.getAmount());

        dashboard.checkPage();

        dashboard.checkDepositCard(DataHelper.getLastCardNumbers(operation.getTargetCard()),
                    targetCardBalance + operation.getAmount());

        dashboard.checkDepositCard(DataHelper.getLastCardNumbers(operation.getSourceCard()),
                    sourceCardBalance - operation.getAmount());
    }

    @Test
    void shouldDepositSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var dashboard = verificationPage.validVerify(DataHelper.getVerificationCode());

        var operation = DataHelper.getOperations()[1];

        int sourceCardBalance = dashboard.getCardBalance(DataHelper.getLastCardNumbers(operation.getSourceCard()));

        int targetCardBalance = dashboard.getCardBalance(DataHelper.getLastCardNumbers(operation.getTargetCard()));

        var depositChangingPage = dashboard.depositCard(operation.getTargetCard());

        depositChangingPage.transfer(operation.getSourceCard(), operation.getAmount());

        dashboard.checkPage();

        dashboard.checkDepositCard(DataHelper.getLastCardNumbers(operation.getTargetCard()),
                targetCardBalance + operation.getAmount());

        dashboard.checkDepositCard(DataHelper.getLastCardNumbers(operation.getSourceCard()),
                sourceCardBalance - operation.getAmount());
    }
}


