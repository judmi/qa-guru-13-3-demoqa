package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {
    CalendarComponent calendar = new CalendarComponent();
    ResultsTableComponent resultsTable = new ResultsTableComponent();
    SelenideElement firstNameInput = $("#firstName");


    public RegistrationFormPage openPage(){
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFormPage setFirstName(String value) { //RegistrationFormPage для возможности вызова цепочки методов
        firstNameInput.setValue(value); // or $("[id=firstName]").setValue("Yulia");

        return this;
    }

    public RegistrationFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click(); //or $("#gender-radio-2").parent().click();

        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        $("#lastName").setValue(value);

        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        $("#userEmail").setValue(value);

        return this;
    }

    public RegistrationFormPage setNumber(String value) {
        $("#userNumber").setValue(value);

        return this;
    }

    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);

        return this;
    }

    public RegistrationFormPage setDateOfBirthWithKeys(String day, String month, String year) {
        $("#dateOfBirthInput").sendKeys(day + " " + month + " " + year);

        return this;
    }

    public RegistrationFormPage checkResult(String key, String value) {
        resultsTable.checkResult(key, value);

        return this;
    }

    public RegistrationFormPage checkResultArr(String key, String[] value) {
        resultsTable.checkResultArr(key, value);

        return this;
    }

    public RegistrationFormPage setSubjects(String[] subjects) {
        for (int i = 0; i < subjects.length; i++) {
            $("#subjectsInput").sendKeys(subjects[i]);
            $("#subjectsInput").pressEnter();
        }

        return this;
    }

    public RegistrationFormPage setHobbies(String[] hobbies) {
        for (int i = 0; i < hobbies.length; i++) {
            $("#hobbiesWrapper").$(byText(hobbies[i])).click();
        }

        return this;
    }

    public RegistrationFormPage uploadPicture(String fileName) {
        $("#uploadPicture").uploadFromClasspath(fileName);

        return this;
    }

    public RegistrationFormPage setAddress(String value) {
        $("#currentAddress").click();
        $("#currentAddress").setValue(value);

        return this;
    }

    public RegistrationFormPage setState(String value) {
        $("#state").click();
        $("#stateCity-wrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setCity(String value) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage clickSubmit() {
        $("#submit").click();

        return this;
    }

}
