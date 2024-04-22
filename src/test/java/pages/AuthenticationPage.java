package pages;

import com.codeborne.selenide.SelenideElement;
import tests.TestBase;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AuthenticationPage extends TestBase {

    String studioLogin = "https://studio.barfin.network/ru/auth/login";
    String studioRegister = "https://studio.barfin.network/auth/register";

    private SelenideElement loginLink = $("#barfin_buttonlink_studio"),
            emailInput = $("#email"),
            passwordInput = $("#password"),
            profileButton = $("#barfin-menubutton-div-user div"),
            passworRegistrationdInput = $("#password1"),
            passworConfirmRegistrationdInput = $("#password2"),
            registrationLink = $("#barfin-link-register"),
            registrationButton = $("#barfin-button-register"),
            allertSuccess = $(".alert-success"),
            alertError = $(".alert-error"),
            errorHint = $(".error-hint"),
            forgotPasswordLink = $("#barfin-link-forgot-password"),
            submitEmailButton = $("#barfin-button-submit-email");


    public AuthenticationPage openPage() {
        open("");
        return this;
    }

    public AuthenticationPage openStudio() {
        open(studioLogin);
        return this;
    }

    public AuthenticationPage openStudioRegister() {
        open(studioRegister);
        return this;
    }

    public AuthenticationPage openLoginPage() {
        loginLink.click();
        return this;
    }

    public AuthenticationPage openRegistrationForm() {
        registrationLink.shouldBe(visible);
        registrationLink.click();
        return this;
    }


    public AuthenticationPage setLoginAndPassword(String login, String password) {
        emailInput.shouldBe(visible);
        emailInput.setValue(login);
        passwordInput.setValue(password).pressEnter();
        return this;
    }

    public AuthenticationPage setRegistrationLoginAndPasswords(String login, String password, String confirmPassword) {
        registrationButton.shouldBe(interactable);
        emailInput.setValue(login);
        passworRegistrationdInput.setValue(password);
        passworConfirmRegistrationdInput.setValue(confirmPassword).pressEnter();
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

    public AuthenticationPage checkSuccessRegistratiom() {
        assertThat(allertSuccess.getText()).isEqualTo("Регистрация прошла успешно, проверьте свою электронную почту, подтвердите отправку письма");
        return this;
    }

    public AuthenticationPage checkAlert(String alertText) {
        alertError.shouldBe(visible);
        assertThat(alertError.getText()).isEqualTo(alertText);
        return this;
    }

    public AuthenticationPage checkerrorHint() {
        errorHint.shouldBe(visible);
        return this;
    }


}
