package ru.netology.domain;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement firstCardButton = $$("[data-test-id=action-deposit]").first();
    private SelenideElement secondCardButton = $$("[data-test-id=action-deposit]").last();
    private SelenideElement updateButton = $$("[data-test-id=action-deposit]").last();
    private SelenideElement balance0001 = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private SelenideElement balance0002 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");

    public RechargingPage chooseFirstCardToRecharge() {
        firstCardButton.click();
        return new RechargingPage();
    }

    public RechargingPage chooseSecondCardToRecharge() {
        secondCardButton.click();
        return new RechargingPage();
    }

    public int checkBalanceOfFirstCard() {
        String selectedValue = balance0001.getText();
        String balanceOfFirstCard = selectedValue.substring(29, selectedValue.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }

    public int checkBalanceOfSecondCard() {
        String selectedValue = balance0002.getText();
        String balanceOfFirstCard = selectedValue.substring(29, selectedValue.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }
}
