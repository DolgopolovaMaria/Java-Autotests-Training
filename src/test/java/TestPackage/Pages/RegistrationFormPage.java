package TestPackage.Pages;

import TestPackage.Pages.Components.CalendarComponent;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class RegistrationFormPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    // locators
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement emailInput = $("#userEmail");
    SelenideElement mobileInput = $("#userNumber");
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    SelenideElement genderInput = $("#genterWrapper");
    SelenideElement hobbyInput = $("#hobbiesWrapper");
    SelenideElement uploadPicture = $("#uploadPicture");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement currentAddressInput = $("#currentAddress");

    SelenideElement stateInput = $("[id=state]");
    SelenideElement cityInput = $("[id=city]");

    SelenideElement submitButton = $("#submit");

    // actions
    @Step("Открываем главную страницу")
    public RegistrationFormPage openPage(){
        Selenide.open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationFormPage setFirstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage clearFirstName(String value){
        firstNameInput.clear();
        return this;
    }

    public RegistrationFormPage setLastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value){
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setEmail(String value){
        emailInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setMobile(String value){
        mobileInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year){
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationFormPage setHobby(String value){
        hobbyInput.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setPicture(String path){
        uploadPicture.uploadFromClasspath(path);
        return this;
    }

    public RegistrationFormPage setSubject(String value){
        subjectsInput.setValue(value).pressEnter();;
        return this;
    }

    public RegistrationFormPage setSubjects(String[] values){
        for (int i = 0; i < values.length; i++){
            subjectsInput.setValue(values[i]).pressEnter();
        }
        return this;
    }

    public RegistrationFormPage setCurrentAddress(String value){
        currentAddressInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setState(String value){
        stateInput.click();
        $(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setCity(String value){
        cityInput.click();
        $(byText(value)).click();
        return this;
    }

    public RegistrationFormPage submit(){
        submitButton.click();
        return this;
    }

    public RegistrationFormPage checkResult(String key, String value){
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    public RegistrationFormPage checkResult(String key, String[] values){
        for (int i = 0; i < values.length; i++){
            $(".table-responsive").$(byText(key)).parent().shouldHave(text(values[i]));
        }
        return this;
    }

    /*
        SelenideElement genderMaleInput = $("#genterWrapper").$(byText("Male"));
        SelenideElement genderFemaleInput = $("#genterWrapper").$(byText("Female"));
        SelenideElement genderOtherInput = $("#genterWrapper").$(byText("Other"));

        public RegistrationFormPage setGenderMale(){
        genderMaleInput.click();
        return this;
    }

    public RegistrationFormPage setGenderFemale(){
        genderFemaleInput.click();
        return this;
    }

    public RegistrationFormPage setGenderOther(){
        genderOtherInput.click();
        return this;
    }*/
/*
    SelenideElement hobbySportsInput = $("#hobbiesWrapper").$(byText("Sports"));
    SelenideElement hobbyReadingInput = $("#hobbiesWrapper").$(byText("Reading"));
    SelenideElement hobbyMusicInput = $("#hobbiesWrapper").$(byText("Music"));

    public RegistrationFormPage setHobbySports(){
        hobbySportsInput.click();
        return this;
    }

    public RegistrationFormPage setHobbyReading(){
        hobbyReadingInput.click();
        return this;
    }

    public RegistrationFormPage setHobbyMusic(){
        hobbyMusicInput.click();
        return this;
    }*/
}
