package ru.netology.pageobject.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.pageobject.data.Data;

import static com.codeborne.selenide.Selenide.$;

public class RechargingPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public void rechargeCard(Data.CardInfo fromCardInfo) {
        String amountToAddForTest = "600";
        amountField.setValue(amountToAddForTest);
        fromField.setValue(fromCardInfo.getCardNumber());
        transferButton.click();
    }
}
