package ru.netology.pageobject.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.pageobject.data.DataHelper;
import ru.netology.pageobject.page.DashboardPage;
import ru.netology.pageobject.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ApplicationTest {
    int amountToAddForTest = 600;

    @Test
    void shouldCheckTransactionFromSecondToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val rechargingPage = dashboardPage.chooseFirstCardToRecharge();
        val cardInfo = DataHelper.getSecondCardInfo();
        rechargingPage.rechargeCard(cardInfo);
        int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargedCard(balanceOfFirstCardBefore, amountToAddForTest);
        int balanceAfterTransaction = DataHelper.checkBalanceWhereRecharge(balanceOfSecondCardBefore, amountToAddForTest);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransactionOnRecharged,balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction,balanceOfSecondCardAfter);
    }
    @Test
    void shouldCheckTransactionFromFirstToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val rechargingPage = dashboardPage.chooseSecondCardToRecharge();
        val cardInfo = DataHelper.getFirstCardInfo();
        rechargingPage.rechargeCard(cardInfo);
        int balanceAfterTransactionOnRecharged = DataHelper.checkBalanceOfRechargedCard(balanceOfSecondCardBefore, amountToAddForTest);
        int balanceAfterTransaction = DataHelper.checkBalanceWhereRecharge(balanceOfFirstCardBefore, amountToAddForTest);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        assertEquals(balanceAfterTransactionOnRecharged,balanceOfFirstCardAfter );
        assertEquals(balanceAfterTransaction,balanceOfSecondCardAfter );
    }
}
