package ru.netology.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

public class ApplicationTest {
    @Test
    void shouldCheckTransactionIsOk() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        int balanceBefore = dashboardPage.checkBalanceOfFirstCard();
        val rechargingPage = dashboardPage.chooseFirstCardToRecharge();
        val cardInfo = DataHelper.getSecondCardInformation();
        val dashboardPage1 = rechargingPage.rechargeCard(cardInfo);
        int balanceAfter = dashboardPage1.checkBalanceOfFirstCard();
        assertEquals(500, balanceAfter - balanceBefore);
    }

    @Test
    void shouldCheckTransactionIsOkForSecondCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        int balanceBefore = dashboardPage.checkBalanceOfSecondCard();
        val rechargingPage = dashboardPage.chooseSecondCardToRecharge();
        val cardInfo = DataHelper.getFirstCardInformation();
        val dashboardPage1 = rechargingPage.rechargeCard(cardInfo);
        int balanceAfter = dashboardPage1.checkBalanceOfSecondCard();
        assertEquals(500, balanceAfter - balanceBefore);
    }

    @Test
    void shouldCheckBalance() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        val balance = dashboardPage.checkBalanceOfFirstCard();
        System.out.println(balance);
    }
}
