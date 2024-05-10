package pages;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AuthenticationPage {

    final String studioLogin = "https://studio.barfin.network/ru/auth/login";


    private final SelenideElement loginLink = $("#barfin_buttonlink_studio");
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement profileButton = $("#barfin-menubutton-div-user div");
    private final SelenideElement passwordRegistrationInput = $("#password1");
    private final SelenideElement passwordConfirmRegistrationInput = $("#password2");
    private final SelenideElement registrationLink = $("#barfin-link-register");
    private final SelenideElement registrationButton = $("#barfin-button-register");
    private final SelenideElement allertSuccess = $(".alert-success");
    private final SelenideElement alertError = $(".alert-error");
    private final SelenideElement errorHint = $(".error-hint");
    private final SelenideElement forgotPasswordLink = $("#barfin-link-forgot-password");
    private final SelenideElement submitEmailButton = $("#barfin-button-submit-email");


    public AuthenticationPage openPage() {
        open("");
        return this;
    }

    public AuthenticationPage openStudio() {
        open(studioLogin);
        return this;
    }


    public AuthenticationPage openLoginPage() {
        loginLink.click();
        return this;
    }



    public AuthenticationPage setLoginAndPassword(String login, String password) {
        emailInput.shouldBe(visible);
        emailInput.setValue(login);
        passwordInput.setValue(password).pressEnter();
        return this;
    }



    public AuthenticationPage forgotPasswordClick() {
        forgotPasswordLink.shouldBe(visible);
        forgotPasswordLink.click();
        return this;
    }

    public AuthenticationPage setEmail(String email) {
        submitEmailButton.shouldBe(visible);
        emailInput.setValue(email).pressEnter();
        return this;
    }

    public AuthenticationPage checkSuccessAlert() {
        assertThat(allertSuccess.getText()).isEqualTo("An email has been sent to the user with the reset password link");
        return this;
    }


    public AuthenticationPage checkSuccessLogin(String email) {
        profileButton.shouldBe(visible);
        assertThat(profileButton.getText()).isEqualTo(email);
        return this;
    }


    public AuthenticationPage checkAlert(String alertText) {
        alertError.shouldBe(visible);
        assertThat(alertError.getText()).isEqualTo(alertText);
        return this;
    }

    public AuthenticationPage checkErrorHint() {
        errorHint.shouldBe(visible);
        return this;
    }


}
