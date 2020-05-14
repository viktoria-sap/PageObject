package ru.netology.pageobject.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.pageobject.data.Data;
import ru.netology.pageobject.page.DashboardPage;
import ru.netology.pageobject.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {
    int amountToAddForTest = 600;

    @Test
    void shouldCheckTransactionFromSecondToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = Data.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Data.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val rechargingPage = dashboardPage.chooseFirstCardToRecharge();
        val cardInfo = Data.getSecondCardInfo();
        rechargingPage.rechargeCard(cardInfo);
        int balanceAfterTransactionOnRecharged = Data.checkBalanceOfRechargedCard(balanceOfFirstCardBefore, amountToAddForTest);
        int balanceAfterTransaction = Data.checkBalanceWhereRecharge(balanceOfSecondCardBefore, amountToAddForTest);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransactionOnRecharged,balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction,balanceOfSecondCardAfter);
    }
    @Test
    void shouldCheckTransactionFromFirstToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = Data.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Data.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val rechargingPage = dashboardPage.chooseSecondCardToRecharge();
        val cardInfo = Data.getFirstCardInfo();
        rechargingPage.rechargeCard(cardInfo);
        int balanceAfterTransactionOnRecharged = Data.checkBalanceOfRechargedCard(balanceOfSecondCardBefore, amountToAddForTest);
        int balanceAfterTransaction = Data.checkBalanceWhereRecharge(balanceOfFirstCardBefore, amountToAddForTest);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        assertEquals(balanceAfterTransactionOnRecharged,balanceOfFirstCardAfter );
        assertEquals(balanceAfterTransaction,balanceOfSecondCardAfter );
    }
}
