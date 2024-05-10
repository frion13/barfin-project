package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class RegistrationPage {

    final String studioRegister = "https://studio.barfin.network/auth/register";
    private final SelenideElement emailInput = $("#email");

    private final SelenideElement passwordRegistrationInput = $("#password1");
    private final SelenideElement passwordConfirmRegistrationInput = $("#password2");
    private final SelenideElement registrationLink = $("#barfin-link-register");
    private final SelenideElement registrationButton = $("#barfin-button-register");
    private final SelenideElement allertSuccess = $(".alert-success");
    private final SelenideElement alertError = $(".alert-error");
    private final SelenideElement errorHint = $(".error-hint");
    private final SelenideElement forgotPasswordLink = $("#barfin-link-forgot-password");
    private final SelenideElement submitEmailButton = $("#barfin-button-submit-email");


    public RegistrationPage openPage() {
        open(studioRegister);
        return this;
    }


    public RegistrationPage openRegistrationForm() {
        registrationLink.shouldBe(visible);
        registrationLink.click();
        return this;
    }



    public RegistrationPage setRegistrationLoginAndPasswords(String login, String password, String confirmPassword) {
        registrationButton.shouldBe(interactable);
        emailInput.setValue(login);
        passwordRegistrationInput.setValue(password);
        passwordConfirmRegistrationInput.setValue(confirmPassword).pressEnter();
        return this;
    }


    public RegistrationPage checkSuccessRegistration() {
        assertThat(allertSuccess.getText()).isEqualTo("Registration successful, check your email, confirmation letter has been sent");
        return this;
    }

    public RegistrationPage checkAlert(String alertText) {
        alertError.shouldBe(visible);
        assertThat(alertError.getText()).isEqualTo(alertText);
        return this;
    }

    public RegistrationPage checkErrorHint() {
        errorHint.shouldBe(visible);
        return this;
    }


}
