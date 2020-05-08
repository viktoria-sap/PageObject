package ru.netology.domain;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement verificationField = $("input[name=\"code\"]");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public DashboardPage verify(DataHelper.VerificationCode code){
        verificationField.setValue(code.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
