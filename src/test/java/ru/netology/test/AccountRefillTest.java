package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class AccountRefillTest {

    @Test
    void shouldAccountRefillFirstCardBySecond() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var dashboard = verificationPage.validVerify(DataHelper.getVerificationCode());
        int balanceBefore = dashboard.getFirstCardBalance();
        var depositChangingPage = dashboard.depositFistBySecondCard();
        int cash = 100;
        depositChangingPage.transfer(cash);
        dashboard.checkAfterDepositFirstCard(balanceBefore + cash);
    }

//    @Test
//    void shouldAccountRefillSecondCardByFirst()
//    {
//        open("http://localhost:9999");
//        var loginPage = new LoginPage();
//        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
//        var dashboard = verificationPage.validVerify(DataHelper.getVerificationCode());
//        int balanceBefore = dashboard.getSecondCardBalance();
//        var depositChangingPage = dashboard.depositSecondByFirstCard();
//        int cash = 100;
//        depositChangingPage.transfer(cash);
//        dashboard.checkAfterDepositSecondCard(balanceBefore + cash);
//    }
}
